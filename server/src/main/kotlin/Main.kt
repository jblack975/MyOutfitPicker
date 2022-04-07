import io.ktor.client.plugins.*
import io.ktor.http.*
import io.ktor.serialization.*
import io.ktor.server.*
import io.ktor.server.logging.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.netty.*
import plugins.configureRouting

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    configureRouting()
    configureSerialization()
}