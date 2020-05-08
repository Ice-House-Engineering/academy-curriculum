package com.example.hellorx16

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


const val LOG = "rxbinding"

class MainActivity : AppCompatActivity() {

    // The server code is in Common/Restful/code/HelloRestful1
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:5000")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val todoApi = retrofit.create(Todo::class.java)

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        downloadTodoList()
    }

    fun downloadTodoList() {
        val subscription = todoApi
                             .requestTodoList()
                             .subscribeOn(Schedulers.io())
                             .observeOn(Schedulers.trampoline())
                             .subscribe {
            Log.d(LOG, it.toString())
        }
        compositeDisposable.add(subscription)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

}
