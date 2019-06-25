package com.example.lentanews

import android.content.Context
import android.opengl.ETC1.getHeight
import android.opengl.ETC1.getWidth
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.GridLayoutManager


class AutoFitGridLayoutManager(context: Context, newSpanCount: Int) : GridLayoutManager(context, 1) {

    override fun setSpanCount(spanCount: Int) {
        super.setSpanCount(spanCount)
    }

    init {
    }
}