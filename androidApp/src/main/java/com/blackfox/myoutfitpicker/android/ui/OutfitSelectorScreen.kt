package com.blackfox.myoutfitpicker.android.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.blackfox.myoutfitpicker.ClothingTypes
import com.blackfox.myoutfitpicker.MyOutfitPickerViewModel
import org.koin.androidx.compose.inject
import java.util.*

@Composable
fun OutfitSelectorScreen(viewmodel : MyOutfitPickerViewModel) {
    Text("Show forecast and results from model")
}