package com.blackfox.myoutfitpicker.plugins

import com.blackfox.myoutfitpicker.Platform
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.Identity.encode
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun Application.configureRouting() {
    val API_KEY = Platform().apiKey
    install(Routing) {
        get("/weather") {
                call.respondText("current weather")
        }
        get("/weather/current") {
            call.respondText("calling for current weather in this city")
        }
        get("/weather/current/{city}") {
            val city = call.parameters.get("city")
            call.respondText("calling for current weather in $city")
        }
        get("/weather/monthly") {
            call.respondText("calling for monthly weather in this city")
        }
        get("/weather/monthly/{city}") {
            val city = call.parameters.get("city")
            val httpClient = createHttpClient().let {
                headersOf("X-RapidAPI-Host", "community-open-weather-map.p.rapidapi.com")
                headersOf("X-RapidAPI-Key", API_KEY)
            }
            val request =
                httpClient.get("'https://community-open-weather-map.p.rapidapi.com/climate/month?q=${city}")
            launch {
                val s = "test response"//request.bodyAsText()
                println(s.toString())
                call.respondText(s)
            }
        }
        post("/outfit_data") {
            val data = call.receive<String>()
            println(data)
            call.respondText("success", null, HttpStatusCode.OK)
        }
    }
}


