package com.blackfox.myoutfitpicker

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class RouterTest {
    @Test
    fun testWeatherCurrentCity() = testApplication {
        val response = client.get("/weather/current/Seattle")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("calling for current weather in Seattle", response.bodyAsText())
    }
    @Test
    fun testWeatherCurrent() = testApplication {
        val response = client.get("/weather/current")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("calling for current weather in this city", response.bodyAsText())
    }
}