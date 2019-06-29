package com.example.lentanews.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.lentanews.*
import com.example.lentanews.rowtypes.NewsRowType
import com.example.lentanews.rowtypes.RowType
import java.util.ArrayList
import android.os.AsyncTask
import com.example.rssmodule.FeedItem
import com.example.rssmodule.ReadRss


class NewsListFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_news_list, container,false)
        val headerTextView : TextView = view.findViewById(R.id.headerTextView)
        val bundle: Bundle? = this.arguments
        val header = bundle?.getString("header")
        headerTextView.text = header

        var feedItem = arrayListOf<FeedItem>()

        when (header) {
            "Top7" -> feedItem = (activity as MainActivity).feedItemTop7
            "Last24" -> feedItem = (activity as MainActivity).feedItemLast24
            "All" -> feedItem = (activity as MainActivity).feedItemAll

        }

        val newsList: ArrayList<RowType> = arrayListOf()
        for (i in 0..feedItem!!.size - 1) {
            newsList.add(NewsRowType(feedItem[i].title, feedItem[i].description))
        }

        val recyclerView: RecyclerView = view!!.findViewById(R.id.news_list_recycler_view)

        recyclerView.setHasFixedSize(true)
        val recyclerViewAdapter = RecyclerViewAdapter(newsList)
        recyclerView.adapter = recyclerViewAdapter

        val layoutManager = LinearLayoutManager(this.activity)
        recyclerView.layoutManager = layoutManager

        return view
    }
}