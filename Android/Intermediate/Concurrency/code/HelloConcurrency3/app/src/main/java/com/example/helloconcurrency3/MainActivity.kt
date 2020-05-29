package com.example.helloconcurrency3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*


const val LOG = "android-coroutines"

class MainActivity : AppCompatActivity() {

    suspend fun problematicMethod() {
        delay(500)
        throw Exception("Error inside problematicMethod")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        runBlocking {
            launch {
                try {
                    problematicMethod()
                } catch (e: Exception) {
                    Log.d(LOG, "Catching an exception from launch method: $e")
                }
            }
        }

        runBlocking {
            async {
                try {
                    problematicMethod()
                } catch (e: Exception) {
                    Log.d(LOG, "Catching an exception from async method: $e")
                }
            }
        }

        runBlocking {
            val deferredMethod = GlobalScope.async { problematicMethod() }
            deferredMethod.join()
            if (deferredMethod.isCancelled) {
                Log.d(LOG, "There is an exception")
            }
        }

        runBlocking {
            val deferredMethod = GlobalScope.async { problematicMethod() }
            try {
                deferredMethod.await()
            } catch (e: Exception) {
                Log.d(LOG, "Catching an exception from async's await: $e")
            }
        }

        val handler = CoroutineExceptionHandler {c, e ->
            Log.d(LOG, "Inside CoroutineExceptionHandler, catching an exception $e in $c")
        }

        runBlocking {
            val deferredMethod = GlobalScope.launch(handler) { problematicMethod() }
            deferredMethod.join()
        }
    }
}
