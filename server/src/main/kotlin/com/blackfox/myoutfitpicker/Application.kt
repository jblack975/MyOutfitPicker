package com.blackfox.myoutfitpicker

import com.blackfox.myoutfitpicker.plugins.configureCallLogging
import io.ktor.server.application.*
import com.blackfox.myoutfitpicker.plugins.configureRouting
import com.blackfox.myoutfitpicker.plugins.configureSerialization
import com.blackfox.myoutfitpicker.plugins.configureStatusPages

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    configureRouting()
    configureStatusPages()
    configureCallLogging()
    configureSerialization()
}

