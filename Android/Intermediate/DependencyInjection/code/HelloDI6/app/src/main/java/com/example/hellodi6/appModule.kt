package com.example.hellodi6

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {

    viewModel { HelloViewModel() }
}