package com.example.hellonetworking3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import okhttp3.*
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import java.io.ByteArrayOutputStream
import kotlin.concurrent.thread


const val LOG = "android-networking"

// https://stackoverflow.com/questions/45940861/android-8-cleartext-http-traffic-not-permitted
// Code for server: Common/Backend/code/HelloBackend3
class MainActivity : AppCompatActivity() {

    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        uploadImage()
    }

    fun uploadImage() {
        val bitmapImage = BitmapFactory.decodeResource(applicationContext.resources, R.drawable.tree)
        val stream = ByteArrayOutputStream()
        bitmapImage.compress(Bitmap.CompressFormat.JPEG, 90, stream)
        val image = stream.toByteArray()

        val header = Headers.headersOf("Content-Disposition", "form-data; name=\"file\"; filename=\"tree.jpg\"")
        val jpgMediaType = "image/jpg".toMediaTypeOrNull()
        val imageRequestBody = image.toRequestBody(jpgMediaType)
        val requestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addPart(header, imageRequestBody)
            .build()

        val request = Request.Builder()
            .url("http://10.0.2.2:5000/upload_image")
            .post(requestBody)
            .build()

        thread {
            client.newCall(request).execute().use { response ->
                val string = response.body!!.string()
                Log.d(LOG, string)
            }
        }
    }
}
