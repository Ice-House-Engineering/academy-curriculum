package com.example.hellodi1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


const val LOG = "hellodi-log"

// Code is taken and adapted from https://start.insert-koin.io/#/quickstart/android
class MainActivity : AppCompatActivity() {

    val mySimplePresenter : MySimplePresenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startKoin {
            androidLogger()
            androidContext(this@MainActivity)
            modules(appModule)
        }

        Log.d(LOG, mySimplePresenter.sayHello())

        val mySimplePresenterEager : MySimplePresenter = get()
        Log.d(LOG, mySimplePresenterEager.sayHello())
    }
}
