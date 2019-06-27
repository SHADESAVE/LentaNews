package com.example.lentanews

import android.content.Context
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.example.lentanews.rowtypes.NewsHorizontalRowType
import com.example.lentanews.rowtypes.RowType


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

    fun getLayoutManager(context: Context): GridLayoutManager {
        val layoutManager = GridLayoutManager(context, 2)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                if (getItemViewType(position) == 2)
                    return 1
                else
                    return 2
            }
        }
        return layoutManager
    }
}
