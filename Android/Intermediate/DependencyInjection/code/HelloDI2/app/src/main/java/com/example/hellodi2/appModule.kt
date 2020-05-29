package com.example.hellodi2

import org.koin.core.qualifier.named
import org.koin.dsl.module


val appModule = module {

    single<HelloRepository>(named("production")) { HelloRepositoryImpl() }

    single<HelloRepository>(named("test")) { HelloRepositoryTestImpl() }

    factory { MySimplePresenter(get(named("production"))) }

}