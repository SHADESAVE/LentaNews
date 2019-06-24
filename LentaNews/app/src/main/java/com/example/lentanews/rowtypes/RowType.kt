package com.example.lentanews

import android.support.v7.widget.RecyclerView


interface RowType {


    fun getItemViewType(): Int

    fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder)

    companion object {
        val HEADER_ROW_TYPE = 0
        val NEWS_ROW_TYPE = 1
    }
}