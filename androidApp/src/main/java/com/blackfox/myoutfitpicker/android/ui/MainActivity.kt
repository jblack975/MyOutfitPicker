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

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : ComponentActivity() {

    lateinit var manager: GlanceAppWidgetManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        manager = GlanceAppWidgetManager(this)

        // adding this here just as test for now....need to see where best place to do
        // this would be
        lifecycleScope.launch {
        }

        setContent {
            MainLayout()
        }
    }
}

@Composable
fun MainLayout() {
    val navController = rememberNavController()
}