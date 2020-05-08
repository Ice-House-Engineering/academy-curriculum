package com.example.hellorx10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.BackpressureStrategy
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject


const val LOG = "rxkotlin-"

class MainActivity : AppCompatActivity() {

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //playWithFlowableDrop()
        //playWithFlowableError()
        //playWithFlowableBuffer()
        playWithFlowableLatest()
    }

    fun playWithFlowableDrop() {
        val observable = PublishSubject.create<String>()
        observable
            .toFlowable(BackpressureStrategy.DROP)
            .onBackpressureDrop {
                Log.d(LOG + "drop-drop", it)
            }
            .observeOn(Schedulers.computation())
            .subscribeBy(
                onNext={ Log.d(LOG + "drop-next", it.toString())}
            ).addTo(compositeDisposable)
        (1..100000).forEach {
            observable.onNext("Event coming fast: $it")
        }
    }

    fun playWithFlowableError() {
        val observable = PublishSubject.create<String>()
        observable
            .toFlowable(BackpressureStrategy.ERROR)
            .observeOn(Schedulers.computation())
            .subscribeBy(
                onNext={ Log.d(LOG + "error-next", it.toString())},
                onError={ Log.d(LOG + "error-error", it.message)}
            ).addTo(compositeDisposable)
        (1..100000).forEach {
            observable.onNext("Event coming fast: $it")
        }
    }

    fun playWithFlowableBuffer() {
        val observable = PublishSubject.create<String>()
        observable
            .toFlowable(BackpressureStrategy.BUFFER)
            .onBackpressureBuffer(500)
            .observeOn(Schedulers.io())
            .subscribeBy(
                onNext={ Log.d(LOG + "buffer-next", it.toString())},
                onError={ Log.d(LOG + "buffer-error", it.message)}
            ).addTo(compositeDisposable)
        (1..100000).forEach {
            observable.onNext("Event coming fast: $it")
        }
    }

    fun playWithFlowableLatest() {
        val observable = PublishSubject.create<String>()
        observable
            .toFlowable(BackpressureStrategy.LATEST)
            .observeOn(Schedulers.io())
            .subscribeBy(
                onNext={ Log.d(LOG + "latest-next", it.toString())},
                onError={ Log.d(LOG + "latest-error", it.message)}
            ).addTo(compositeDisposable)
        (1..100000).forEach {
            observable.onNext("Event coming fast: $it")
        }
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }
}
