package com.example.hellonetworking8

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlin.concurrent.thread


// https://github.com/square/okhttp/blob/master/samples/guide/src/main/java/okhttp3/recipes/Progress.java
// https://github.com/jobinlawrance/okhttp3-downloadprogress-interceptor
class MainActivity : AppCompatActivity() {

    private var imageView: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.imageView)
        downloadImageWithProgress()
    }

    fun downloadImageWithProgress() {
        val imageUrl = "https://images.unsplash.com/photo-1573108037329-37aa135a142e?auto=format&fit=crop&w=800"
        val request = Request.Builder()
            .url(imageUrl)
            .build()

        val client = OkHttpClient.Builder()
            .addNetworkInterceptor(DownloadProgressInterceptor())
            .build()

        thread {
            client.newCall(request).execute().use { response ->
                val imageBytes = response.body!!.byteStream()
                val image = BitmapFactory.decodeStream(imageBytes)
                runOnUiThread {
                    imageView!!.setImageBitmap(image)
                }
            }
        }
    }
}
