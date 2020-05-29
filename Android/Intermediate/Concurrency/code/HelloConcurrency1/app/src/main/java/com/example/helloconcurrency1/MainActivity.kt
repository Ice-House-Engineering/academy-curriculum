package com.example.helloconcurrency1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*


const val LOG = "android-coroutines"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        runBlocking {
            GlobalScope.launch {
                Log.d(LOG, "Inside launch method of GlobalScope")
                delay(1000)
                Log.d(LOG, "Still inside launch method of GlobalScope")
            }
            Log.d(LOG, "Outside launch method of GlobalScope")
        }

        runBlocking {
            val lazyJob = GlobalScope.launch(start = CoroutineStart.LAZY) {
                Log.d(LOG, "Lazy job. It must be started manually.")
            }
            val parentJob = GlobalScope.launch(start = CoroutineStart.LAZY) {
                Log.d(LOG, "Parent job. It will be started via child job.")
            }
            val childJob = GlobalScope.launch {
                parentJob.join()
                Log.d(LOG, "The child of parentJob. It will start the parent job first.")
            } // We wait this job to be finished first before going to the next line
            lazyJob.start() // Starting lazyJob
            childJob.join()
        }

        runBlocking {
            launch {
                delay(1000)
                Log.d(LOG, "Inside runBlocking with launch")
            }
        }

        Log.d(LOG, "Outside runBlocking with launch")

        runBlocking {
            GlobalScope.launch {
                delay(1000)
                Log.d(LOG, "Inside runBlocking with GlobalScope.launch")
            }
        }

        Log.d(LOG, "Outside runBlocking with GlobalScope.launch")

        runBlocking {
            launch {
                Log.d(LOG, "The name of the thread with launch is ${Thread.currentThread().name}")
            }
            GlobalScope.launch(Dispatchers.Main) {
                Log.d(LOG, "The name of the thread with GlobalScope.launch(Dispatchers.Main) is ${Thread.currentThread().name}")
            }
            launch(Dispatchers.IO) {
                Log.d(LOG, "The name of the thread with launch(Dispatchers.IO) is ${Thread.currentThread().name}")
            }
            launch(Dispatchers.Unconfined) {
                Log.d(LOG, "The name of the thread with launch(Dispatchers.Unconfined) is ${Thread.currentThread().name}")
            }
            launch(Dispatchers.Default) {
                Log.d(LOG, "The name of the thread with launch(Dispatchers.Default) is ${Thread.currentThread().name}")
            }
        }

    }
}
