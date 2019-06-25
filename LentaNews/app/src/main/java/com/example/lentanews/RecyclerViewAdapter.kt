package com.example.lentanews

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.example.lentanews.rowtypes.RowType
import android.support.v7.widget.LinearLayoutManager
import com.example.lentanews.rowtypes.NewsHorizontalRowType
import com.example.lentanews.rowtypes.RowType.Companion.NEWS_HORIZONTAL_ROW_TYPE




class RecyclerViewAdapter(private val dataSet: List<RowType>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return dataSet[position].getItemViewType()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewHolders = RecyclerViewHolders()
        return viewHolders.create(parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        dataSet[position].onBindViewHolder(holder)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}
