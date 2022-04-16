package com.blackfox.myoutfitpicker.plugins

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.Identity.encode
import kotlinx.coroutines.runBlocking

fun Application.configureRouting() {
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
            val httpClient = createHttpClient()
                val request = httpClient.get("https://www.google.com/complete/search?q=${city}")
                println(request.bodyAsText())
            call.respondText("calling for monthly weather in $city")
        }
        post("/outfit_data/") {
            val data = call.receive<String>()
            println(data)
            call.respondText("success", null, HttpStatusCode.OK)
        }
    }
}


