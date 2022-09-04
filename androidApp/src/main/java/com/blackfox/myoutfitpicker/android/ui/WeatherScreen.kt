package com.blackfox.myoutfitpicker.android.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.blackfox.myoutfitpicker.MyOutfitPickerViewModel
import kotlinx.coroutines.runBlocking
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun WeatherScreen(viewmodel : MyOutfitPickerViewModel) {
    var city by remember { mutableStateOf(TextFieldValue("")) }
    Scaffold(topBar = { TopAppBar(title = { Text("Weather") }) }) { paddingValue ->
        Column(
            Modifier
                .width(IntrinsicSize.Max)
                .height(IntrinsicSize.Max)
                .padding(paddingValue)
        ) {
            Text(text = "weather view", style = MaterialTheme.typography.h6)
            TextField(
                value = city,
                onValueChange = { newValue -> city = newValue },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                label = { Text("label") },
                placeholder = { Text("placeholder") },
            )
            Row(horizontalArrangement = Arrangement.End) {
                Button(onClick = {
                    println("call for weather")
                    runBlocking {
                        val a = viewmodel.monthlyWeather(city.text)
                        println("Monthly response from $a")
                    }
                }, enabled = true) {
                    Text("Monthly Data")
                }
                Button(onClick = {
                    println("call for weather")
                    runBlocking {
                        val a = viewmodel.currentWeather(city.text)
                        println("Current response from $a")
                    }
                }, enabled = true) {
                    Text("Current Data")
                }
            }
            Text(viewmodel.currentWeather.collectAsState(initial = null).value?.wind.toString())
            Text("${viewmodel.currentWeather.collectAsState(initial = null).value?.visibility}%")
            Text(viewmodel.currentWeather.value?.wind.toString())
            Text("${viewmodel.currentWeatherFlow.collectAsState(initial = null).value?.feels_like.toString()}℉")
            Text("${SimpleDateFormat("HH:mm:ss").format(Date(viewmodel.monthlyWeather.value?.list?.first()?.dt ?: 1 * 1000))}")
            Text("${viewmodel.monthlyWeather.value?.list?.first()?.humidity}%")
            Text("${viewmodel.monthlyWeather.value?.list?.first()?.temp}℉ ")
            Text("${viewmodel.monthlyWeather.value?.list?.first()?.pressure} mB")
        }
    }
}