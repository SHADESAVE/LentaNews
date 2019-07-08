package com.example.lentanews

import android.content.Context
import android.inputmethodservice.Keyboard
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.example.lentanews.rowtypes.NewsHorizontalRowType
import com.example.lentanews.rowtypes.NewsRowType
import com.example.lentanews.rowtypes.RowType
import com.example.lentanews.rowtypes.RowType.Companion.NEWS_HORIZONTAL_ROW_TYPE
import com.example.lentanews.rowtypes.RowType.Companion.NEWS_ROW_TYPE
import com.example.rssmodule.FeedItem
import kotlinx.android.synthetic.main.recycler_view_news.view.*

const val NEWS_HORIZONTAL_ROW_TYPE = 2

class RecyclerViewAdapter(private val dataSet: List<RowType>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return dataSet[position].getItemViewType()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewHolders = RecyclerViewHolders()
        return viewHolders.create(parent, viewType)
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        dataSet[position].onBindViewHolder(holder, position)
    }
    override fun getItemCount(): Int {
        return dataSet.size
    }
    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        if (holder.itemViewType == NEWS_ROW_TYPE) {
            (holder as RecyclerViewHolders.NewsViewHolder).imageView.setImageBitmap(null)
            holder.imageTask!!.cancel(true)
            Log.d("key", "1")
        }
        if (holder.itemViewType == NEWS_HORIZONTAL_ROW_TYPE) {
            (holder as RecyclerViewHolders.NewsHorizontalViewHolder).imageView.setImageBitmap(null)
            holder.imageTask!!.cancel(true)
            Log.d("key", "2")
        }
        super.onViewRecycled(holder)
    }

    fun getLayoutManager(context: Context): GridLayoutManager {
        val layoutManager = GridLayoutManager(context, 2)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (getItemViewType(position) == NEWS_HORIZONTAL_ROW_TYPE)
                    1
                else
                    2
            }
        }
        return layoutManager
    }
}
