package com.example.lentanews

import android.support.v7.widget.RecyclerView

class NewsRowType(val news: String): RowType {
    override fun getItemViewType(): Int {
        return RowType.NEWS_ROW_TYPE
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder) {
        val textViewHolder = viewHolder as ViewHolders.NewsViewHolder
        textViewHolder.textView1.setText(news)
    }
}