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
import com.example.rssmodule.FeedItem
import com.example.rssmodule.ReadRss
import kotlin.collections.ArrayList
import android.app.Activity




class MainFragment : Fragment() {

    var mActivity: Activity? = null

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        mActivity = activity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_main, container, false)

        val asyncRequest = AsyncRequest()
        asyncRequest.execute()

        return view
    }

    fun onClick(v: View, text: String) {
        val newsListFragment = NewsListFragment()
        val activity = v.context as AppCompatActivity
        val bundle = Bundle()

        bundle.putString("header", text)
        newsListFragment.setArguments(bundle)

        activity.getSupportFragmentManager().beginTransaction().replace(
            R.id.fragment_container,
            newsListFragment
        ).addToBackStack(null).commit()
    }

    inner class AsyncRequest() : AsyncTask<Void, Void, Void>() {
        val rssParserTop7 = ReadRss()
        val rssParserLast24 = ReadRss()
        val rssParserAll = ReadRss()

        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun doInBackground(vararg voids: Void): Void? {
            rssParserTop7.ProcessXml(rssParserTop7.Getdata("https://lenta.ru/rss/top7"))
            rssParserLast24.ProcessXml(rssParserLast24.Getdata("https://lenta.ru/rss/last24"))
            rssParserAll.ProcessXml(rssParserTop7.Getdata("https://lenta.ru/rss/news"))
            return null
        }

        override fun onPostExecute(aVoid: Void?) {
            super.onPostExecute(aVoid)

            val feedItemTop7 = rssParserTop7.getFeedItemts()
            Log.d("feedTop7", "size "+feedItemTop7.size)

            val feedItemLast24 = rssParserLast24.getFeedItemts()
            Log.d("feedLast24", "size "+feedItemLast24.size)

            val feedItemAll = rssParserAll.getFeedItemts()
            Log.d("feedAll", "size "+feedItemAll.size)

            val mainList : ArrayList<RowType> = arrayListOf(
                HeaderRowType("Top7"),
                NewsRowType(feedItemTop7[0].title),
                NewsRowType(feedItemTop7[1].title),
                NewsRowType(feedItemTop7[2].title),
                NewsRowType(feedItemTop7[3].title),
                HeaderRowType("Last24"),
                NewsHorizontalRowType(feedItemLast24[0].title),
                NewsHorizontalRowType(feedItemLast24[1].title),
                NewsHorizontalRowType(feedItemLast24[2].title),
                NewsHorizontalRowType(feedItemLast24[3].title),
                HeaderRowType("All"),
                NewsHorizontalRowType(feedItemAll[0].title),
                NewsHorizontalRowType(feedItemAll[1].title),
                NewsHorizontalRowType(feedItemAll[2].title),
                NewsHorizontalRowType(feedItemAll[3].title)
            )

            (activity as MainActivity).feedItemTop7 = feedItemTop7
            (activity as MainActivity).feedItemLast24 = feedItemLast24
            (activity as MainActivity).feedItemAll = feedItemAll
            Log.d("main","size "+ (activity as MainActivity).feedItemTop7.size)

            val recyclerView: RecyclerView = view!!.findViewById(R.id.main_recycler_view) as RecyclerView
            recyclerView.setHasFixedSize(true)

            val recyclerViewAdapter = RecyclerViewAdapter(mainList)
            recyclerView.adapter = recyclerViewAdapter

            val layoutManager = recyclerViewAdapter.getLayoutManager(context!!)
            recyclerView.layoutManager = layoutManager
        }
    }
}