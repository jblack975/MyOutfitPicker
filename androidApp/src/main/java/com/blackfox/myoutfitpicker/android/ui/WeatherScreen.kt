package com.blackfox.myoutfitpicker.android.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun WeatherScreen() {
    Scaffold(topBar = { TopAppBar(title = { Text("Weather") }) }) { paddingValue ->
        Column(
            Modifier
                .width(IntrinsicSize.Max)
                .height(IntrinsicSize.Max)
                .padding(paddingValue)
        ) {
            Text(text = "weather view", style = MaterialTheme.typography.h6)
        }
    }
}