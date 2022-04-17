package com.blackfox.myoutfitpicker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blackfox.myoutfitpicker.ClothingWeatherModel
import com.blackfox.myoutfitpicker.repository.WeatherRepository
import kotlinx.coroutines.CoroutineScope
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

    actual suspend fun sendAnonymousData(data:ClothingWeatherModel) {
        weatherRepository.saveAnonymousData(data)
    }
}