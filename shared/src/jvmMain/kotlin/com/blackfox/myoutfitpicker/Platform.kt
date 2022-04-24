package com.blackfox.myoutfitpicker

val apiKey = BuildKonfig.api_key

actual class Platform actual constructor() {
//    actual val platform: String = "backend server"
    val apiKey = BuildKonfig.api_key
    actual val platform: String
        get() = "java platform"
}