package com.blackfox.myoutfitpicker.viewmodel

import com.blackfox.myoutfitpicker.ClothingWeatherModel
import com.blackfox.myoutfitpicker.CurrentForecast
import com.blackfox.myoutfitpicker.MonthlyForecast
import com.blackfox.myoutfitpicker.repository.WeatherRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import org.koin.java.KoinJavaComponent

actual open class SharedViewModel actual constructor() {
    protected actual val weatherRepository: WeatherRepository by KoinJavaComponent.inject(
        WeatherRepository::class.java
    )
    actual val sharedScope: CoroutineScope = GlobalScope
    actual var city: String = ""
    actual open fun onCleared() {
    }

    actual fun sendAnonymousData(data: ClothingWeatherModel) {
        sharedScope.launch {
            weatherRepository.saveAnonymousData(data)
        }
    }

    actual suspend fun retrieveMonthlyForecastByCity(city: String): com.blackfox.myoutfitpicker.MonthlyForecast? {
        return weatherRepository.retrieveMonthlyForecastByCity(city)
    }

    actual suspend fun retrieveCurrentWeatherByCity(city: String): com.blackfox.myoutfitpicker.CurrentForecast? {
        return weatherRepository.retrieveCurrentWeatherByCity(city)
    }
}