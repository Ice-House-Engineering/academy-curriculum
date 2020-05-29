package com.example.helloasynchronous5

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters


class SimpleWorker2(appContext: Context, workerParams: WorkerParameters): CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        doingSomethingIntense()

        return Result.success()
    }

    fun doingSomethingIntense() {
        Thread.sleep(1000)
        Log.d(LOG, "Doing something intense in SimpleWorker2")
    }
}