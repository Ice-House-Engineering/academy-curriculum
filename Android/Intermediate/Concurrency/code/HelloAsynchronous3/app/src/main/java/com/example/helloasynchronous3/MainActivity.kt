package com.example.helloasynchronous3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.work.*
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button).setOnClickListener {
            callWorker()
        }
    }

    fun callWorker() {
        val constraints = Constraints.Builder()
            .setRequiresDeviceIdle(false)
            .setRequiresCharging(false)
            .setRequiresBatteryNotLow(true)
            .setRequiresStorageNotLow(true)
            .setRequiredNetworkType(NetworkType.NOT_ROAMING)
            .build()

        val inputData = workDataOf("key" to "value")

        val basicTrulySimpleWorker = OneTimeWorkRequestBuilder<BasicTrulySimpleWorker>()
            .setConstraints(constraints)
            .setInitialDelay(2, TimeUnit.SECONDS)
            .setBackoffCriteria(BackoffPolicy.LINEAR, 11, TimeUnit.SECONDS)
            .setInputData(inputData)
            .build()
        WorkManager.getInstance(applicationContext).enqueue(basicTrulySimpleWorker)
    }
}
