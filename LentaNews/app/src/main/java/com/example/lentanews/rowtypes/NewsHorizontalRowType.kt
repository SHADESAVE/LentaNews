package com.example.lentanews.rowtypes

import android.support.v7.widget.RecyclerView
import com.example.lentanews.GetImage
import com.example.lentanews.RecyclerViewHolders


class NewsHorizontalRowType(private val tittle: String, private val description: String, private val imageUrl: String) : RowType {

    override fun getItemViewType(): Int {

        return RowType.NEWS_HORIZONTAL_ROW_TYPE
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {

        val textViewHolder = viewHolder as RecyclerViewHolders.NewsHorizontalViewHolder

        val getImage = GetImage(textViewHolder.imageView)
        getImage.execute(imageUrl)

        textViewHolder.textTittleHoriz.setText(tittle)
        textViewHolder.textDescriptionHoriz.setText(description)
    }
}