package com.blackfox.myoutfitpicker.viewmodel

import com.blackfox.myoutfitpicker.ClothingWeatherModel
import com.blackfox.myoutfitpicker.CurrentForecast
import com.blackfox.myoutfitpicker.MonthlyForecast
import com.blackfox.myoutfitpicker.repository.WeatherRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import platform.darwin.dispatch_async
import platform.darwin.dispatch_get_main_queue
import kotlin.native.internal.GC

@Suppress("EmptyDefaultConstructor")
actual open class SharedViewModel actual constructor() {
    protected actual val weatherRepository: WeatherRepository = WeatherRepository()

    actual val sharedScope: CoroutineScope = createViewModelScope()

    actual var city:String = ""

    actual open fun onCleared() {
        sharedScope.cancel()

        dispatch_async(dispatch_get_main_queue()) { GC.collect() }
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

@ThreadLocal
private var createViewModelScope: () -> CoroutineScope = {
    CoroutineScope(createUIDispatcher())
}

private fun createUIDispatcher(): CoroutineDispatcher = UIDispatcher()
