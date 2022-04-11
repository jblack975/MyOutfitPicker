package com.blackfox.myoutfitpicker.android.ui

import android.os.Bundle
import com.blackfox.myoutfitpicker.Greeting
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import androidx.compose.material.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : ComponentActivity() {

    lateinit var manager: GlanceAppWidgetManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        manager = GlanceAppWidgetManager(this)

        lifecycleScope.launch {
        }

        setContent {
            MainLayout()
        }
    }
}

sealed class Screen(val title: String) {
    object LoginScreen : Screen("Login")
    object AnonymousSendScreen : Screen("AnonymousSend")
    object WeatherScreen : Screen("WeatherScreen")
    object OutfitSelectorScreen : Screen("OutfitSelector")
    object SettingsScreen : Screen("Settings")
    object HomeScreen : Screen("Home")
}

@Composable
fun MainLayout() {
    val navController = rememberNavController()
    MyOutfitPickerTheme() {
        NavHost(navController, startDestination = Screen.HomeScreen.title) {
            composable(Screen.HomeScreen.title) {
            }
            composable(Screen.AnonymousSendScreen.title + "/{countryCode}") { backStackEntry ->
                HomeScreen()
            }
            composable(Screen.SettingsScreen.title + "/{networkId}") { backStackEntry ->
                HomeScreen()
            }
            composable(Screen.WeatherScreen.title + "/{networkId}") { backStackEntry ->
                HomeScreen()
            }
            composable(Screen.OutfitSelectorScreen.title + "/{networkId}") { backStackEntry ->
                HomeScreen()
            }
            composable(Screen.LoginScreen.title + "/{networkId}") { backStackEntry ->
                HomeScreen()
            }
        }
    }
}