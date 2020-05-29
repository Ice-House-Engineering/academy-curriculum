package com.example.helloasynchronous5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager


const val LOG = "android-concurrency"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button).setOnClickListener {
            callWorker()
        }
    }

    fun callWorker() {
        val simpleWorker1 = OneTimeWorkRequestBuilder<SimpleWorker1>().build()
        val simpleWorker2 = OneTimeWorkRequestBuilder<SimpleWorker2>().build()
        val simpleWorker3 = OneTimeWorkRequestBuilder<SimpleWorker3>().build()
        WorkManager.getInstance(applicationContext)
            .beginWith(simpleWorker1)
            .then(listOf(simpleWorker2, simpleWorker3))
            .enqueue()
    }
}
