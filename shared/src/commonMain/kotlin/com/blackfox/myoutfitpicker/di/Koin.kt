package com.blackfox.myoutfitpicker.di

import com.blackfox.myoutfitpicker.MyOutfitPickerViewModel
import com.blackfox.myoutfitpicker.OutfitPickerStore
import com.blackfox.myoutfitpicker.remote.WeatherApi
import com.blackfox.myoutfitpicker.repository.WeatherRepository
import com.blackfox.myoutfitpicker.viewmodel.SharedViewModel
import io.ktor.client.*
import io.ktor.client.plugins.*
//import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(commonModule())
    }

// called by iOS etc
fun initKoin() = initKoin {}

fun commonModule() = module {
    single { createHttpClient() }
    single { WeatherApi(get()) }
    single { WeatherRepository() }
    single { SharedViewModel()}
    single { OutfitPickerStore() }
    single { MyOutfitPickerViewModel() }
}

fun createHttpClient() = HttpClient {
    /*
    install(ContentNegotiation) {
        json(Json { isLenient = true; ignoreUnknownKeys = true; useAlternativeNames = false; prettyPrint = true }
        )
    }

     */
    install(Logging) {
        logger = Logger.DEFAULT
        level = LogLevel.INFO
    }
}
