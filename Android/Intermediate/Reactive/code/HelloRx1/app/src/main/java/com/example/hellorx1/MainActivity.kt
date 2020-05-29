package com.example.hellorx1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.toObservable
import io.reactivex.subjects.AsyncSubject
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.ReplaySubject


const val LOG = "rxkotlin"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playWithSimpleCase()
        playWithCompositeDisposable()
        playWithSubscribe()
        playWithObservable()
        playWithCreate()

        playWithPublishSubject()
        playWithMultiplePublishSubject()
        playWithBehaviorSubject()
        playWithReplaySubject()
        playWithAsyncSubject()
    }

    fun playWithSimpleCase() {
        val sub = arrayOf(1, 2, 3).toObservable().subscribe {
            Log.d(LOG + "-simple", it.toString())
        }
        sub.dispose()
    }

    fun playWithCompositeDisposable() {
        val compositeDisposable = CompositeDisposable()
        arrayOf(1, 2, 3).toObservable().subscribe {
            Log.d(LOG + "-composite", it.toString())
        }.addTo(compositeDisposable)
        arrayOf(7, 8, 9).toObservable().subscribe {
            Log.d(LOG + "-composite", it.toString())
        }.addTo(compositeDisposable)
        compositeDisposable.dispose()
    }

    fun playWithSubscribe() {
        val sub = arrayOf(1, 2, 3).toObservable().subscribe(
            { Log.d(LOG + "-subscribe", it.toString()) },
            { Log.d(LOG + "-subscribe","Error") },
            { Log.d(LOG + "-subscribe", "Complete") })
        sub.dispose()

        val sub2 = arrayOf(1, 2, 3).toObservable().subscribeBy(
            onNext = { Log.d(LOG + "-subscribeBy", it.toString()) },
            onError = { Log.d(LOG + "-subscribeBy","Error") },
            onComplete = { Log.d(LOG + "-subscribeBy", "Complete") }
        )
        sub2.dispose()
    }

    fun playWithObservable() {
        val sub = Observable.fromArray(arrayOf(1, 2, 3)).subscribe {
            Log.d(LOG + "-toObservable", it.joinToString())
        }
        sub.dispose()

        val sub2 = Observable.just(1, 2, 3).subscribe {
            Log.d(LOG + "-just", it.toString())
        }
        sub2.dispose()

        val sub3 = Observable.fromIterable(listOf(1, 2, 3)).subscribe {
            Log.d(LOG + "-fromIterable", it.toString())
        }
        sub3.dispose()
    }

    fun playWithCreate() {
        val sub = Observable.create<Int> {
            it.onNext(1)
            it.onNext(2)
            it.onNext(3)
            it.onComplete()
        }.subscribeBy(
            onNext = { Log.d(LOG + "-create", it.toString()) },
            onComplete = { Log.d(LOG + "-create", "Complete") }
        )
        sub.dispose()
    }

    fun playWithPublishSubject() {
        val publishSubject = PublishSubject.create<Int>()
        val sub = publishSubject.subscribe {
            Log.d(LOG + "-PubSub-simple", it.toString())
        }
        publishSubject.onNext(1)
        publishSubject.onNext(2)
        publishSubject.onNext(3)
        publishSubject.onComplete()
        sub.dispose()
    }

    fun playWithMultiplePublishSubject() {
        val publishSubject = PublishSubject.create<Int>()
        val sub = publishSubject.subscribe {
            Log.d(LOG + "-PubSub1", it.toString())
        }
        publishSubject.onNext(1)
        val sub2 = publishSubject.subscribe {
            Log.d(LOG + "-PubSub2", it.toString())
        }
        publishSubject.onNext(2)
        publishSubject.onNext(3)
        publishSubject.onComplete()
        sub.dispose()
        sub2.dispose()
    }

    fun playWithBehaviorSubject() {
        val behaviorSubject = BehaviorSubject.createDefault(0)
        val sub = behaviorSubject.subscribe {
            Log.d(LOG + "-BehSub1", it.toString())
        }
        behaviorSubject.onNext(1)
        behaviorSubject.onNext(2)
        val sub2 = behaviorSubject.subscribe {
            Log.d(LOG + "-BehSub2", it.toString())
        }
        behaviorSubject.onNext(3)
        behaviorSubject.onComplete()
        sub.dispose()
        sub2.dispose()
    }

    fun playWithReplaySubject() {
        val replaySubject = ReplaySubject.createWithSize<Int>(2)
        val sub = replaySubject.subscribe {
            Log.d(LOG + "-RepSub1", it.toString())
        }
        replaySubject.onNext(1)
        replaySubject.onNext(2)
        replaySubject.onNext(3)
        val sub2 = replaySubject.subscribe {
            Log.d(LOG + "-RepSub2", it.toString())
        }
        replaySubject.onNext(4)
        replaySubject.onComplete()
        sub.dispose()
        sub2.dispose()
    }

    fun playWithAsyncSubject() {
        val asyncSubject = AsyncSubject.create<Int>()
        val sub = asyncSubject.subscribe {
            Log.d(LOG + "-AsySub1", it.toString())
        }
        asyncSubject.onNext(1)
        asyncSubject.onNext(2)
        val sub2 = asyncSubject.subscribe {
            Log.d(LOG + "-AsySub2", it.toString())
        }
        asyncSubject.onNext(3)
        asyncSubject.onComplete()
        sub.dispose()
        sub2.dispose()
    }
}
