package com.example.helloasynchronous2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
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
        val basicTrulySimpleWorker = OneTimeWorkRequestBuilder<BasicTrulySimpleWorker>().build()
        WorkManager.getInstance(applicationContext).enqueue(basicTrulySimpleWorker)

        val repeatBasicTrulySimpleWorker = PeriodicWorkRequestBuilder<BasicTrulySimpleWorker>(20, TimeUnit.MINUTES).build()
        WorkManager.getInstance(applicationContext).enqueue(repeatBasicTrulySimpleWorker)
    }
}
