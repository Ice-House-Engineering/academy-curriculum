package com.example.hellomvvm9.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val appModule = module {

    single(named("RETROFIT")) { Retrofit.Builder()
        .baseUrl("http://10.0.2.2:5000")
        .addConverterFactory(GsonConverterFactory.create())
        .build() }

}