package com.example.hellorx5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.rxkotlin.toObservable
import io.reactivex.subjects.PublishSubject


const val LOG = "rxkotlin-"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playWithMap()
        playWithScan()
        playWithBuffer()
        playWithFlatMap()
        playWithSwitchMap()
    }

    fun playWithMap() {
        val sub = (1..4)
            .toObservable()
            .map {
                it * 3
            }
            .subscribe {
                Log.d(LOG + "-map", it.toString())
            }
        sub.dispose()
    }

    fun playWithScan() {
        val sub = arrayOf(1, 1, 2, 3, 5)
            .toObservable()
            .scan { t1: Int, t2: Int -> t1 + t2 }
            .subscribe {
                Log.d(LOG + "-scan", it.toString())
            }
        sub.dispose()
    }

    fun playWithBuffer() {
        val sub = (1..5)
            .toObservable()
            .buffer(3, 1)
            .subscribe {
                Log.d(LOG + "-buffer", it.toString())
            }
        sub.dispose()
    }

    fun playWithFlatMap() {
        val publishSubject = PublishSubject.create<PublishSubject<Int>>()
        val sub = publishSubject
            .flatMap {
                it
            }
            .subscribe {
                Log.d(LOG + "-flatMap", it.toString())
            }
        val bitcoin = PublishSubject.create<Int>()
        val ethereum = PublishSubject.create<Int>()
        publishSubject.onNext(bitcoin)
        publishSubject.onNext(ethereum)
        bitcoin.onNext(9000)
        ethereum.onNext(200)
        bitcoin.onNext(10000)
        ethereum.onNext(140)
        sub.dispose()
    }

    fun playWithSwitchMap() {
        val publishSubject = PublishSubject.create<PublishSubject<Int>>()
        val sub = publishSubject
            .switchMap {
                it
            }
            .subscribe {
                Log.d(LOG + "-switchMap", it.toString())
            }
        val bitcoin = PublishSubject.create<Int>()
        val ethereum = PublishSubject.create<Int>()
        publishSubject.onNext(bitcoin)
        bitcoin.onNext(9000)
        publishSubject.onNext(ethereum)
        ethereum.onNext(200)
        bitcoin.onNext(10000)
        ethereum.onNext(140)
        sub.dispose()
    }
}
