package com.example.lentanews.rowtypes

import android.support.v7.widget.RecyclerView
import com.example.lentanews.RecyclerViewHolders


class NewsRowType(val tittle: String): RowType {

    override fun getItemViewType(): Int {
        return RowType.NEWS_ROW_TYPE
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position : Int) {
        val textViewHolder = viewHolder as RecyclerViewHolders.NewsViewHolder

        textViewHolder.textView1.setText(tittle)
    }
}