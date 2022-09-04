package com.blackfox.myoutfitpicker.plugins

import com.blackfox.myoutfitpicker.CurrentForecast
import com.blackfox.myoutfitpicker.MonthlyForecast
import com.blackfox.myoutfitpicker.apiKey
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.Identity.encode
import io.netty.handler.codec.DateFormatter.append
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun Application.configureRouting() {
    val API_KEY = apiKey
    install(Routing) {
        get("/weather") {
                call.respondText("current weather")
        }
        get("/weather/current") {
            call.respondText("calling for current weather in this city")
        }
        get("/weather/current/{city}") {
            val city = call.parameters.get("city")
            val httpClient = createHttpClient()
            val response =
                httpClient.get("https://community-open-weather-map.p.rapidapi.com/weather?q=${city}") {
                    headers {
                        append("X-RapidAPI-Host", "community-open-weather-map.p.rapidapi.com")
                        append("X-RapidAPI-Key", API_KEY)
                    }
                }.body<CurrentForecast>()
            println("current weather is $response")
            call.respond(HttpStatusCode.OK, response)
        }
        get("/weather/monthly") {
            call.respondText("calling for monthly weather in this city")
        }
        get("/weather/monthly/{city}") {
            val city = call.parameters.get("city")
            val httpClient = createHttpClient()
            val response =
                httpClient.get("https://community-open-weather-map.p.rapidapi.com/climate/month?q=${city}") {
                    headers {
                        append("X-RapidAPI-Host", "community-open-weather-map.p.rapidapi.com")
                        append("X-RapidAPI-Key", API_KEY)
                    }
                }.body<MonthlyForecast>()
            println("monthly weather is $response")
            call.respond(HttpStatusCode.OK, response)
        }
        post("/outfit_data") {
            val data = call.receive<String>()
            println(data)
            call.respondText("success", null, HttpStatusCode.OK)
        }
    }
}

