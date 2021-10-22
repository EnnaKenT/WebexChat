package com.example.webexchat.ui.login

import android.annotation.TargetApi
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient

class CustomWebViewClient(private val listener: (code: String) -> Unit) : WebViewClient() {

    @TargetApi(Build.VERSION_CODES.N)
    override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
        view.loadUrl(request.url.toString())
        return true
    }

    // for old device
    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
        view.loadUrl(url)
        return true
    }

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)
        Log.d(TAG, "onPageStarted $url")
        if (url != null) {

            val uri = Uri.parse(url)
            val server = uri.authority
            val path = uri.path
            val protocol = uri.scheme
            val args: Set<String> = uri.queryParameterNames

            if (url.startsWith("https://webexdemoapp.com/?code=")) {
                val code = uri.getQueryParameter("code")
                code?.let { listener.invoke(it) }
            } else {
                val error = uri.getQueryParameter("error")
            }
        }
    }

    companion object {
        private const val TAG = "CustomWebView"
    }
}