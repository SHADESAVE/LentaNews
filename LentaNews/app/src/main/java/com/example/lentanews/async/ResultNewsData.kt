package com.example.lentanews.async

import com.example.rssmodule.FeedItem

data class ResultNewsData (
    val top7: ArrayList<FeedItem>,
    val last24: ArrayList<FeedItem>,
    val all: ArrayList<FeedItem>
)