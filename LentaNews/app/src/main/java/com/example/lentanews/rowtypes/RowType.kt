package com.example.lentanews.rowtypes

import android.support.v7.widget.RecyclerView


interface RowType {

    fun getItemViewType(): Int

    fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder)

    companion object {
        const val HEADER_ROW_TYPE = 0
        const val NEWS_ROW_TYPE = 1
        const val NEWS_HORIZONTAL_ROW_TYPE = 2
    }
}