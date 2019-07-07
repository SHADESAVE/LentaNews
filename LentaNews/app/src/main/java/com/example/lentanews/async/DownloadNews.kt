package com.example.lentanews.async

import android.os.AsyncTask
import com.example.rssmodule.ReadRss

class DownloadNews: AsyncTask<Void, Void, ResultNewsData>() {

    private val rssParserTop7 = ReadRss()
    private val rssParserLast24 = ReadRss()
    private val rssParserAll = ReadRss()
    override fun doInBackground(vararg voids: Void): ResultNewsData {
        rssParserTop7.ProcessXml(rssParserTop7.Getdata("https://lenta.ru/rss/top7"))
        rssParserLast24.ProcessXml(rssParserLast24.Getdata("https://lenta.ru/rss/last24"))
        rssParserAll.ProcessXml(rssParserTop7.Getdata("https://lenta.ru/rss/news"))
        return ResultNewsData(
            rssParserTop7.getFeedItemts(),
            rssParserLast24.getFeedItemts(),
            rssParserAll.getFeedItemts()
        )
    }
}