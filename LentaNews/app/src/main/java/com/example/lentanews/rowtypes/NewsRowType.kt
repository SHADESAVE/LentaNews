package com.example.lentanews.rowtypes

import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.lentanews.DownloadImage
import com.example.lentanews.RecyclerViewHolders


class NewsRowType(private val tittle: String, private val description: String, private val imageLink: String, private val date: String): RowType {

    override fun getItemViewType(): Int {

        return RowType.NEWS_ROW_TYPE
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position : Int) {

        Log.d("imageUrl", "url: $imageLink")
        val textViewHolder = viewHolder as RecyclerViewHolders.NewsViewHolder
        //textViewHolder.imageView.setImageDrawable (null)
        //textViewHolder.imageView.context.
        //textViewHolder.imageView.setVisibility(View.GONE)
        val getImage = DownloadImage(textViewHolder.imageView)
        //getImage.cancel(true)
        getImage.execute(imageLink)
        //textViewHolder.imageView.setVisibility(View.VISIBLE)
        textViewHolder.textDate.text = date
        textViewHolder.textTittle.text = tittle
        textViewHolder.textDescription.text = description
    }
}