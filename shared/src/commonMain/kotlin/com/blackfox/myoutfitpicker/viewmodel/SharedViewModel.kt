package com.blackfox.myoutfitpicker.viewmodel

import com.blackfox.myoutfitpicker.ClothingWeatherModel
import com.blackfox.myoutfitpicker.repository.WeatherRepository
import kotlinx.coroutines.CoroutineScope

@Suppress("EmptyDefaultConstructor")
expect open class SharedViewModel() {
    val sharedScope: CoroutineScope
    protected val weatherRepository: WeatherRepository

    var city: String

    open fun onCleared()

    suspend fun sendAnonymousData(data:ClothingWeatherModel)
}
