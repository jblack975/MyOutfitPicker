package com.blackfox.myoutfitpicker.android.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.blackfox.myoutfitpicker.ClothingTypes
import com.blackfox.myoutfitpicker.MyOutfitPickerViewModel
import java.util.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "MutableCollectionMutableState")
@Preview
@Composable
fun OutfitSelectorScreen() {
    var expanded by remember { mutableStateOf(true) }
    var selectedIndex by remember { mutableStateOf(MutableList(ClothingTypes.getNumberOfItems()) { 0 }) }
    val checkedState = remember { mutableStateOf(MutableList(ClothingTypes.getNumberOfItems()) { false }) }
    Column {
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(Color.LightGray)
        ) {
            ClothingTypes.values().forEachIndexed { index, s ->
                DropdownMenuItem(onClick = {
                    selectedIndex[index] = index
                    expanded = true
                }) {
                    Switch(
                        checked = checkedState.value[index],
                        onCheckedChange = { checkedState.value[index] = it }
                    )
                    Text(text = s.clothingLabel)
                }
            }
        }
        Text(text = "outfit view", style = MaterialTheme.typography.h6)
    }
}