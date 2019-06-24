package com.example.lentanews

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.View;
import android.widget.Toast
import com.example.lentanews.fragments.NewsListFragment


class HeaderRowType(val header: String) : RowType {
    override fun getItemViewType() : Int {
        return RowType.HEADER_ROW_TYPE
    }


    fun onclick(){
    }
//    fun onClick(v: View) {
//        val bundle = Bundle()
//        bundle.putString("header", textView.text as String?)
//        val newsListFragment = NewsListFragment()
//        newsListFragment.setArguments(bundle)
//        val activity = v.context as AppCompatActivity
//
//        activity.getSupportFragmentManager().beginTransaction().replace(
//            R.id.fragment_container,
//            newsListFragment
//        ).addToBackStack(null).commit()
//    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder) {
        val textViewHolder = viewHolder as ViewHolders.HeaderViewHolder
        textViewHolder.headerTextView.setText(header)
    }
}