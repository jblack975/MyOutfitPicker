package com.blackfox.myoutfitpicker

import com.blackfox.myoutfitpicker.remote.WeatherApi
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class OutfitPickerStore : KoinComponent {
    private val weatherApi: WeatherApi by inject()
    private val settingsStorage: SettingsStorage = SettingsStorage()

    private val _disabledCategories: Flow<Set<String>> = settingsStorage.recentCities
    val fetchActiveUser = false
    val fetchAnonymousId = settingsStorage.getAnonymousId()
    suspend fun saveAnonymousId(id: String) {
        settingsStorage.saveAnonymousId(id)
    }
}