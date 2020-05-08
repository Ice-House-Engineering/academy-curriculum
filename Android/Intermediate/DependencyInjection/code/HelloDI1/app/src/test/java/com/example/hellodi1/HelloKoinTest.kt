package com.example.hellodi1

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject


class HelloKoinTest : KoinTest {

    val mySimplePresenter : MySimplePresenter by inject()

    val appModuleTesting = module {

        single<HelloRepository> { HelloRepositoryTestImpl() }

        factory { MySimplePresenter(get()) }
    }

    @Before
    fun setup() {
        startKoin {
            modules(appModuleTesting)
        }
    }

    @Test
    fun should_inject_components() {
        assert(mySimplePresenter.sayHello() == "Hello Koin for Testing from presenter")
    }

    @After
    fun tearDown() {
        stopKoin()
    }
}