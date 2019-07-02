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
import com.example.rssmodule.ReadRss
import kotlin.collections.ArrayList
import android.support.v7.app.AlertDialog


class MainFragment : Fragment() {


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

    fun onItemClick(v: View, position: Int) {
        val webFragment = WebViewFragment()
        val activity = v.context as AppCompatActivity
        val bundle = Bundle()

        if (position < 5) {
            bundle.putString("link", (activity as MainActivity).feedItemTop7[position-1].link)

        } else if (position < 10) {
            bundle.putString("link", (activity as MainActivity).feedItemLast24[position-6].link)

        } else {
            bundle.putString("link", (activity as MainActivity).feedItemAll[position-11].link)
        }
        webFragment.setArguments(bundle)

        activity.getSupportFragmentManager().beginTransaction().replace(
            R.id.fragment_container,
            webFragment
        ).addToBackStack(null).commit()
    }

    inner class AsyncRequest() : AsyncTask<Void, Void, Void>() {

        val rssParserTop7 = ReadRss()
        val rssParserLast24 = ReadRss()
        val rssParserAll = ReadRss()
        val dialog: AlertDialog

        init {
            val builder = AlertDialog.Builder(context!!)
            builder.setView(R.layout.progress_dialog)
            builder.setCancelable(false);
            dialog = builder.create()
        }
        override fun onPreExecute() {
            super.onPreExecute()
            dialog.show()
        }

        override fun doInBackground(vararg voids: Void): Void? {
            rssParserTop7.ProcessXml(rssParserTop7.Getdata("https://lenta.ru/rss/top7"))
            rssParserLast24.ProcessXml(rssParserLast24.Getdata("https://lenta.ru/rss/last24"))
            rssParserAll.ProcessXml(rssParserTop7.Getdata("https://lenta.ru/rss/news"))
            return null
        }

        override fun onPostExecute(aVoid: Void?) {
            super.onPostExecute(aVoid)
            dialog.dismiss()

            val feedItemTop7 = rssParserTop7.getFeedItemts()
            Log.d("feedTop7", "size "+feedItemTop7.size)

            val feedItemLast24 = rssParserLast24.getFeedItemts()
            Log.d("feedLast24", "size "+feedItemLast24.size)

            val feedItemAll = rssParserAll.getFeedItemts()
            Log.d("feedAll", "size "+feedItemAll.size)
            Log.d("feedDescriptionAndLink", "D: \n"+feedItemTop7[0].description + "L: \n"+feedItemTop7[0].link)


            //Обработать size
            val mainList : ArrayList<RowType> = arrayListOf(
                HeaderRowType("Top7"),
                NewsRowType(feedItemTop7[0].title, feedItemTop7[0].description, feedItemTop7[0].imageUrl),
                NewsRowType(feedItemTop7[1].title, feedItemTop7[1].description, feedItemTop7[1].imageUrl),
                NewsRowType(feedItemTop7[2].title, feedItemTop7[2].description, feedItemTop7[2].imageUrl),
                NewsRowType(feedItemTop7[3].title, feedItemTop7[3].description, feedItemTop7[3].imageUrl),
                HeaderRowType("Last24"),
                NewsHorizontalRowType(feedItemLast24[0].title, feedItemLast24[0].description, feedItemLast24[0].imageUrl),
                NewsHorizontalRowType(feedItemLast24[1].title, feedItemLast24[1].description, feedItemLast24[1].imageUrl),
                NewsHorizontalRowType(feedItemLast24[2].title, feedItemLast24[2].description, feedItemLast24[2].imageUrl),
                NewsHorizontalRowType(feedItemLast24[3].title, feedItemLast24[3].description, feedItemLast24[3].imageUrl),
                HeaderRowType("All"),
                NewsHorizontalRowType(feedItemAll[0].title, feedItemAll[0].description, feedItemAll[0].imageUrl),
                NewsHorizontalRowType(feedItemAll[1].title, feedItemAll[1].description, feedItemAll[1].imageUrl),
                NewsHorizontalRowType(feedItemAll[2].title, feedItemAll[2].description, feedItemAll[2].imageUrl),
                NewsHorizontalRowType(feedItemAll[3].title, feedItemAll[3].description, feedItemAll[3].imageUrl)
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