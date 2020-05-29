package com.example.hellodi5

import org.koin.core.qualifier.named
import org.koin.dsl.module


val appModule = module {
    scope(named("Scope A")) {
        scoped { HelloRepositoryImpl() }
    }

    scope(named("Scope B")) {
        scoped { HelloRepositoryTestImpl() }
    }
}