package com.example.hellodi4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named


const val LOG = "hellodi-log"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startKoin {
            androidLogger()
            androidContext(this@MainActivity)
            modules(appModule)
        }

        val cryptocurrency : String = get(named("CRYPTOCURRENCY"))
        Log.d(LOG, cryptocurrency)
    }
}
