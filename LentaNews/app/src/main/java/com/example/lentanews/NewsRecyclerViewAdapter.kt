package com.example.lentanews

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Button
import com.example.lentanews.fragments.NewsListFragment
import android.os.Bundle
import android.support.v7.app.AppCompatActivity






class NewsRecyclerViewAdapter(val newsList: ArrayList<NewsRecyclerViewItem>, val headersList: ArrayList<NewsRecyclerViewItem>): RecyclerView.Adapter<NewsRecyclerViewAdapter.ViewHolder>() {
    var i: Int = 0
    var k: Int = 0

    class ViewHolder(itemView: View, p1: Int): RecyclerView.ViewHolder(itemView) {
        val textView: TextView
        lateinit var buttonView: Button
        init {
            if (p1 == 0) {
                textView = itemView.findViewById(R.id.headers)
                buttonView = itemView.findViewById(R.id.buttonView)
                buttonView.setOnClickListener(View.OnClickListener { onClick(itemView)})

            }
            else {
                textView = itemView.findViewById(R.id.textView1)
            }
        }

        fun onClick(v: View) {
            val bundle = Bundle()
            bundle.putString("header", textView.text as String?)
            val newsListFragment = NewsListFragment()
            newsListFragment.setArguments(bundle)
            val activity = v.context as AppCompatActivity

            activity.getSupportFragmentManager().beginTransaction().replace(
                R.id.fragment_container,
                newsListFragment
            ).addToBackStack(null).commit()
        }
    }
    override fun getItemViewType(position: Int): Int {
        var viewType = 1
        if (position == 0 || position % 5 == 0)
            viewType = 0
        return viewType
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): NewsRecyclerViewAdapter.ViewHolder {
        val view : View
        if(p1  == 1) {
            view = LayoutInflater.from(p0.context).inflate(R.layout.recycler_view_news, p0, false)
            return ViewHolder(view, p1)
        } else
        {
            view = LayoutInflater.from(p0.context).inflate(R.layout.recycler_view_headers, p0, false)
            return ViewHolder(view, p1)
        }
    }

    override fun getItemCount(): Int {
        return newsList.size + headersList.size
    }

    override fun onBindViewHolder(p0: NewsRecyclerViewAdapter.ViewHolder, p1: Int) {
        if (p1 % 5 == 0) {
            val item : NewsRecyclerViewItem = headersList.get(i)
            i++
            p0.textView.setText(item.getText())
        }
        else {
            val item : NewsRecyclerViewItem = newsList.get(k)
            k++
            p0.textView.setText(item.getText())
        }
    }
}