package com.blackfox.myoutfitpicker.android.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.blackfox.myoutfitpicker.ClothingTypes
import com.blackfox.myoutfitpicker.MyOutfitPickerViewModel
import com.blackfox.myoutfitpicker.Situations
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import org.koin.androidx.compose.inject

@Preview
@Composable
fun AnonymousSendScreen() {
    val viewmodel : MyOutfitPickerViewModel by inject()
    var expanded by remember { mutableStateOf(false) }
    val checkedState = remember { mutableStateOf(List(ClothingTypes.getNumberOfItems()) { false }) }

    Scaffold(topBar = { TopAppBar(title = { Text("Home") }) }) { paddingValue ->
        Column(
            Modifier
                .padding(paddingValue)
        ) {
            Text(text = "anonymous view", style = MaterialTheme.typography.h6)
            Row {
                Text(text = viewmodel.anonynmousId)
                Button(onClick = {
                    println("button clicked")
                    runBlocking {
                        viewmodel.submitAnonymousData()
                    }
                                 }, enabled=viewmodel.readyToSubmit.collectAsState(initial = false).value) {
                    Text("Submit Data")
                }
            }
            Row {
                IconButton(onClick = { expanded = true }) {
                    Icon(Icons.Default.List, contentDescription = "Situation")
                }
                viewmodel.situationChoice?.let { Text(it.situationalName) }
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                Situations.values().forEachIndexed { _, situations ->
                    DropdownMenuItem(onClick = {
                        viewmodel.situationChoice = situations
                        expanded = false
                    }) {
                        Text(situations.situationalName, style = MaterialTheme.typography.h6)
                    }
                }
            }
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(8.dp)
            ) {
                itemsIndexed(ClothingTypes.values()) { index, item ->
                    Card(
                        modifier = Modifier.padding(4.dp),
                        backgroundColor = Color.LightGray
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                            Switch(
                                checked = checkedState.value[index],
                                onCheckedChange = {
                                    checkedState.value =
                                        checkedState.value.mapIndexed { i, b -> if (i == index) it else b }
                                    viewmodel.addClothingToClothingWeather(item)
                                }
                            )
                            Text(item.clothingLabel, style = MaterialTheme.typography.h6)
                        }
                    }
                }
            }
        }
    }
}