package com.example.helloasynchronous4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager


class MainActivity : AppCompatActivity() {

    val basicTrulySimpleWorker = OneTimeWorkRequestBuilder<BasicTrulySimpleWorker>().build()

    lateinit var workManager: WorkManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        workManager = WorkManager.getInstance(applicationContext)
        workManager.getWorkInfoByIdLiveData(basicTrulySimpleWorker.id)
            .observe(this, Observer {
                if (it != null) {
                    Log.d(LOG, "Lifecycle of WorkManager: ${it.state}")
                }
            })

        findViewById<Button>(R.id.startWorkManager).setOnClickListener {
            callWorker()
        }

        findViewById<Button>(R.id.cancelWorkManager).setOnClickListener {
            cancelWorker()
        }
    }

    fun callWorker() {
        workManager.enqueue(basicTrulySimpleWorker)
    }

    fun cancelWorker() {
        workManager.cancelWorkById(basicTrulySimpleWorker.id)
    }
}
