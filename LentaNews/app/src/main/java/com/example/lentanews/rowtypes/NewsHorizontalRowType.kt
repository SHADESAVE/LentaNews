package com.example.lentanews.rowtypes

import android.support.v7.widget.RecyclerView
import com.example.lentanews.async.DownloadImage
import com.example.lentanews.RecyclerViewHolders


class NewsHorizontalRowType(private val tittle: String, private val description: String, private val imageUrl: String, private val link: String, private val date: String) : RowType {

    override fun getItemViewType(): Int {

        return RowType.NEWS_HORIZONTAL_ROW_TYPE
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val textViewHolder = viewHolder as RecyclerViewHolders.NewsHorizontalViewHolder
        textViewHolder.imageTask = DownloadImage(textViewHolder.imageView, textViewHolder.progressBar)
        textViewHolder.imageTask!!.execute(imageUrl)
        textViewHolder.textDate.text = date
        textViewHolder.click(link)
        textViewHolder.textTittleHoriz.text = tittle
        textViewHolder.textDescriptionHoriz.text = description
    }
}