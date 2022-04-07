package com.blackfox.myoutfitpicker

import com.russhwolf.settings.coroutines.FlowSettings
import com.blackfox.myoutfitpicker.viewmodel.settings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class SettingsStorage(
    private val settings: FlowSettings = settings()
) {
    companion object {

        private const val SETTINGS_CITY_LIST = "recent_cities"
        private const val SETTINGS_DEGREE_TYPE = "use_celsius"
    }

    val recentCities: Flow<Set<String>> =
        settings.getStringOrNullFlow(SETTINGS_CITY_LIST)
            .map {
                if (it == null) return@map emptySet<String>()
                Json.decodeFromString(it)
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

}