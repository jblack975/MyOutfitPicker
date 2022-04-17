package com.blackfox.myoutfitpicker.remote

import com.blackfox.myoutfitpicker.*
import com.blackfox.myoutfitpicker.BuildKonfig
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class WeatherApi(private val client: HttpClient,
                 private val baseUrl: String = BuildKonfig.weather_client_host_name) {
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
            "$baseUrl/weather/monthly/${city.replace(" ", "%20")}"
        ) {
            contentType(ContentType.Application.Json)
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
    suspend fun sendUserData(data:ClothingWeatherModel) : Boolean {
        client.plugin(HttpSend).intercept { request ->
            val originalCall = execute(request)
            if (originalCall.response.status.value !in 100..399) {
                execute(request)
            } else {
                originalCall
            }
        }
        client.post(
            "$baseUrl/outfit_data"
        ) {
            contentType(ContentType.Application.Json)
            setBody(data)
        }.also { response ->
            return response.status == HttpStatusCode.OK || response.status == HttpStatusCode.Created
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
            "$baseUrl/weather/current/${city.replace(" ", "%20")}"
        ) {
            contentType(ContentType.Application.Json)
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