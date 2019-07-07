package com.example.lentanews.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.lentanews.*
import com.example.lentanews.rowtypes.NewsRowType
import com.example.lentanews.rowtypes.RowType
import java.util.ArrayList
import android.support.v7.app.AppCompatActivity
import com.example.rssmodule.FeedItem


class NewsListFragment: Fragment() {

    var feedItem = arrayListOf<FeedItem>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_news_list, container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val headerTextView : TextView = view.findViewById(R.id.headerTextView)
        val bundle: Bundle? = this.arguments
        val header = bundle?.getString("header")
        headerTextView.text = header
        when (header) {
            "Top7" -> feedItem = (activity as MainActivity).newsList!!.top7
            "Last24" -> feedItem = (activity as MainActivity).newsList!!.last24
            "All" -> feedItem = (activity as MainActivity).newsList!!.all

        }
        val newsList: ArrayList<RowType> = arrayListOf()
        for (i in 0 until feedItem.size) {
            newsList.add(NewsRowType(feedItem[i].title, feedItem[i].description, feedItem[i].imageUrl, feedItem[i].pubDate, feedItem[i].link))
        }
        val recyclerView: RecyclerView = view.findViewById(R.id.news_list_recycler_view)
        recyclerView.setHasFixedSize(true)
        val recyclerViewAdapter = RecyclerViewAdapter(newsList)
        recyclerView.adapter = recyclerViewAdapter
        val layoutManager = LinearLayoutManager(this.activity)
        recyclerView.layoutManager = layoutManager
    }
}