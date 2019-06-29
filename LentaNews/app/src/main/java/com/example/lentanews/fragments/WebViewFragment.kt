package com.example.lentanews.fragments

import android.graphics.Bitmap
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.webkit.WebSettings
import android.webkit.WebView
import com.example.lentanews.R
import android.widget.ProgressBar
import android.webkit.WebChromeClient
import android.support.v7.app.AlertDialog


class WebViewFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

//        val builder = AlertDialog.Builder(context!!)
//        builder.setView(com.example.lentanews.R.layout.progress_dialog)
//        builder.setCancelable(true);
//        val dialog = builder.create()
//        dialog.show()

        val link : String = this.arguments!!.getString("link")

        val view = inflater.inflate(R.layout.fragment_web, container,false)
        val mWebView = view.findViewById(R.id.webview) as WebView

        mWebView.webViewClient = object : WebViewClient() {

            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
                //view.visibility = View.INVISIBLE
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
                //dialog.dismiss()
                //view.visibility = View.VISIBLE
            }

        }
        mWebView.settings.javaScriptEnabled = true

        val settings = mWebView.settings
        settings.domStorageEnabled = true

        mWebView.loadUrl(link)
        return view
    }
}