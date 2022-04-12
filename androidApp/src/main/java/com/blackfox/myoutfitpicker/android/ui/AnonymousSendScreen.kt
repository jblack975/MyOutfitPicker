package com.blackfox.myoutfitpicker.android.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.blackfox.myoutfitpicker.MyOutfitPickerViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview
@Composable
fun AnonymousSendScreen() {
    val viewmodel = MyOutfitPickerViewModel()
    Scaffold(topBar = { TopAppBar(title = { Text("Home") }) }) {
        Column {
            Text(text = "anonymous view", style = MaterialTheme.typography.h6)
            Row {
                Text(text = viewmodel.anonynmousId)

            }
        }
    }
}