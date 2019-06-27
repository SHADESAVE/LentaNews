package com.example.lentanews.fragments

import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lentanews.*
import com.example.lentanews.rowtypes.HeaderRowType
import com.example.lentanews.rowtypes.NewsHorizontalRowType
import com.example.lentanews.rowtypes.NewsRowType
import com.example.lentanews.rowtypes.RowType
import java.util.*
import com.example.rssmodule.FeedItem
import com.example.rssmodule.ReadRss








class MainFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_main, container, false)

        val feedUrl = "http://www.sciencemag.org/rss/news_current.xml"

        val asyncRequest = AsyncRequest()
        asyncRequest.execute()

        return view
    }

    fun onClick(v: View, text: String) {
        val bundle = Bundle()
        bundle.putString("header", text)
        val newsListFragment = NewsListFragment()
        newsListFragment.setArguments(bundle)
        val activity = v.context as AppCompatActivity

        activity.getSupportFragmentManager().beginTransaction().replace(
            R.id.fragment_container,
            newsListFragment
        ).addToBackStack(null).commit()
    }

    inner class AsyncRequest() : AsyncTask<Void, Void, Void>() {
        val rssParser = ReadRss()

        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun doInBackground(vararg voids: Void): Void? {
            rssParser.ProcessXml(rssParser.Getdata())
            return null
        }

        override fun onPostExecute(aVoid: Void?) {
            super.onPostExecute(aVoid)

            val feedItem = rssParser.getFeedTiemts()
            Log.d("feedItem", "size "+feedItem.size)

            val mainList : ArrayList<RowType> = arrayListOf(
                HeaderRowType("Best7"),
                NewsRowType(feedItem[0].title),
                NewsRowType(feedItem[1].title),
                NewsRowType(feedItem[2].title),
                NewsRowType(feedItem[3].title),
                HeaderRowType("Last24"),
                NewsHorizontalRowType("itemH1"),
                NewsHorizontalRowType("itemH2"),
                NewsHorizontalRowType("itemH3"),
                NewsHorizontalRowType("itemH4"),
                HeaderRowType("All"),
                NewsHorizontalRowType("itemH1"),
                NewsHorizontalRowType("itemH2"),
                NewsHorizontalRowType("itemH3"),
                NewsHorizontalRowType("itemH4")

            )

            val recyclerView: RecyclerView = view!!.findViewById(R.id.main_recycler_view) as RecyclerView

            val recyclerViewAdapter = RecyclerViewAdapter(mainList)
            recyclerView.adapter = recyclerViewAdapter

            val layoutManager = recyclerViewAdapter.getLayoutManager(context!!)
            recyclerView.layoutManager = layoutManager
        }
    }
}