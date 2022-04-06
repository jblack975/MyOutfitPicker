package com.blackfox.myoutfitpicker.remote

import com.blackfox.myoutfitpicker.Greeting
import com.blackfox.myoutfitpicker.di.createHttpClient
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertTrue

class WeatherApiTest {
    @Test
    fun testExample() {
        assertTrue(runBlocking {  WeatherApi(createHttpClient()).retrieveMonthlyForecastByCity("London,ON,CA")?.list?.size!! > 5})
    }
    @Test
    fun testCurrentWeather() {
        assertTrue(runBlocking {  WeatherApi(createHttpClient()).retrieveCurrentWeatherByCity("London,Uk")?.weather?.size!! > 0})
    }
}