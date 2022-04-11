package com.blackfox.myoutfitpicker

actual class Platform actual constructor() {
    actual val platform: String = "Android ${android.os.Build.VERSION.SDK_INT}"

    val apiKey = BuildKonfig.api_key
    val weatherClientApiKey = BuildKonfig.weather_client_api_key
}