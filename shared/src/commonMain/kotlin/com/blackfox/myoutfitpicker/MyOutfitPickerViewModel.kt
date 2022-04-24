package com.blackfox.myoutfitpicker

import com.blackfox.myoutfitpicker.viewmodel.SharedViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.serialization.Serializable

class MyOutfitPickerViewModel() : SharedViewModel() {
    private val outfitPickerStore = OutfitPickerStore()
    private var situation:Situations? = null
    var activeUser = outfitPickerStore.fetchActiveUser
    var anonynmousId = outfitPickerStore.fetchAnonymousId
    fun monthlyForecast(city:String) : MonthlyForecast? {
        return runBlocking {
            val job = async(Dispatchers.Default) {
                outfitPickerStore.retrieveMonthlyForecastByCity(city)
            }
            job.start()
            return@runBlocking job.await().also { it }
        }
    }
    fun currentWeather(city:String) : CurrentForecast? {
        return runBlocking {
            val job: Deferred<CurrentForecast?> = async(Dispatchers.Default) {
                outfitPickerStore.retrieveCurrentWeatherByCity(city)
            }
            job.start()
            return@runBlocking job.await()
        }
    }
    var readyToSubmit = MutableSharedFlow<Boolean>(1)
    var currentWeatherFlow = MutableSharedFlow<MainTemperature>(1)
    fun changeAnonymousId() : String {
        val s = (1..Companion.STRING_LENGTH)
            .map { _ -> kotlin.random.Random.nextInt(0, Companion.charPool.size) }
            .map(Companion.charPool::get)
            .joinToString("");
        println("changeAnonymousId: $s")
        return runBlocking {
            val job: Deferred<Unit> = async(Dispatchers.Default) {
                outfitPickerStore.saveAnonymousId(s)
            }
            job.start()
            job.await()
            return@runBlocking s
        }.also { anonynmousId = it }
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
<<<<<<< HEAD
    fun situationChoiceFromName(choice:String) {
        val a = Situations.values().find { it.situationalName == choice } ?: Situations.GYM
        situationChoice = a
    }
    fun addClothingNameToClothingWeather(clothing:String) {
        val a = ClothingTypes.values().find { it.clothingLabel == clothing } ?: ClothingTypes.OTHER
        addClothingToClothingWeather(a)
=======
    fun addClothingNameToClothingWeather(clothing:String) {
        val clothEnum = ClothingTypes.values().asList().filter { it.clothingLabel == clothing }
        println("Found enum $clothEnum for $clothing")
        addClothingToClothingWeather(clothEnum.firstOrNull() ?: ClothingTypes.OTHER)
    }
    fun situationChoiceFromName(name:String) {
        val situationEnum = Situations.values().asList().filter{ it.situationalName == name }
        situationChoice = situationEnum.firstOrNull() ?: Situations.GYM
>>>>>>> 7b9f16b547f17ea0a8e724376d4e5990ac872f09
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

    companion object {
        const val STRING_LENGTH = 48;
        val charPool : List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
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