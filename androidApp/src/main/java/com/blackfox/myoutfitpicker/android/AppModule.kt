package com.blackfox.myoutfitpicker.android

import co.touchlab.kermit.LogcatWriter
import com.blackfox.myoutfitpicker.viewmodel.SharedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { SharedViewModel() }
    single { LogcatWriter() }
}