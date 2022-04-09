package com.blackfox.myoutfitpicker

import io.ktor.server.application.*
import com.blackfox.myoutfitpicker.plugins.configureRouting
import com.blackfox.myoutfitpicker.plugins.configureSerialization
import org.koin.ext.getFullName

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    configureRouting()
    configureSerialization()
    println(try { Platform().platform } catch(e:Throwable) { "Wrong platform called"})
}