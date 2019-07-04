package com.example.lentanews

import android.view.LayoutInflater
import android.view.ViewGroup
import android.support.v7.widget.RecyclerView
import android.widget.TextView import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.ImageView
import com.example.lentanews.fragments.MainFragment
import com.example.lentanews.rowtypes.NewsRowType
import com.example.lentanews.rowtypes.RowType



class RecyclerViewHolders {

    class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var headerTextView: TextView = itemView.findViewById(R.id.headers)
        var button: Button = itemView.findViewById(R.id.buttonView) as Button

        init {
            button.setOnClickListener {
                val mainFragment = MainFragment()
                mainFragment.onClick(itemView, headerTextView.text as String)
            }
        }
    }

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val textTittle: TextView = itemView.findViewById(R.id.textTittle)
        val textDescription: TextView = itemView.findViewById(R.id.textDescription)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val textDate: TextView = itemView.findViewById(R.id.date_text)

        init {
            itemView.setOnClickListener{
                val mainFragment = MainFragment()
                mainFragment.onItemClick(itemView, adapterPosition)
            }
        }

    }

    class NewsHorizontalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val textTittleHoriz: TextView = itemView.findViewById(R.id.textTittleHoriz)
        val textDescriptionHoriz: TextView = itemView.findViewById(R.id.textDescriptionHoriz)
        val imageView: ImageView = itemView.findViewById(R.id.imageViewHoriz)

        init {
            itemView.setOnClickListener{
                val mainFragment = MainFragment()
                mainFragment.onItemClick(itemView, adapterPosition)
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