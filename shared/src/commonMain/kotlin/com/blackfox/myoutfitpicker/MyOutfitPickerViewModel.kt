package com.blackfox.myoutfitpicker

import com.blackfox.myoutfitpicker.remote.WeatherApi
import com.blackfox.myoutfitpicker.repository.WeatherRepository
import com.blackfox.myoutfitpicker.viewmodel.SharedViewModel
import kotlinx.serialization.Serializable
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MyOutfitPickerViewModel: SharedViewModel() {
    var searchCity : String
        get() = city.toString()
        set(value) {
            city = value
        }
}

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