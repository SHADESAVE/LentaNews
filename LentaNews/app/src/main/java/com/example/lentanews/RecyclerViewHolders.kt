package com.example.lentanews

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.ViewGroup
import android.support.v7.widget.RecyclerView
import android.widget.TextView import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.example.lentanews.fragments.MainFragment
import com.example.lentanews.rowtypes.RowType
import com.example.lentanews.fragments.NewsListFragment
import kotlinx.android.synthetic.main.recycler_view_news_horizontal.view.*
import android.support.v7.widget.LinearLayoutManager
import com.example.rssmodule.FeedItem


class RecyclerViewHolders {

    class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var headerTextView: TextView
        var button: Button

        init {
            headerTextView = itemView.findViewById(R.id.headers)
            button = itemView.findViewById(R.id.buttonView) as Button
            val mainFragment = MainFragment()
            button.setOnClickListener {
                mainFragment.onClick(itemView, headerTextView.text as String)
            }
        }
    }

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var textView1: TextView

        init {
            textView1 = itemView.findViewById(R.id.textView1)
        }

    }

    class NewsHorizontalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var textView2: TextView
        init {
            textView2 = itemView.findViewById(R.id.textView2)
        }

    }

    fun create(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        when (viewType) {
            RowType.HEADER_ROW_TYPE -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recycler_view_headers, parent, false)
                return HeaderViewHolder(view)
            }

            RowType.NEWS_ROW_TYPE -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recycler_view_news, parent, false)
                return NewsViewHolder(view)
            }

            RowType.NEWS_HORIZONTAL_ROW_TYPE -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recycler_view_news_horizontal, parent, false)
                return NewsHorizontalViewHolder(view)
            }

            else ->  {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recycler_view_news, parent, false)
                return NewsViewHolder(view)
            }
        }
    }
}