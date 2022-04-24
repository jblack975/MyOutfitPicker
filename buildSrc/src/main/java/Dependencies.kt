
object Versions {
    const val kotlinCoroutines = "1.6.0-native-mt"
    const val ktor = "2.0.0"
    const val kotlinxSerialization = "1.3.2"
    const val koin = "3.1.5"
    const val lifecycle = "2.2.0-alpha01"

    const val compose = "1.2.0-alpha08"
    const val navCompose = "2.4.0-beta02"
    const val slf4j = "1.7.30"

    const val kermit = "1.0.3"

    const val junit = "4.12"
}

object AndroidSdk {
    const val min = 21
    const val compile = 31
    const val target = compile
}

object Deps {
    const val kermit = "co.touchlab:kermit:${Versions.kermit}"
    const val datastore = "androidx.datastore:datastore-preferences:1.0.0"
    const val lifecycleViewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutines}"
}

object Compose {
    const val ui = "androidx.compose.ui:ui:${Versions.compose}"
    const val uiGraphics = "androidx.compose.ui:ui-graphics:${Versions.compose}"
    const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    const val foundationLayout = "androidx.compose.foundation:foundation-layout:${Versions.compose}"
    const val material = "androidx.compose.material:material:${Versions.compose}"
    const val navigation = "androidx.navigation:navigation-compose:${Versions.navCompose}"
    const val runtime = "androidx.navigation:navigation-compose:${Versions.navCompose}"
}

object Koin {
    val core = "io.insert-koin:koin-core:${Versions.koin}"
    val test = "io.insert-koin:koin-test:${Versions.koin}"
    val android = "io.insert-koin:koin-android:${Versions.koin}"
    val compose = "io.insert-koin:koin-androidx-compose:${Versions.koin}"
}

object Serialization {
    val core = "org.jetbrains.kotlinx:kotlinx-serialization-core:${Versions.kotlinxSerialization}"
}

object Ktor {
    val clientCore = "io.ktor:ktor-client-core:${Versions.ktor}"
    val clientJson = "io.ktor:ktor-client-json:${Versions.ktor}"
    val clientLogging = "io.ktor:ktor-client-logging:${Versions.ktor}"
    val clientSerialization = "io.ktor:ktor-client-serialization:${Versions.ktor}"
    val clientContentNegotiation = "io.ktor:ktor-client-content-negotiation:${Versions.ktor}"
    val clientSerializationJson = "io.ktor:ktor-serialization-kotlinx-json:${Versions.ktor}"
    val clientAndroid = "io.ktor:ktor-client-android:${Versions.ktor}"
    val clientApache = "io.ktor:ktor-client-apache:${Versions.ktor}"
    val slf4j = "org.slf4j:slf4j-simple:${Versions.slf4j}"
    val clientIos = "io.ktor:ktor-client-ios:${Versions.ktor}"
}

