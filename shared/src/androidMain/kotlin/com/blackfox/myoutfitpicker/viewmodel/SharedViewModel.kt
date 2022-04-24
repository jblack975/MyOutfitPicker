package com.blackfox.myoutfitpicker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blackfox.myoutfitpicker.ClothingWeatherModel
import com.blackfox.myoutfitpicker.CurrentForecast
import com.blackfox.myoutfitpicker.MonthlyForecast
import com.blackfox.myoutfitpicker.repository.WeatherRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent

@Suppress("EmptyDefaultConstructor")
actual open class SharedViewModel actual constructor() : ViewModel() {
    protected actual val weatherRepository: WeatherRepository by KoinJavaComponent.inject(
        WeatherRepository::class.java
    )
    actual val sharedScope: CoroutineScope = viewModelScope
    actual var city:String = ""

    public actual override fun onCleared() {
        super.onCleared()
    }

    actual fun sendAnonymousData(data: ClothingWeatherModel) {
        sharedScope.launch {
            weatherRepository.saveAnonymousData(data)
        }
    }

    actual suspend fun retrieveMonthlyForecastByCity(city: String): MonthlyForecast? {
        return weatherRepository.retrieveMonthlyForecastByCity(city)
    }

    actual suspend fun retrieveCurrentWeatherByCity(city: String): CurrentForecast? {
        return weatherRepository.retrieveCurrentWeatherByCity(city)
    }
}