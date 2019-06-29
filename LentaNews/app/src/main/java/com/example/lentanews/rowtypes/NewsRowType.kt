package com.example.lentanews.rowtypes

import android.support.v7.widget.RecyclerView
import com.example.lentanews.RecyclerViewHolders


class NewsRowType(private val tittle: String, private val description: String): RowType {

    override fun getItemViewType(): Int {

        return RowType.NEWS_ROW_TYPE
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position : Int) {

        val textViewHolder = viewHolder as RecyclerViewHolders.NewsViewHolder

        textViewHolder.textTittle.setText(tittle)
        textViewHolder.textDescription.setText(description)
    }
}