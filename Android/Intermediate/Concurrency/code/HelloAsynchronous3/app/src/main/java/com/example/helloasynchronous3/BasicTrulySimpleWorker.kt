package com.example.helloasynchronous3

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters


const val LOG = "android-concurrency"

class BasicTrulySimpleWorker(appContext: Context, workerParams: WorkerParameters): Worker(appContext, workerParams) {

    companion object {
        var times = 0
    }

    override fun doWork(): Result {
        val inputData = inputData.getString("key")!!

        return if (times == 2) {
            doingSomethingIntense(inputData)
            Result.success()
        } else {
            times++
            Log.d(LOG, "Retry for: $times times")
            Result.retry()
        }
    }

    fun doingSomethingIntense(inputData: String) {
        Thread.sleep(1000)
        Log.d(LOG, "Doing something intense with: ${inputData}")
    }
}