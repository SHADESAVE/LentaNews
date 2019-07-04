package com.example.lentanews

import android.graphics.Bitmap
import android.os.AsyncTask
import android.graphics.BitmapFactory
import android.util.Log
import android.widget.ImageView
import java.io.IOException
import java.lang.ref.WeakReference
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL


class DownloadImage(imv: ImageView) : AsyncTask<String, Void, Bitmap>() {
    private val imageview: WeakReference<ImageView>?

    init {
        imageview = WeakReference<ImageView>(imv)
    }

    /** Background process
     * input:url
     * output: Bitmap image
     * It passed into onPostExecute method
     */
    override fun doInBackground(vararg urls: String): Bitmap? {

        if (isCancelled()) {
            return null
        }
        else {
            return getBitMapFromUrl(urls[0])
        }
    }

    /** This method called after the doINputBackground method
     * input:Bitmap image
     * output: image set into the image view
     * Image view  passed from RecyclerViewOperation to ShowImage class through constructor
     */
    override fun onPostExecute(result: Bitmap?) {
        if (imageview != null && result != null) {
            val imgview = imageview!!.get()


            if (imgview != null) {

                imgview!!.setImageBitmap(result)
            }
        }
    }

    /** This method called by doInBackground method
     * input:url
     * output: Bitmap image
     *
     */
    private fun getBitMapFromUrl(imageuri: String): Bitmap? {

        try {
            val url = URL(imageuri)
            Log.d("bucky","bitmap" + imageuri);
            val connection = url.openConnection() as HttpURLConnection

            connection.setDoInput(true)
            connection.connect()
            val `is` = connection.getInputStream()

            return BitmapFactory.decodeStream(`is`)


        } catch (e: MalformedURLException) {
            e.printStackTrace()
            return null
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
    }

}