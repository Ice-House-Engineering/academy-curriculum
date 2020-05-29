package com.example.hellonetworking8

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException


const val LOG = "android-networking"

class DownloadProgressInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalResponse = chain.proceed(chain.request())
        val responseBuilder = originalResponse.newBuilder()

        val downloadProgressListener = object: DownloadProgressListener {
            override fun update(bytesRead: Long, contentLength: Long, done: Boolean) {
                if (done) {
                    Log.d(LOG, "Complete downloading item")
                } else {
                    Log.d(LOG, "${(bytesRead * 100) / contentLength}% done")
                }
            }
        }

        val downloadProgressBody = DownloadProgressResponseBody(originalResponse.body!!, downloadProgressListener)

        responseBuilder.body(downloadProgressBody)

        return responseBuilder.build()
    }
}