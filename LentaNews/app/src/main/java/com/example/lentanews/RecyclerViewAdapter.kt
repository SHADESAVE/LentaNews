package com.example.lentanews

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup


class RecyclerViewAdapter(private val dataSet: List<RowType>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return dataSet[position].getItemViewType()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewHolders = ViewHolders()
        return viewHolders.create(parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        dataSet[position].onBindViewHolder(holder)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}
