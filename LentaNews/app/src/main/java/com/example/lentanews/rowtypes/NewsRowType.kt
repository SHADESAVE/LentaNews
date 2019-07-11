package com.example.lentanews.rowtypes

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.example.lentanews.async.DownloadImage
import com.example.lentanews.RecyclerViewHolders


class NewsRowType(private val tittle: String, private val description: String, private val imageLink: String, private val date: String, val link: String): RowType {

    override fun getItemViewType(): Int {

        return RowType.NEWS_ROW_TYPE
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position : Int) {

        Log.d("imageUrl", "url: $imageLink")
        val textViewHolder = viewHolder as RecyclerViewHolders.NewsViewHolder
        textViewHolder.imageTask = DownloadImage(textViewHolder.imageView, textViewHolder.progressBar)
        textViewHolder.imageTask!!.execute(imageLink)
        textViewHolder.click(link)
        textViewHolder.textDate.text = date
        textViewHolder.textTittle.text = tittle
        textViewHolder.textDescription.text = description
    }
}