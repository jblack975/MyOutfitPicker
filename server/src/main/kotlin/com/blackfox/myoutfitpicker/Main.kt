package com.blackfox.myoutfitpicker

import io.ktor.server.application.*
import com.blackfox.myoutfitpicker.plugins.configureRouting
import com.blackfox.myoutfitpicker.plugins.configureSerialization

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    configureRouting()
    configureSerialization()
}