package com.blackfox.myoutfitpicker.android.ui

import android.annotation.SuppressLint
import android.os.Bundle
import com.blackfox.myoutfitpicker.Greeting
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState

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
            MainScreen()
        }
    }
}

data class BarItem(
    val title: String,
    val image: ImageVector,
    val route: String
)

object NavBarItems {
    val BarItems = listOf(
        BarItem(
            title = "Home",
            image = Icons.Filled.Home,
            route = "home"
        ),
        BarItem(
            title = "Anon",
            image = Icons.Filled.Face,
            route = "anonymous"
        ),
        BarItem(
            title = "Outfit",
            image = Icons.Filled.List,
            route = "outfits"
        ),
        BarItem(
            title = "Settings",
            image = Icons.Filled.Settings,
            route = "settings"
        ),
        BarItem(
            title = "Weather",
            image = Icons.Filled.Clear,
            route = "weather"
        )
    )
}
sealed class NavRoutes(val route: String) {
    object Home : NavRoutes("home")
    object Anonymous : NavRoutes("anonymous")
    object Settings : NavRoutes("settings")
    object OutfitSelector : NavRoutes("outfits")
    object Weather : NavRoutes("weather")
}
@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavRoutes.Home.route,
    ) {
        composable(NavRoutes.Home.route) {
            HomeScreen()
        }

        composable(NavRoutes.Anonymous.route) {
            AnonymousSendScreen()
        }

        composable(NavRoutes.Settings.route) {
            SettingsScreen()
        }

        composable(NavRoutes.OutfitSelector.route) {
            OutfitSelectorScreen()
        }

        composable(NavRoutes.Weather.route) {
            WeatherScreen()
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {

    BottomNavigation {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route

        NavBarItems.BarItems.forEach { navItem ->

            BottomNavigationItem(
                selected = currentRoute == navItem.route,
                onClick = {
                    navController.navigate(navItem.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },

                icon = {
                    Icon(imageVector = navItem.image,
                        contentDescription = navItem.title)
                },
                label = {
                    Text(text = navItem.title)
                },
            )
        }
    }
}

@Preview
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        topBar = { TopAppBar(title = {Text("Outfit Selector")},backgroundColor = MaterialTheme.colors.primary)  },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = { FloatingActionButton(onClick = {}){
            Icon(imageVector = Icons.Default.Add, contentDescription = "Outfit")
        } },
        bottomBar = { BottomNavigationBar(navController = navController)}
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding),  propagateMinConstraints=true) {
            NavigationHost(navController = navController)
        }
    }
}