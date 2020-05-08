package com.example.hellorx3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.Observable
import io.reactivex.rxkotlin.toObservable
import java.util.concurrent.TimeUnit


const val LOG = "rxkotlin-"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playWithFilter()
        playWithDistinct()
        playWithElementAt()
        playWithSkip()
        playWithDebounce()
        playWithThrottle()
    }

    fun playWithFilter() {
        val sub = (1..10)
              .toObservable()
              .filter {
                it % 2 == 0
              }
              .subscribe {
            Log.d(LOG + "-filter", it.toString())
        }
        sub.dispose()
    }

    fun playWithDistinct() {
        val sub = arrayOf(2, 3, 3, 3, 4, 8, 11, 11, 11, 19)
            .toObservable()
            .distinct()
            .subscribe {
                Log.d(LOG + "-distinct", it.toString())
            }
        sub.dispose()
    }

    fun playWithElementAt() {
        val sub = arrayOf("John", "Budi", "Joko", "Susan", "Dewi")
            .toObservable()
            .elementAt(2)
            .subscribe {
                Log.d(LOG + "-elementAt", it.toString())
            }
        sub.dispose()
    }

    fun playWithSkip() {
        val sub = (1..10)
            .toObservable()
            .skip(7)
            .subscribe {
                Log.d(LOG + "-skip", it.toString())
            }
        sub.dispose()
    }

    fun playWithDebounce() {
        val sub = Observable.create<Int> {
                it.onNext(1)
                it.onNext(2)
                Thread.sleep(600)
                it.onNext(3)
                Thread.sleep(600)
                it.onNext(4)
                it.onNext(5)
                it.onNext(6)
                Thread.sleep(600)
            }
            .debounce(200, TimeUnit.MILLISECONDS)
            .subscribe {
                Log.d(LOG + "-debounce", it.toString())
            }
        sub.dispose()
    }

    fun playWithThrottle() {
        val sub = Observable.create<Int> {
                it.onNext(1)
                it.onNext(2)
                Thread.sleep(600)
                it.onNext(3)
                Thread.sleep(600)
                it.onNext(4)
                it.onNext(5)
                it.onNext(6)
                Thread.sleep(600)
            }
            .throttleFirst(1000, TimeUnit.MILLISECONDS)
            .subscribe {
                Log.d(LOG + "-throttle", it.toString())
            }
        sub.dispose()
    }
}
