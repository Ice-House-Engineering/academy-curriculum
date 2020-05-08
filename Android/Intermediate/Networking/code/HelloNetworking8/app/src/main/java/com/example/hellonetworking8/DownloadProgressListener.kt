package com.example.hellonetworking8


interface DownloadProgressListener {
    fun update(bytesRead: Long, contentLength: Long, done: Boolean)
}