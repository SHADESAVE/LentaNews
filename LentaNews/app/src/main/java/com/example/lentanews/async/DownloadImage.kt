package com.example.lentanews.async

import android.graphics.Bitmap
import android.os.AsyncTask
import android.graphics.BitmapFactory
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import java.io.IOException
import java.lang.ref.WeakReference
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL


class DownloadImage(imv: ImageView,val prgB: ProgressBar) : AsyncTask<String, Void, Bitmap>() {
    private val imageview: WeakReference<ImageView>?

    init {
        imageview = WeakReference(imv)
    }
    override fun doInBackground(vararg urls: String): Bitmap? {
        return if (isCancelled) {
            null
        } else {
            getBitMapFromUrl(urls[0])
        }
    }

    override fun onPostExecute(result: Bitmap?) {
        if (imageview != null && result != null) {
            imageview.get()?.setImageBitmap(result)
        }
        prgB.visibility = View.GONE
    }

    private fun getBitMapFromUrl(imageuri: String): Bitmap? {
        return try {
            val url = URL(imageuri)
            Log.d("bucky", "bitmap$imageuri")
            val connection = url.openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()
            val `is` = connection.inputStream
            BitmapFactory.decodeStream(`is`)
        } catch (e: MalformedURLException) {
            e.printStackTrace()
            null
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

}