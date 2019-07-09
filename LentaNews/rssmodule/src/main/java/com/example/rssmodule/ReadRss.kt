package com.example.rssmodule

import android.util.Log
import java.net.HttpURLConnection;
import java.net.URL;

import org.w3c.dom.Document
import javax.xml.parsers.DocumentBuilderFactory


class ReadRss(){

    //val address = "https://lenta.ru/rss/top7"
    var feedItems: ArrayList<FeedItem> = arrayListOf()

    fun getFeedItemts(): ArrayList<FeedItem> {
        return feedItems
    }
    fun ProcessXml(data: Document?) {
        if (data != null) {

            val root = data.getDocumentElement()
            val channel = root.getChildNodes().item(1)
            val items = channel.getChildNodes()

            for (i in 0 until items.getLength()) {

                val cureentchild = items.item(i)

                if (cureentchild.getNodeName().equals("item", ignoreCase = true)) {

                    val item = FeedItem()
                    val itemchilds = cureentchild.getChildNodes()

                    for (j in 0 until itemchilds.getLength()) {

                        val cureent = itemchilds.item(j)

                        when {
                            cureent.nodeName.equals("title", ignoreCase = true) -> item.title = (cureent.textContent)
                            cureent.nodeName.equals("description", ignoreCase = true) -> item.description = (cureent.textContent)
                            cureent.nodeName.equals("pubDate", ignoreCase = true) -> item.pubDate = (cureent.textContent).substring(0, (cureent.textContent).length-9)
                            cureent.nodeName.equals("link", ignoreCase = true) -> item.link = (cureent.textContent)
                            //Log.d("loadFeedLink", "url: " + item.link)
                            cureent.nodeName.equals("enclosure", ignoreCase = true) -> {
                                val url = cureent.attributes.item(0).textContent
                                item.imageUrl = (url)
                                Log.d("loadImageUrl", "url: " + item.imageUrl)
                            }
                        }
                    }
                    feedItems.add(item)
                    Log.d("feed", "addedItem"+feedItems.size)

                }
            }
        }
    }

    //This method will download rss feed document from specified url
    fun Getdata(address: String): Document? {
        try {
            val url = URL(address)
            val connection = url.openConnection() as HttpURLConnection
            connection.setRequestMethod("GET")
            val inputStream = connection.getInputStream()
            val builderFactory = DocumentBuilderFactory.newInstance()
            val builder = builderFactory.newDocumentBuilder()
            return builder.parse(inputStream)
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("feed", "errorConnection")
            return null
        }

    }
}