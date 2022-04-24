package com.blackfox.myoutfitpicker

import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
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
    @Test
    fun testWeatherMonthlyCity() = testApplication {
        val response = client.get("/weather/monthly/Tampa")
        assertEquals(HttpStatusCode.OK, response.status)
        val model = response.body<MonthlyForecast>()
        assertNotNull(model)
        println(model)
    }
}