package com.blackfox.myoutfitpicker

import com.blackfox.myoutfitpicker.remote.WeatherApi
import com.blackfox.myoutfitpicker.viewmodel.SharedViewModel
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.component.inject

class OutfitPickerStore : KoinComponent {
    private val weatherApi: WeatherApi by inject()
    private val viewModel: SharedViewModel by inject()
    private val settingsStorage: SettingsStorage = SettingsStorage(viewModel = get())

    private val _disabledCategories: Flow<Set<String>> = settingsStorage.recentCities
    val fetchActiveUser = false
    val fetchAnonymousId = settingsStorage.getAnonymousId()
    suspend fun retrieveMonthlyForecastByCity(city: String): MonthlyForecast?  {
        return viewModel.retrieveMonthlyForecastByCity(city)
    }

    suspend fun retrieveCurrentWeatherByCity(city: String): CurrentForecast?  {
        return viewModel.retrieveCurrentWeatherByCity(city)
    }
    suspend fun saveAnonymousId(id: String) {
        settingsStorage.saveAnonymousId(id)
    }

    suspend fun saveCurrentWeatherbyCity(city: String, a: CurrentForecast?) {
        settingsStorage.saveCurrentWeatherByCity(city, a)
    }

    suspend fun saveMonthlyWeatherbyCity(city: String, a: MonthlyForecast?) {
        settingsStorage.saveMonthlyWeatherByCity(city, a)
    }
    suspend fun checkCurrentWeatherByCity(city: String) : CurrentForecast? {
        return settingsStorage.checkCurrentWeatherByCity(city)
    }
    suspend fun checkMonthlyWeatherByCity(city: String)  : MonthlyForecast? {
        return settingsStorage.checkMonthlyWeatherByCity(city)
    }
}

enum class ClothingTypes(val clothingLabel:String) {
    BLAZER("blazer"),
    BLOUSE("blouse"),
    BODY("body"),
    DRESS("dress"),
    HAT("hat"),
    HOODIE("hoodie"),
    LONGSLEEVE("longsleeve"),
    OTHER("other"),
    OUTERWARE("outerware"),
    PANTS("pants"),
    POLO("polo"),
    SHOES("shoes"),
    SKIP("skip"),
    SHIRT("shirt"),
    SKIRT("skirt"),
    TOP("top"),
    TSHIRT("t-shirt"),
    UNDERSHIRT("undershirt");

    companion object {
        fun getNumberOfItems() = values().size
    }
}

enum class Situations(val situationalName:String) {
    GYM("gym"),
    CASUAL("casual"),
    BUSINESS_CASUAL("business casual"),
    DRESSY_CASUAL("dressy casual"),
    FORMAL("formal");

    companion object {
        fun getNumberOfItems() = ClothingTypes.values().size
    }
}