package com.example.helloconcurrency2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking


const val LOG = "android-coroutines"

class MainActivity : AppCompatActivity() {

    suspend fun downloadFile(): String {
        delay(1000)
        return "Downloaded File"
    }

    suspend fun readDataFromDatabase(): String {
        delay(1000)
        return "Data From Database"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        runBlocking {
            Log.d(LOG, "Before executing methods")
            val result1 = downloadFile()
            val result2 = readDataFromDatabase()
            Log.d(LOG, "After executing methods, getting result: ${result1 + result2}")
        }

        runBlocking {
            Log.d(LOG, "Before async")
            val deferredDownload = async { downloadFile() }
            val deferredDatabase = async { readDataFromDatabase() }

            val result1 = deferredDownload.await()
            val result2 = deferredDatabase.await()
            Log.d(LOG, "After async's await, getting result: ${result1 + result2}")
        }
    }
}
