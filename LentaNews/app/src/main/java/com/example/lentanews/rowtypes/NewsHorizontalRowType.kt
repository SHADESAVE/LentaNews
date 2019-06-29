package com.example.lentanews.rowtypes

import android.support.v7.widget.RecyclerView
import com.example.lentanews.RecyclerViewHolders


class NewsHorizontalRowType(private val tittle: String, private val description: String) : RowType {

    override fun getItemViewType(): Int {

        return RowType.NEWS_HORIZONTAL_ROW_TYPE
    }

    override fun getLink(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {

        val textViewHolder = viewHolder as RecyclerViewHolders.NewsHorizontalViewHolder

        textViewHolder.textTittleHoriz.setText(tittle)
        textViewHolder.textDescriptionHoriz.setText(description)
    }
}