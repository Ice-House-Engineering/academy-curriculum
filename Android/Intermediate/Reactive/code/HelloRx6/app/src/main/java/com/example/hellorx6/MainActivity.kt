package com.example.hellorx6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.toObservable
import io.reactivex.rxkotlin.zipWith
import io.reactivex.subjects.PublishSubject


const val LOG = "rxkotlin-"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playWithStart()
        playWithMerge()
        playWithZip()
        playWithCombineLatest()
    }

    fun playWithStart() {
        val sub = (1..4)
            .toObservable()
            .startWith(-90)
            .subscribe {
                Log.d(LOG + "-startWith", it.toString())
            }
        sub.dispose()
    }

    fun playWithMerge() {
        val sub = (1..4)
            .toObservable()
            .mergeWith((5..8).toObservable())
            .subscribe {
                Log.d(LOG + "-mergeWith", it.toString())
            }
        sub.dispose()
    }

    fun playWithZip() {
        val sub = arrayOf("Joko", "Budi", "Susi")
            .toObservable()
            .zipWith(arrayOf("Widodo", "Gunawan", "Susanti").toObservable()) { first, second ->
                first + " " + second
            }
            .subscribe {
                Log.d(LOG + "-zipWith", it.toString())
            }
        sub.dispose()
    }

    fun playWithCombineLatest() {
        val publishSubjectCryptocurrency = PublishSubject.create<String>()
        val publishSubjectMarket = PublishSubject.create<String>()

        val sub = Observables.combineLatest(publishSubjectCryptocurrency, publishSubjectMarket) { p1, p2 ->
            p1 + " " + p2
        }.subscribe {
            Log.d(LOG + "-combineLatest", it.toString())
        }
        publishSubjectCryptocurrency.onNext("Bitcoin")
        publishSubjectMarket.onNext("Bear")
        publishSubjectMarket.onNext("Bear")
        publishSubjectMarket.onNext("Bull")
        publishSubjectCryptocurrency.onNext("Ethereum")
        publishSubjectCryptocurrency.onNext("Litecoin")
        publishSubjectMarket.onNext("Bear")
        sub.dispose()
    }
}
