package com.example.lentanews.async

import android.content.Context
import android.os.AsyncTask
import android.support.v7.app.AlertDialog
import com.example.lentanews.R
import com.example.rssmodule.ReadRss


class DownloadNews(builder: AlertDialog.Builder) : AsyncTask<Void, Void, ResultNewsData>() {
    val dialog = builder.create()

    override fun onPreExecute() {
        dialog.show()
        super.onPreExecute()
    }
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

    override fun onPostExecute(result: ResultNewsData?) {
        dialog.dismiss()
        super.onPostExecute(result)
    }
}