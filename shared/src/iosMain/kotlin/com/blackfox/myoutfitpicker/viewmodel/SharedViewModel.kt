package com.blackfox.myoutfitpicker.viewmodel

import com.blackfox.myoutfitpicker.ClothingWeatherModel
import com.blackfox.myoutfitpicker.repository.WeatherRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
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

    actual suspend fun sendAnonymousData(data: ClothingWeatherModel) {
        weatherRepository.saveAnonymousData(data)
    }
}

@ThreadLocal
private var createViewModelScope: () -> CoroutineScope = {
    CoroutineScope(createUIDispatcher())
}

private fun createUIDispatcher(): CoroutineDispatcher = UIDispatcher()
