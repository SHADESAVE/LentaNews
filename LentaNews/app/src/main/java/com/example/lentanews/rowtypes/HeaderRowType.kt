package com.example.lentanews.rowtypes

import android.support.v7.widget.RecyclerView
import com.example.lentanews.RecyclerViewHolders


class HeaderRowType(val header: String) : RowType {
    override fun getItemViewType() : Int {
        return RowType.HEADER_ROW_TYPE
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder) {
        val textViewHolder = viewHolder as RecyclerViewHolders.HeaderViewHolder
        textViewHolder.headerTextView.setText(header)
    }
}