package com.example.hellodi3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.parameter.parametersOf


const val LOG = "hellodi-log"

class MainActivity : AppCompatActivity() {

    val helloRepository : HelloRepositoryImpl by inject { parametersOf("Bitcoin") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startKoin {
            androidLogger()
            androidContext(this@MainActivity)
            modules(appModule)
        }

        Log.d(LOG, helloRepository.giveHello())

        val helloRepositoryEager : HelloRepository2Impl = get { parametersOf("Ethereum") }
        Log.d(LOG, helloRepositoryEager.giveHello())
    }
}
