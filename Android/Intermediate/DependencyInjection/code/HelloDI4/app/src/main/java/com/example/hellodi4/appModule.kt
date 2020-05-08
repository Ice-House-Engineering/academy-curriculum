package com.example.hellodi4

import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module


val appModule = module {

    single(named("CRYPTOCURRENCY")) { androidContext().resources.getString(R.string.cryptocurrency) }

}