package com.blackfox.myoutfitpicker.plugins

import io.ktor.client.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.serialization.json.Json

fun createHttpClient() = HttpClient {
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
        })
    }
}
