package com.blackfox.myoutfitpicker.android.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview
@Composable
fun HomeScreen() {
    Column {
        Text(text = "home view", style = MaterialTheme.typography.h6)
    }
}

