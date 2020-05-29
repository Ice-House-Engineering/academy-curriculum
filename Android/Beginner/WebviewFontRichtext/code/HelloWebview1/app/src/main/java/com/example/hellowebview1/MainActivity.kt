package com.example.hellowebview1

import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val webViewInternet : WebView = findViewById(R.id.webViewInternet)
        webViewInternet.loadUrl("https://news.ycombinator.com")

        val webViewLocal1 : WebView = findViewById(R.id.webViewLocal1)
        val cssString = assets.open("html/style.css").bufferedReader().readText()
        webViewLocal1.loadDataWithBaseURL(null, "<html>" +
                "<head>" +
                "<style>" +
                cssString +
                "</style>" +
                "</head>" +
                "<body>" +
                "<p>This is p node <strong>with strong node</strong>" +
                "</body>" +
                "</html>",
            "text/html", "UTF-8",null)

        val webViewLocal2 : WebView = findViewById(R.id.webViewLocal2)
        webViewLocal2.loadUrl("file:///android_asset/html/index.html")
    }

}