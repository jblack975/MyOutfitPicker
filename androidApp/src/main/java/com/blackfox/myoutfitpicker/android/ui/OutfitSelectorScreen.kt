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
import java.util.*

@Preview
@Composable
fun OutfitSelectorScreen() {
    val checkedState = remember { mutableStateOf(List(ClothingTypes.getNumberOfItems()) { false }) }
    Column(
        Modifier.width(IntrinsicSize.Max).height(IntrinsicSize.Max)
            .verticalScroll(
                rememberScrollState(),
                enabled = true,
                flingBehavior = null,
                reverseScrolling = true
            )
    ) {
        ClothingTypes.values().forEachIndexed { index, s ->
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                Switch(
                    checked = checkedState.value[index],
                    onCheckedChange = {
                        checkedState.value =
                            checkedState.value.mapIndexed { i, b -> if (i == index) it else b }
                    }
                )
                Text(s.clothingLabel, style = MaterialTheme.typography.h6)
            }
        }
    }
}