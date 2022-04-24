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
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.blackfox.myoutfitpicker.MyOutfitPickerViewModel
import org.koin.androidx.compose.inject

@Preview
@Composable
fun SettingsScreen(@PreviewParameter(ViewmodelProvider::class) viewmodel : MyOutfitPickerViewModel) {
    Scaffold(topBar = { TopAppBar(title = { Text("Settings") }) }) { paddingValue ->
        Column(
            Modifier
            .width(IntrinsicSize.Max)
            .height(IntrinsicSize.Max)
            .padding(paddingValue)
        ) {
            Text(text = "settings view", style = MaterialTheme.typography.h6)
        }
    }
}