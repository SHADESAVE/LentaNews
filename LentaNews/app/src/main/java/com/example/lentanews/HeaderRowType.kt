package com.example.lentanews

import android.support.v7.widget.RecyclerView



class HeaderRowType(val header: String) : RowType {
    override fun getItemViewType() : Int {
        return RowType.HEADER_ROW_TYPE
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder) {
        val textViewHolder = viewHolder as ViewHolders.HeaderViewHolder
        textViewHolder.headerTextView.setText(header)
    }
}