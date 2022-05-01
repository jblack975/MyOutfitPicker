package com.blackfox.myoutfitpicker

import com.blackfox.myoutfitpicker.viewmodel.SharedViewModel
import com.russhwolf.settings.coroutines.FlowSettings
import com.blackfox.myoutfitpicker.viewmodel.settings
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
//import org.kodein.memory.util.currentTimestampMillis

class SettingsStorage(
    private val viewModel: SharedViewModel,
    private val settings: FlowSettings = settings()
) {
    companion object {

        private const val SETTINGS_CITY_LIST = "recent_cities"
        private const val SETTINGS_DEGREE_TYPE = "use_celsius"
        private const val SETTINGS_ANONYMOUS_ID = "anon_id"
    }

    val recentCities: Flow<Set<String>> =
        settings.getStringOrNullFlow(SETTINGS_CITY_LIST)
            .map {
                if (it == null) return@map emptySet<String>()
                Json.decodeFromString(it)
            }

    suspend fun checkCurrentWeatherByCity(city:String) : CurrentForecast? {
        val weather = settings.getStringOrNull("current_$city")
        return if(weather != null && weather.length > 20) {
            val a = Json.decodeFromString<CurrentForecast>(weather)
            //if(a.dt + (1000 * 60 * 5) < currentTimestampMillis()) a else null
            a
        } else null
    }
    suspend fun checkMonthlyWeatherByCity(city:String) : MonthlyForecast? {
        val weather = settings.getStringOrNull("monthly_$city")
        return if(weather != null && weather.length > 20) {
            val a = Json.decodeFromString<MonthlyForecast>(weather)
            //if(a.dt < currentTimestampMillis()) a else null
            a
        } else null
    }
    suspend fun addRecentCity(city: String) {
        val recentCitiesSerialized = settings.getStringOrNull(SETTINGS_CITY_LIST)
        val recentCitiesList: MutableSet<String> = if (recentCitiesSerialized == null) {
            mutableSetOf()
        } else {
            Json.decodeFromString(recentCitiesSerialized)
        }

        recentCitiesList.add(city)
        val resultSerialized = Json.encodeToString(recentCitiesList)

        settings.putString(SETTINGS_CITY_LIST, resultSerialized)
    }

    var celsiusTemperatureSettings: Boolean
        set(isCelsius) {
            runBlocking {
                saveTemperatureSettings(isCelsius)
            }
        }
        get() {
            return runBlocking {
                return@runBlocking getTemperatureSettings()
            }
        }

    suspend fun saveTemperatureSettings(isCelsius: Boolean) {
        settings.putBoolean(SETTINGS_DEGREE_TYPE, isCelsius)
    }

    suspend fun getTemperatureSettings(): Boolean {
        return settings.getBooleanOrNull(SETTINGS_DEGREE_TYPE) ?: true
    }
    suspend fun saveAnonymousId(anonId: String) {
        settings.putString(SETTINGS_ANONYMOUS_ID, anonId)
    }

    fun getAnonymousId(): String {
        return runBlocking {
            val job: Deferred<String> = async(Dispatchers.Default) {
                settings.getString(SETTINGS_ANONYMOUS_ID)
            }
            job.start()
            var s = job.await()
            if(s.isBlank()) {
                s = "ffffffjjjjj"
                saveAnonymousId(s)
            }
            return@runBlocking s
        }
    }

    suspend fun saveCurrentWeatherByCity(city: String, a: CurrentForecast?) {
        settings.putString("current_$city", Json.encodeToString(a))
    }

    suspend fun saveMonthlyWeatherByCity(city: String, a: MonthlyForecast?) {
        settings.putString("monthly_$city", Json.encodeToString(a))
    }
}