package com.blackfox.myoutfitpicker

import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertTrue

class RouterTest {
    @Test
    fun testExample() {
        //assertTrue(runBlocking {  WeatherApi(createHttpClient()).retrieveMonthlyForecastByCity("London,ON,CA")?.list?.size!! > 5})
    }
    @Test
    fun testCurrentWeather() {
        //assertTrue(runBlocking {  WeatherApi(createHttpClient()).retrieveCurrentWeatherByCity("London,Uk")?.weather?.size!! > 0})
    }
}