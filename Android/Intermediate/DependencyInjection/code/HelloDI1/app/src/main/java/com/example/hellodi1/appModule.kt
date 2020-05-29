package com.example.hellodi1

import org.koin.dsl.module


val appModule = module {

    single<HelloRepository> { HelloRepositoryImpl() }

    factory { MySimplePresenter(get()) }
}

