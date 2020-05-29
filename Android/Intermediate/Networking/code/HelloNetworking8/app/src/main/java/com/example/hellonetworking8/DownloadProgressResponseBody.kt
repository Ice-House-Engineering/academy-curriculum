package com.example.hellonetworking8

import okhttp3.MediaType
import okhttp3.ResponseBody
import okio.*
import java.io.IOException


class DownloadProgressResponseBody(val responseBody: ResponseBody, val downloadProgressListener: DownloadProgressListener): ResponseBody() {

    private var bufferedSource: BufferedSource? = null

    override fun contentLength(): Long = responseBody.contentLength()

    override fun contentType(): MediaType? = responseBody.contentType()

    override fun source(): BufferedSource {
        if (bufferedSource == null)
            bufferedSource = getforwardSource(responseBody.source()).buffer()
        return bufferedSource!!
    }

    private fun getforwardSource(source: Source): Source =
        object : ForwardingSource(source) {
            var totalBytesRead = 0L

            @Throws(IOException::class)
            override fun read(sink: Buffer, byteCount: Long): Long {
                val bytesRead = super.read(sink, byteCount)

                totalBytesRead += if (bytesRead != -1L) bytesRead else 0
                downloadProgressListener.update(totalBytesRead, responseBody.contentLength(), bytesRead == -1L)
                return bytesRead
            }
        }
}