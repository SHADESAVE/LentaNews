package com.example.lentanews.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.lentanews.*
import com.example.lentanews.rowtypes.NewsRowType
import com.example.lentanews.rowtypes.RowType
import kotlinx.android.synthetic.main.fragment_news_list.*
import java.util.ArrayList

class NewsListFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_news_list, container,false)
        val headerTextView : TextView = view.findViewById(R.id.headerTextView)
        val bundle = this.arguments
            headerTextView.text = bundle!!.get("header").toString()

        val headers : ArrayList<RowType> = arrayListOf()

        var i: Int = 0
        while (i < 20) {
            i++
            headers.add(NewsRowType("item" + i))
        }

        val recyclerView: RecyclerView = view.findViewById(R.id.news_list_recycler_view)
        recyclerView.setHasFixedSize(true)
        val recyclerViewAdapter = RecyclerViewAdapter(headers)
        recyclerView.adapter = recyclerViewAdapter
        val layoutManager = LinearLayoutManager(this.activity)
        recyclerView.layoutManager = layoutManager

        return view
    }
}