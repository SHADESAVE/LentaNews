package com.example.lentanews.rowtypes

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.RelativeLayout
import com.example.lentanews.RecyclerViewHolders
import android.support.v7.widget.StaggeredGridLayoutManager



class NewsHorizontalRowType(val news : String) : RowType {
    override fun getItemViewType(): Int {
        return RowType.NEWS_HORIZONTAL_ROW_TYPE
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder) {
        val textViewHolder = viewHolder as RecyclerViewHolders.NewsHorizontalViewHolder
        textViewHolder.textView2.setText(news)
    }
}