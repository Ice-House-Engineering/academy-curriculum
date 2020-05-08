package com.example.helloconcurrency7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*
import kotlin.coroutines.coroutineContext


const val LOG = "android-coroutine"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val handler = CoroutineExceptionHandler {_, _ ->
            Log.d(LOG, "Catching exception inside CoroutineExceptionHandler")
        }

        Log.d(LOG, "=== Using Supervisor ===")
        runBlocking {
            val supervisor = SupervisorJob()
            val scope = CoroutineScope(coroutineContext + supervisor)
            val child1 = scope.launch(handler) {
                Log.d(LOG, "Child 1")
                throw Exception("Error in child 1")
            }
            val child2 = scope.launch {
                Log.d(LOG, "Child 2")
            }
            child1.join()
            child2.join()
        }

        Log.d(LOG, "=== Using Job ===")
        runBlocking {
            val job = Job()
            val scope = CoroutineScope(coroutineContext + job)
            val child1 = scope.launch(handler) {
                Log.d(LOG, "Child 1")
                throw Exception("Error in child 1")
            }
            val child2 = scope.launch {
                Log.d(LOG, "Child 2")
            }
            child1.join()
            child2.join()
        }
    }
}
