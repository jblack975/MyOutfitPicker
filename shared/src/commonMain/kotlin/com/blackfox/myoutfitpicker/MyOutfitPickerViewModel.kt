package com.blackfox.myoutfitpicker

import com.blackfox.myoutfitpicker.remote.WeatherApi
import com.blackfox.myoutfitpicker.repository.WeatherRepository
import com.blackfox.myoutfitpicker.viewmodel.SharedViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MyOutfitPickerViewModel() : SharedViewModel() {
    private val outfitPickerStore = OutfitPickerStore()
    private var situation:Situations? = null
    var activeUser = outfitPickerStore.fetchActiveUser
    var anonynmousId = outfitPickerStore.fetchAnonymousId
    var readyToSubmit = MutableSharedFlow<Boolean>(1)
    var currentWeatherFlow = MutableSharedFlow<MainTemperature>(1)
    fun changeAnonymousId() : String {
        // TODO: Change this to something better
        runBlocking {
            outfitPickerStore.saveAnonymousId("s9dfhjsphfsp")
        }
        return "aaaaa"
    }
    var appIntro = """
Welcome to MyOutfitPicker. 
In order to better help you decide on which outfit to wear the more access to information you give the better the application can do, but, if you don't give any extra permissions it can still try to help you out. 
You can also anonymously send information to use a training and that ID is never connected to your own id.
        """
    val clothingTypeList = ClothingTypes.values().map{it.clothingLabel}
    var biomentricsPassed:Boolean? = null
    val situationTypeList = Situations.values().map{it.situationalName}
    var clothingWeatherData:ClothingWeatherModel = ClothingWeatherModel(emptyList(), null, null, id=anonynmousId)
    fun addClothingNameToClothingWeather(clothing:String) {
        val clothEnum = ClothingTypes.values().asList().filter { it.clothingLabel == clothing }
        println("Found enum $clothEnum for $clothing")
        addClothingToClothingWeather(clothEnum.firstOrNull() ?: ClothingTypes.OTHER)
    }
    fun situationChoiceFromName(name:String) {
        val situationEnum = Situations.values().asList().filter{ it.situationalName == name }
        situationChoice = situationEnum.firstOrNull() ?: Situations.GYM
    }
    fun addClothingToClothingWeather(clothing:ClothingTypes) {
        val list = clothingWeatherData.clothing?.toMutableList()
        list?.add(clothing)
        clothingWeatherData.clothing = list?.toList()
        readyToSubmit.tryEmit(clothingWeatherData.clothing?.isNotEmpty() == true && clothingWeatherData.situation != null)
    }
    var situationChoice: Situations?
        get() {
            return situation
        }
        set(value) {
            situation = value
            clothingWeatherData.apply {
                situation = value
                temperature = com.blackfox.myoutfitpicker.MainTemperature(70.0, 60.0, 60.0, 40.0, 70.0, 50.0, 70, 70)
            }
            readyToSubmit.tryEmit(clothingWeatherData.clothing?.isNotEmpty() == true && clothingWeatherData.situation != null)
        }
    fun submitAnonymousData() {
        sharedScope.launch {
                sendAnonymousData(clothingWeatherData)
                clothingWeatherData =
                    ClothingWeatherModel(emptyList(), null, null, id = anonynmousId)
        }
    }
}

@Serializable
data class ClothingWeatherModel(var clothing:List<ClothingTypes>? = emptyList(), var temperature:MainTemperature? = null, var situation:Situations? = null, var id:String = "")

@Serializable
data class ErrorModel(val cod:Int, val message:String)

@Serializable
data class CurrentForecast(val coord: Coordinates, val weather: List<Weather>, val base:String, val main:MainTemperature, val visibility:Int, val wind:WindModel, val clouds:CloudModel,
val dt:Long, val sys:SysModel, val timezone:Int, val id:Int, val name:String, val cod:Int)

@Serializable
data class Weather(val id:Int, val main:String, val description:String, val icon:String)

@Serializable
data class MainTemperature(val temp: Double, val feels_like:Double, val temp_min:Double, val temp_max:Double, val pressure: Double, val humidity: Double, val sea_level:Int? = null, val grnd_level:Int? = null)

@Serializable
data class WindModel(val speed:Double, val deg: Int, val gust:Double? = null)

@Serializable
data class SysModel(val type:Int, val id:Int, val country:String, val sunrise:Long, val sunset:Long)

@Serializable
data class CloudModel(val all:Int)

@Serializable
data class MonthlyForecast(val cod:Int, val city:City, val message:Double?, val list:List<Forecast>?)

@Serializable
data class City(val id:Int, val name:String,val coord:Coordinates, val country:String)

@Serializable
data class Coordinates(val lon:Double, val lat: Double)

@Serializable
data class Forecast(val dt:Long, val humidity:Double, val pressure:Double, val temp: Temperature, val wind_speed:Double)

@Serializable
data class Temperature(val average:Double, val average_max:Double, val average_min:Double, val record_max:Double, val record_min:Double)