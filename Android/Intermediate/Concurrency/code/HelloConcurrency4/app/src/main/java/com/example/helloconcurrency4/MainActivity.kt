package com.example.helloconcurrency4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*


const val LOG = "android-coroutines"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(LOG, "=== With cancelAndJoin")

        runBlocking {
            val job = launch {
                try {
                    repeat(10) {
                        Log.d(LOG, "Printing.... 1")
                        Log.d(LOG, "Printing.... 2")
                        Log.d(LOG, "Printing.... 3")
                        Log.d(LOG, "Printing.... 4")
                        delay(20)
                    }
                } finally {
                    Log.d(LOG, "Finally...")
                }
            }
            job.invokeOnCompletion {
                Log.d(LOG, "Executed after completion")
            }
            delay(50)
            Log.d(LOG, "Outside launch before 'cancel'")
            job.cancelAndJoin()
            Log.d(LOG, "Outside launch after 'cancel'")
        }

        Log.d(LOG, "=== With cancel")

        runBlocking {
            val job = launch {
                try {
                    repeat(10) {
                        Log.d(LOG, "Printing....")
                        delay(20)
                    }
                } finally {
                    Log.d(LOG, "Finally...")
                }
            }
            job.invokeOnCompletion {
                Log.d(LOG, "Executed after completion")
            }
            delay(50)
            Log.d(LOG, "Outside launch before 'cancel'")
            job.cancel()
            Log.d(LOG, "Outside launch after 'cancel'")
        }

        Log.d(LOG, "=== With isActive")

        runBlocking {
            val job = launch {
                try {
                    for (i in 1..10000) {
                        if (isActive) {
                            if (i % 100 == 0) {
                                Log.d(LOG, "Printing....")
                            }
                        }
                    }
                } finally {
                    Log.d(LOG, "Finally...")
                }
            }
            delay(500)
            Log.d(LOG, "Outside launch before 'cancel'")
            job.cancel()
            Log.d(LOG, "Outside launch after 'cancel'")
        }

        Log.d(LOG, "=== cancelAllChildren")

        runBlocking {
            val job = launch {
                launch {
                    repeat(10) {
                        Log.d(LOG, "Printing in child job....")
                        delay(50)
                    }
                }
                repeat(8) {
                    Log.d(LOG, "Printing....")
                    delay(50)
                }
            }
            delay(100)
            Log.d(LOG, "Outside launch before 'cancel'")
            job.cancelChildren()
            Log.d(LOG, "Outside launch after 'cancel'")
            job.join()
        }

        Log.d(LOG, "=== withTimeout")

        runBlocking {
            try {
                withTimeout(200) {
                    repeat(8) {
                        Log.d(LOG, "Printing....")
                        delay(50)
                    }
                }
            } catch (e: Exception) {
                Log.d(LOG, e.toString())
            }
        }

        Log.d(LOG, "=== withTimeoutOrNull returns Null")

        runBlocking {
            val result = withTimeoutOrNull(200) {
                repeat(8) {
                    Log.d(LOG, "Printing....")
                    delay(50)
                }
                "Finishing printing"
            }
            Log.d(LOG, result.toString())
        }

        Log.d(LOG, "=== withTimeoutOrNull returns result")

        runBlocking {
            val result = withTimeoutOrNull(200) {
                repeat(2) {
                    Log.d(LOG, "Printing....")
                    delay(50)
                }
                "Finishing printing"
            }
            Log.d(LOG, result.toString())
        }
    }
}
