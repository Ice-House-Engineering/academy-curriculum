package com.example.helloconcurrency6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


const val LOG = "android-coroutine"

class MainActivity : AppCompatActivity(), CoroutineScope {

    private val job: Job = Job()

    private val handler = CoroutineExceptionHandler {c, e ->
        Log.d(LOG, "Inside CoroutineExceptionHandler, catching an exception $e in $c")
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job + handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button).setOnClickListener {
            launch {
                throw Exception("Error in coroutine")
            }
        }
    }
}
