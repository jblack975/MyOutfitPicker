package com.blackfox.myoutfitpicker.plugins

import com.blackfox.myoutfitpicker.Platform
import com.blackfox.myoutfitpicker.api.WeatherApi
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

fun Application.configureRouting() {
    install(Routing) {
        get("/") {
            val uri = call.request.uri
            call.respondText("Request uri: $uri")
        }

        get("/hello") {
            call.respondText("Hello")
        }
        get("/weather/") {
                call.respondText("current weather")
        }
    }
}


