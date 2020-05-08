package com.example.hellodi3

import org.koin.dsl.module


val appModule = module {

    single { (string: String) -> HelloRepositoryImpl(string) }

    single { (string: String) -> HelloRepository2Impl(string) }

}