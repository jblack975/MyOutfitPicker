package com.blackfox.myoutfitpicker.repository

import com.blackfox.myoutfitpicker.CurrentForecast
import com.blackfox.myoutfitpicker.MonthlyForecast
import com.blackfox.myoutfitpicker.remote.WeatherApi
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class WeatherRepository : KoinComponent {
    private val weatherApi: WeatherApi by inject()

    suspend fun retrieveMonthlyForecastByCity(city: String): MonthlyForecast? {
        return weatherApi.retrieveMonthlyForecastByCity(city)
    }

    suspend fun retrieveCurrentWeatherByCity(city: String): CurrentForecast? {
        return weatherApi.retrieveCurrentWeatherByCity(city)
    }
}