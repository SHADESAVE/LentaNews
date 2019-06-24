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
import com.example.lentanews.RowType.Companion.HEADER_ROW_TYPE
import java.util.ArrayList

class NewsListFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_news_list, container,false)
        val headerTextView : TextView = view.findViewById(R.id.headerTextView)
        val bundle = this.arguments
            headerTextView.text = bundle!!.get("header").toString()

        val headers : List<RowType> = listOf (
            HeaderRowType("hello"),
            HeaderRowType("this"),
            NewsRowType("is"),
            HeaderRowType("typed"),
            NewsRowType("list")
        )

        val recyclerView: RecyclerView = view.findViewById(R.id.news_list_recycler_view)
        val recyclerViewAdapter = RecyclerViewAdapter(headers)
        recyclerView.adapter = recyclerViewAdapter
        val layoutManager = LinearLayoutManager(this.activity)
        recyclerView.layoutManager = layoutManager

        return view
    }
    }