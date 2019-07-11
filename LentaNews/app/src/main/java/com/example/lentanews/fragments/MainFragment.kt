package com.example.lentanews.fragments

import android.support.v7.app.AlertDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lentanews.*
import com.example.lentanews.rowtypes.HeaderRowType
import com.example.lentanews.rowtypes.NewsHorizontalRowType
import com.example.lentanews.rowtypes.NewsRowType
import com.example.lentanews.rowtypes.RowType
import kotlin.collections.ArrayList
import com.example.lentanews.async.DownloadNews


class MainFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = view.findViewById(R.id.main_recycler_view) as RecyclerView
        val recyclerViewAdapter = RecyclerViewAdapter(getResult())
        val layoutManager = recyclerViewAdapter.getLayoutManager(context!!)

        recyclerView.adapter = recyclerViewAdapter
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
    }
    private fun getResult(): ArrayList<RowType>  {
        val builder = AlertDialog.Builder(context!!)
        builder.setView(R.layout.progress_dialog)
        builder.setCancelable(false)
        val asyncRequest = DownloadNews(builder)
        val news = asyncRequest.execute().get()
        (activity as MainActivity).newsList = news

        return arrayListOf(
                HeaderRowType("Top7"),
                NewsRowType(news.top7[0].title, news.top7[0].description,
                    news.top7[0].imageUrl, news.top7[0].pubDate, news.top7[0].link),
                NewsRowType(news.top7[1].title, news.top7[1].description,
                    news.top7[1].imageUrl, news.top7[1].pubDate, news.top7[1].link),
                NewsRowType(news.top7[2].title, news.top7[2].description,
                    news.top7[2].imageUrl, news.top7[2].pubDate, news.top7[2].link),
                NewsRowType(news.top7[3].title, news.top7[3].description,
                    news.top7[3].imageUrl, news.top7[3].pubDate, news.top7[3].link),

                HeaderRowType("Last24"),
                NewsHorizontalRowType(news.last24[0].title, news.last24[0].description,
                    news.last24[0].imageUrl, news.last24[0].link, news.last24[0].pubDate),
                NewsHorizontalRowType(news.last24[1].title, news.last24[1].description,
                    news.last24[1].imageUrl, news.last24[1].link, news.last24[1].pubDate),
                NewsHorizontalRowType(news.last24[2].title, news.last24[2].description,
                    news.last24[2].imageUrl, news.last24[2].link, news.last24[2].pubDate),
                NewsHorizontalRowType(news.last24[3].title, news.last24[3].description,
                    news.last24[3].imageUrl, news.last24[3].link, news.last24[3].pubDate),

                HeaderRowType("All"),
                NewsHorizontalRowType(news.all[0].title, news.all[0].description,
                    news.all[0].imageUrl, news.all[0].link, news.all[0].pubDate),
                NewsHorizontalRowType(news.all[1].title, news.all[1].description,
                    news.all[1].imageUrl, news.all[1].link, news.all[1].pubDate),
                NewsHorizontalRowType(news.all[2].title, news.all[2].description,
                    news.all[2].imageUrl, news.all[2].link, news.all[2].pubDate),
                NewsHorizontalRowType(news.all[3].title, news.all[3].description,
                    news.all[3].imageUrl, news.all[3].link, news.all[3].pubDate)
        )
    }
}