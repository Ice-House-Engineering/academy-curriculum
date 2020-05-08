package com.example.helloretrofit6

interface DownloadProgressListener {
    fun update(bytesRead: Long, contentLength: Long, done: Boolean)
}