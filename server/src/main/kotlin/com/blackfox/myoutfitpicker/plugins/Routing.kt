package com.blackfox.myoutfitpicker.plugins

import com.blackfox.myoutfitpicker.Platform
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        route("/currentWeather") {
            get("{city?}") {
                call.respondText(Platform().platform, status = HttpStatusCode.OK)
            }
        }
        route("/monthlyForecast") {
            get("{city?}") {

            }
        }
    }
}