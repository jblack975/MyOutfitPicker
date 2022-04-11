package com.blackfox.myoutfitpicker.android.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun WeatherScreen() {
    Scaffold(topBar = { TopAppBar(title = { Text("Weather") }) }) {
        Column {
            Text(text = "home view", style = MaterialTheme.typography.h6)
        }
    }
}