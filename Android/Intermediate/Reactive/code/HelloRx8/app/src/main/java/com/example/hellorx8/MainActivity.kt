package com.example.hellorx8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy


const val LOG = "rxkotlin-"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playWithDoOnNext()
        playWithOnErrorReturn()
        playWithRetry()
    }

    fun playWithDoOnNext() {
        val sub = Observable.create<Int> {
                it.onNext(1)
                it.onError(Exception("Exception thrown"))
                it.onNext(3)
                it.onComplete()
            }
            .doOnNext {
                Log.d(LOG + "-doonnext-next", it.toString())
            }
            .doOnError {
                Log.d(LOG + "-doonnext-err", it.toString())
            }
            .subscribeBy(
                onNext= { Log.d(LOG + "-doonnext-subs", it.toString()) },
                onError= { Log.d(LOG + "-doonnext-subs", it.toString()) }
            )
        sub.dispose()
    }

    fun playWithOnErrorReturn() {
        val sub = Observable.create<Int> {
                it.onNext(1)
                it.onError(Exception("Exception thrown"))
                it.onNext(3)
                it.onComplete()
            }
            .onErrorReturn { -99 }
            .subscribeBy(
                onNext = { Log.d(LOG + "-error-return", it.toString()) },
                onComplete = { Log.d(LOG + "-error-return", "Complete") }
            )
        sub.dispose()
    }

    fun playWithRetry() {
        var limit = 0
        val sub = Observable.create<Int> {
                it.onNext(1)
                it.onNext(2)
                if (limit < 1) {
                    it.onError(Exception("Exception thrown"))
                    limit++
                }
                it.onNext(18)
                it.onNext(19)
            }
            .retry(5)
            .subscribeBy(
                onNext = { Log.d(LOG + "-retry", it.toString()) },
                onComplete = { Log.d(LOG + "-retry", "Complete") }
            )
        sub.dispose()
    }
}
