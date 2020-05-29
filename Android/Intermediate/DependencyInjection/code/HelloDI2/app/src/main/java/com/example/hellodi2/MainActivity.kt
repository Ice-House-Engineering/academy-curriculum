package com.example.hellodi2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named


const val LOG = "hellodi-log"

// Code is taken and adapted from https://start.insert-koin.io/#/quickstart/android
class MainActivity : AppCompatActivity() {

    val helloRepository : HelloRepository by inject(named("production"))
    val mySimplePresenter : MySimplePresenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startKoin {
            androidLogger()
            androidContext(this@MainActivity)
            modules(appModule)
        }

        Log.d(LOG, helloRepository.giveHello())
        Log.d(LOG, mySimplePresenter.sayHello())

        val helloRepositoryTest : HelloRepository = get(named("test"))
        Log.d(LOG, helloRepositoryTest.giveHello())
    }
}
