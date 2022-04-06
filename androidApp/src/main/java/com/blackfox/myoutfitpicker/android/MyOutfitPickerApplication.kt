package com.blackfox.myoutfitpicker.android

import android.app.Application
import com.blackfox.myoutfitpicker.appContext
import com.blackfox.myoutfitpicker.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class MyOutfitPickerApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        appContext = this

        initKoin() {
            // https://github.com/InsertKoinIO/koin/issues/1188
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@MyOutfitPickerApplication)
            modules(appModule)
        }
    }
}