package com.blackfox.myoutfitpicker.repository

import co.touchlab.kermit.Logger
import com.blackfox.myoutfitpicker.ClothingWeatherModel
import com.blackfox.myoutfitpicker.CurrentForecast
import com.blackfox.myoutfitpicker.MonthlyForecast
import com.blackfox.myoutfitpicker.MyOutfitPickerViewModel
import com.blackfox.myoutfitpicker.remote.WeatherApi
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class WeatherRepository : KoinComponent {
    private val weatherApi: WeatherApi by inject()
    val logger:Logger by inject()
    suspend fun retrieveMonthlyForecastByCity(city: String): MonthlyForecast? {
        return weatherApi.retrieveMonthlyForecastByCity(city)
    }

    suspend fun retrieveCurrentWeatherByCity(city: String): CurrentForecast? {
        return weatherApi.retrieveCurrentWeatherByCity(city)
    }

    suspend fun saveAnonymousData(data: ClothingWeatherModel) {
        val succeeded = weatherApi.sendUserData(data)
        if(!succeeded) {
            // cache the data

        }
    }
}