package com.blackfox.myoutfitpicker.plugins

import com.blackfox.myoutfitpicker.Platform
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

fun Application.configureRouting() {
    install(Routing) {
        routing {
            get("/") {
                val uri = call.request.uri
                call.respondText("Request uri: $uri")
            }

            get("/hello") {
                call.respondText("Hello")
            }
            get("/weather") {
                call.respondText("current weather")
                    //currentWeather()
            }
        }
    }
}
/*
fun Route.currentWeather() {
    get("{city}") {
        val city = call.parameters["city"] ?: ""
        val ret = WeatherApi(createHttpClient()).retrieveCurrentWeatherByCity(city)
        println(ret)
        call.respondText(Json.encodeToString(ret))
    }
}

fun Route.monthlyWeather() {
    get("{city}") {
        val city = call.parameters["city"] ?: ""
        val ret = "testing, testing"//WeatherApi(createHttpClient()).retrieveMonthlyForecastByCity(city)
        call.respondText(Json.encodeToString(ret))
    }
}
 */