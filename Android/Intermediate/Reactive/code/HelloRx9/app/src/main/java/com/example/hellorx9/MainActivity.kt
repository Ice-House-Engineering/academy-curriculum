package com.example.hellorx9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers


const val LOG = "rxkotlin-"

class MainActivity : AppCompatActivity() {

    private lateinit var textView : TextView
    private lateinit var compositeDisposable : CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)
        compositeDisposable = CompositeDisposable()

        playWithDifferentScheduler()
        playWithAndroidSchedulers()
    }

    fun playWithDifferentScheduler() {
        Observable.create<Int> {
                it.onNext(1)
                it.onNext(2)
                it.onNext(3)
                it.onNext(4)
                it.onComplete()
            }
            .subscribeOn(Schedulers.io())
            .filter {
                Log.d(LOG + "-io", "$it ${Thread.currentThread().name}")
                it % 2 == 1
            }
            .observeOn(Schedulers.computation())
            .subscribe {
                Log.d(LOG + "-computation", "$it ${Thread.currentThread().name}")
            }
            .addTo(compositeDisposable)
    }

    fun playWithAndroidSchedulers() {
        Observable.create<Int> {
                it.onNext(1)
                it.onNext(2)
                it.onNext(3)
                it.onNext(4)
                it.onComplete()
            }
            .subscribeOn(Schedulers.trampoline())
            .filter {
                Log.d(LOG + "-trampoline", "$it ${Thread.currentThread().name}")
                it % 2 == 0
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                textView.text = "${textView.text} $it"
                Log.d(LOG + "-mainthread", "$it ${Thread.currentThread().name}")
            }
            .addTo(compositeDisposable)
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }
}
