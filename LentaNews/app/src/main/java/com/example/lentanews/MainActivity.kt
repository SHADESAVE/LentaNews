package com.example.lentanews

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.lentanews.fragments.MainFragment
import com.example.lentanews.rowtypes.RowType
import com.example.rssmodule.FeedItem

class
MainActivity : AppCompatActivity() {

    var mainList: ArrayList<RowType> = arrayListOf()
    var feedItemTop7: ArrayList<FeedItem> = arrayListOf()
    var feedItemLast24: ArrayList<FeedItem> = arrayListOf()
    var feedItemAll: ArrayList<FeedItem> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(
            R.id.fragment_container,
            MainFragment()
        ).commit()
    }
}
