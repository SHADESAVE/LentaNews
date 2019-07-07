package com.example.lentanews

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.support.v7.widget.RecyclerView
import android.widget.TextView import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.ImageView
import com.example.lentanews.fragments.MainFragment
import com.example.lentanews.fragments.NewsListFragment
import com.example.lentanews.rowtypes.NewsRowType
import com.example.lentanews.rowtypes.RowType
import android.R.attr.button
import com.example.lentanews.async.DownloadImage
import com.example.lentanews.fragments.WebViewFragment
import kotlinx.android.synthetic.main.recycler_view_news.view.*
import android.os.AsyncTask.execute




class RecyclerViewHolders {

    class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var headerTextView: TextView = itemView.findViewById(R.id.headers)
        var button: Button = itemView.findViewById(R.id.buttonView) as Button

        init {
            button.setOnClickListener {
                val newsListFragment = NewsListFragment()
                val activity = itemView.context as AppCompatActivity
                val bundle = Bundle()

                bundle.putString("header", headerTextView.text.toString())
                newsListFragment.arguments = bundle

                activity.supportFragmentManager.beginTransaction().replace(
                    R.id.fragment_container,
                    newsListFragment
                ).addToBackStack(null).commit()
            }
        }
    }

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val textTittle: TextView = itemView.findViewById(R.id.textTittle)
        val textDescription: TextView = itemView.findViewById(R.id.textDescription)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val textDate: TextView = itemView.findViewById(R.id.date_text)

        fun click(link: String) {
            itemView.setOnClickListener {
                val webFragment = WebViewFragment()
                val activity = itemView.context as AppCompatActivity
                val bundle = Bundle()
                bundle.putString("link", link)
                webFragment.arguments = bundle

                activity.getSupportFragmentManager().beginTransaction().replace(
                    R.id.fragment_container,
                    webFragment
                ).addToBackStack(null).commit()
            }
        }
    }

    class NewsHorizontalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val textTittleHoriz: TextView = itemView.findViewById(R.id.textTittleHoriz)
        val textDescriptionHoriz: TextView = itemView.findViewById(R.id.textDescriptionHoriz)
        val imageView: ImageView = itemView.findViewById(R.id.imageViewHoriz)

        fun click(link: String) {
            itemView.setOnClickListener {
                val webFragment = WebViewFragment()
                val activity = itemView.context as AppCompatActivity
                val bundle = Bundle()
                bundle.putString("link", link)
                webFragment.arguments = bundle

                activity.getSupportFragmentManager().beginTransaction().replace(
                    R.id.fragment_container,
                    webFragment
                ).addToBackStack(null).commit()
            }
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