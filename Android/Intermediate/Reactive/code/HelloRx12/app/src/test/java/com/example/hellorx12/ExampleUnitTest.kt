package com.example.hellorx12

import io.reactivex.Observable
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun observable_isCorrect() {
        val observableTest = Observable.fromArray(0, 1, 2, 3, 4)
            .filter {
                it % 2 == 0
            }
            .test()
        observableTest.assertValueCount(3)
        observableTest.assertResult(0, 2, 4)
        observableTest.assertValues(0, 2, 4)
        observableTest.assertComplete()
    }

    @Test
    fun observable_isCaughtError() {
        val observableTest = Observable.create<Int> {
                it.onNext(1)
                it.onNext(2)
                it.onError(Exception("Error thrown"))
                it.onNext(4)
                it.onComplete()
            }
            .test()
        observableTest.assertValueCount(2)
        observableTest.assertError(Exception::class.java)
    }
}
