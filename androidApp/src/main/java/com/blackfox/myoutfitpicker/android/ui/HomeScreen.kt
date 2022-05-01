package com.blackfox.myoutfitpicker.android.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.blackfox.myoutfitpicker.MyOutfitPickerViewModel
import org.koin.androidx.compose.inject

@Composable
fun HomeScreen(viewmodel : MyOutfitPickerViewModel) {
    Column {
        Text(text = viewmodel.appIntro, style = MaterialTheme.typography.h6)
    }
}

