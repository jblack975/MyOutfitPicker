package com.blackfox.myoutfitpicker

import co.touchlab.kermit.Logger
import com.blackfox.myoutfitpicker.plugins.configureCallLogging
import io.ktor.server.application.*
import com.blackfox.myoutfitpicker.plugins.configureRouting
import com.blackfox.myoutfitpicker.plugins.configureSerialization
import com.blackfox.myoutfitpicker.plugins.configureStatusPages
import io.ktor.client.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.serialization.json.Json

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    configureRouting()
    configureStatusPages()
    configureCallLogging()
//    configureSerialization()
}

