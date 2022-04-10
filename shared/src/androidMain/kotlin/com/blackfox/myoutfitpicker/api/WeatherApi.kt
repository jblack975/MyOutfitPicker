package com.blackfox.myoutfitpicker.api

import com.blackfox.myoutfitpicker.CurrentForecast
import com.blackfox.myoutfitpicker.ErrorModel
import com.blackfox.myoutfitpicker.MonthlyForecast
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import com.blackfox.myoutfitpicker.BuildKonfig

/*
val request = Request.Builder()
	.url("https://community-open-weather-map.p.rapidapi.com/climate/month?q=San%20Francisco")
	.get()
	.addHeader("X-RapidAPI-Host", "community-open-weather-map.p.rapidapi.com")
	.addHeader("X-RapidAPI-Key", "c3d2af13b7msh494f812713f91cdp1cdbe4jsn9998c26e66b4")
	.build()

val response = client.newCall(request).execute()
 */
class WeatherApi(private val client: HttpClient,
                 private val baseUrl: String = "https://community-open-weather-map.p.rapidapi.com") {
    private val apiKey = BuildKonfig.api_key
    private val json = Json { isLenient = true; ignoreUnknownKeys = true; useAlternativeNames = false; prettyPrint = true }

    suspend fun retrieveMonthlyForecastByCity(city:String) : MonthlyForecast? {
        client.plugin(HttpSend).intercept { request ->
            val originalCall = execute(request)
            if (originalCall.response.status.value !in 100..399) {
                execute(request)
            } else {
                originalCall
            }
        }
        client.get(
            "$baseUrl/climate/month?q=${java.net.URLEncoder.encode(city, "utf-8")}"
        ) {
            contentType(ContentType.Application.Json)
            headers.append("X-RapidAPI-Key", apiKey)
            headers.append("X-RapidAPI-Host", "community-open-weather-map.p.rapidapi.com")
        }.also { response ->
            val text = response.bodyAsText()
            println("${response.status}\n$text")

            return try {
                json.decodeFromString<MonthlyForecast>(text)
            } catch (e: Exception) {
                println(e.toString())
                val error = json.decodeFromString<ErrorModel>(text)
                println(error)
                null
            }
        }
    }
    suspend fun retrieveCurrentWeatherByCity(city:String) : CurrentForecast? {
        client.plugin(HttpSend).intercept { request ->
            val originalCall = execute(request)
            if (originalCall.response.status.value !in 100..399) {
                execute(request)
            } else {
                originalCall
            }
        }
        client.get(
            "$baseUrl/weather?q=${java.net.URLEncoder.encode(city, "utf-8")}"
        ) {
            contentType(ContentType.Application.Json)
            headers.append("X-RapidAPI-Key", apiKey)
            headers.append("X-RapidAPI-Host", "community-open-weather-map.p.rapidapi.com")
        }.also { response ->
            val text = response.bodyAsText()
            println("${response.status}\n$text")
            return try {
                json.decodeFromString<CurrentForecast>(text)
            } catch (e: Exception) {
                println(e.toString())
                null
            }
        }
    }
}