package com.example.hellorx7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy


const val LOG = "rxkotlin-"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playWithSingle()
        playWithSingleError()
        playWithMaybe()
        playWithCompletable()
    }

    fun playWithSingle() {
        val sub = Single.create<Int> {
            it.onSuccess(3)
        }.subscribeBy {
            Log.d(LOG + "-single", it.toString())
        }
        sub.dispose()
    }

    fun playWithSingleError() {
        val sub = Single.create<Int> {
            it.onError(Exception("Exception occurred"))
        }.subscribeBy(onSuccess = {
            Log.d(LOG + "-singleerror", "This will never be printed")
        }, onError = {
            Log.d(LOG + "-singleerror", it.toString())
        })
        sub.dispose()
    }

    fun playWithMaybe() {
        val sub = Observable.create<Maybe<Int>> {
            it.onNext(Maybe.just(1))
            it.onNext(Maybe.empty())
            it.onNext(Maybe.just(2))
            it.onError(Exception("Exception 1"))
            it.onNext(Maybe.just(3))
        }.subscribeBy(onNext = {
            it.subscribe {
                Log.d(LOG + "-maybe", it.toString())
            }
        }, onError = {
            Log.d(LOG + "-maybe", it.toString())
        })
        sub.dispose()
    }

    fun playWithCompletable() {
        val sub = Completable.create {
            Log.d(LOG + "-completable", "Doing something")
            it.onComplete()
        }.subscribe {
            Log.d(LOG + "-completable", "The process is complete")
        }
        sub.dispose()
    }
}
