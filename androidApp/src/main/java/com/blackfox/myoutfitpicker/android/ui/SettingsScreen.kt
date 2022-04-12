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
fun SettingsScreen() {
    Scaffold(topBar = { TopAppBar(title = { Text("Settings") }) }) {
        Column {
            Text(text = "settings view", style = MaterialTheme.typography.h6)
        }
    }
}