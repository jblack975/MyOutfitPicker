package com.blackfox.myoutfitpicker.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

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
            call.respondText("calling for monthly weather in $city")
        }
    }
}


