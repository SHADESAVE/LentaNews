package com.example.lentanews.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lentanews.R
import com.example.lentanews.NewsRecyclerViewAdapter
import com.example.lentanews.NewsRecyclerViewItem
import java.util.*


class MainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_main, container, false)
        val recyclerView: RecyclerView = view!!.findViewById(R.id.main_recycler_view) as RecyclerView
        val news : ArrayList<NewsRecyclerViewItem> = arrayListOf()
        val headers : ArrayList<NewsRecyclerViewItem> = arrayListOf(
            NewsRecyclerViewItem(
                "Top7"
            ),
            NewsRecyclerViewItem("Last24"),
            NewsRecyclerViewItem("All")
        )
        var i : Int = 0

        while (i < 12) {
            i++
            news.add(
                NewsRecyclerViewItem(
                    "News" + i
                )
            )
        }
        recyclerView.setHasFixedSize(true)

        val recyclerViewAdapter = NewsRecyclerViewAdapter(news, headers)
        recyclerView.adapter = recyclerViewAdapter
        val layoutManager = LinearLayoutManager(this.activity)
        recyclerView.layoutManager = layoutManager


        return view
    }
}