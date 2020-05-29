package com.example.helloasynchronous2

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters


const val LOG = "android-concurrency"

class BasicTrulySimpleWorker(appContext: Context, workerParams: WorkerParameters): Worker(appContext, workerParams) {

    override fun doWork(): Result {
        doingSomethingIntense()

        return Result.success()
    }

    fun doingSomethingIntense() {
        Thread.sleep(1000)
        Log.d(LOG, "Doing something intense")
    }
}