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

                        if (cureent.getNodeName().equals("title", ignoreCase = true)) {
                            item.title = (cureent.getTextContent())
                        } else if (cureent.getNodeName().equals("description", ignoreCase = true)) {
                            item.description = (cureent.getTextContent())
                        } else if (cureent.getNodeName().equals("pubDate", ignoreCase = true)) {
                            item.pubDate = (cureent.getTextContent())
                        } else if (cureent.getNodeName().equals("link", ignoreCase = true)) {
                            item.link = (cureent.getTextContent())
                            //Log.d("loadFeedLink", "url: " + item.link)
                        } else if (cureent.getNodeName().equals("enclosure", ignoreCase = true)) {
                            val url = cureent.getAttributes().item(0).getTextContent()
                            item.imageUrl = (url)
                            Log.d("loadImageUrl", "url: " + item.imageUrl)
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