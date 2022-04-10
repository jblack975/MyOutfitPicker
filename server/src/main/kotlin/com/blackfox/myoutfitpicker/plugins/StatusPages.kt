package com.blackfox.myoutfitpicker.plugins

import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.response.*

fun Application.configureStatusPages() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            if(cause is RuntimeException) {
                call.respondText(text = "403: $cause" , status = HttpStatusCode.Forbidden)
            } else {
                call.respondText(text = "500: $cause" , status = HttpStatusCode.InternalServerError)
            }
        }
        status(HttpStatusCode.NotFound) { call, status ->
            call.respondText(text = "404: Page Not Found", status = status)
        }
    }
}