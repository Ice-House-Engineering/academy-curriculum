package com.example.helloretrofit4

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity() {

    private var imageView: ImageView? = null

    // The server code is in Common/Backend/code/HelloBackend2
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:5000")
        .addConverterFactory(ScalarsConverterFactory.create())
        .build()

    private val imageApi = retrofit.create(ImageApi::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.imageView)

        downloadImage()
    }

    fun downloadImage() {
        val call = imageApi.getImage()
        thread {
            val imageBody = call.execute().body()
            val imageBytes = imageBody!!.byteStream()
            val image = BitmapFactory.decodeStream(imageBytes)
            runOnUiThread {
                imageView!!.setImageBitmap(image)
            }
        }
    }
}
