package com.example.lentanews.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.lentanews.R

class NewsListFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_news_list, container,false)
        val headerTextView : TextView = view.findViewById(R.id.headerTextView)
        val bundle = this.arguments
            headerTextView.text = bundle!!.get("header").toString()
        return view
    }
    }