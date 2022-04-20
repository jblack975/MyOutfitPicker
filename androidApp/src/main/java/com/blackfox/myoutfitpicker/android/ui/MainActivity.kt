package com.blackfox.myoutfitpicker.android.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.hardware.biometrics.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import com.blackfox.myoutfitpicker.Greeting
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_WEAK
import androidx.biometric.BiometricPrompt
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
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.blackfox.myoutfitpicker.MyOutfitPickerViewModel
import org.koin.androidx.compose.inject
import java.lang.Thread.sleep

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewmodel = MyOutfitPickerViewModel()
        setContent {
            val promptInfo = BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric login for my app")
                .setSubtitle("Log in using your biometric credential")
                .setNegativeButtonText("Cancel")
                .setAllowedAuthenticators(BIOMETRIC_STRONG)
                .build()
            val biometricManager = BiometricManager.from(this)
            when (biometricManager.canAuthenticate(BIOMETRIC_STRONG)) {
                BiometricManager.BIOMETRIC_SUCCESS ->
                    Log.d("MY_APP_TAG", "App can authenticate using biometrics.")
                BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE ->
                    Log.e("MY_APP_TAG", "No biometric features available on this device.")
                BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE ->
                    Log.e("MY_APP_TAG", "Biometric features are currently unavailable.")
                BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                    // Prompts the user to create credentials that your app accepts.
                    val enrollIntent = Intent(Settings.ACTION_BIOMETRIC_ENROLL).apply {
                        putExtra(
                            Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                            BIOMETRIC_STRONG
                        )
                    }
                    startActivityForResult(enrollIntent, 7654)
                }
            }
            showBiometricPrompt(viewModel = viewmodel)

            MainScreen(viewmodel)
        }
    }
    private fun showBiometricPrompt(viewModel: MyOutfitPickerViewModel)  {
        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("biometric title")
            .setSubtitle("some description")
            .setNegativeButtonText("Cancel")
            .setConfirmationRequired(true)
            .setAllowedAuthenticators(BIOMETRIC_STRONG)
            .build()

        val biometricPrompt = BiometricPrompt(
            this@MainActivity,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(
                    errorCode: Int,
                    errString: CharSequence
                ) {
                        Toast.makeText(
                            this@MainActivity,
                            "Error will rogers",
                            Toast.LENGTH_LONG
                        ).show()
                    viewModel.biomentricsPassed = false
                }

                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult
                ) {
                    viewModel.biomentricsPassed = true
                }

                override fun onAuthenticationFailed() {
                    Toast.makeText(
                        this@MainActivity,
                        "auth failed",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        )

        biometricPrompt.authenticate(promptInfo)
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
fun NavigationHost(navController: NavHostController, viewmodel:MyOutfitPickerViewModel) {
    NavHost(
        navController = navController,
        startDestination = NavRoutes.Home.route,
    ) {
        composable(NavRoutes.Home.route) {
            HomeScreen()
        }

        composable(NavRoutes.Anonymous.route) {
            AnonymousSendScreen(viewmodel)
        }

        composable(NavRoutes.Settings.route) {
            SettingsScreen(viewmodel)
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
fun MainScreen(@PreviewParameter(ViewmodelProvider::class) viewmodel: MyOutfitPickerViewModel) {
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
            NavigationHost(navController = navController, viewmodel = viewmodel)
        }
    }
}

private const val MinPinLength = 4
private const val MaxPinLength = 16
private const val CorrectPin = "1234"

enum class LoadState {
    SHOW_PIN,
    SHOW_CONTENT,
}

data class PinState(
    val pin: String,
    val pinButtonEnabled: Boolean,
    val pinError: Boolean,
)

data class MainState(
    val loadState: LoadState,
    val pinState: PinState,
    override val value: LoadState = LoadState.SHOW_PIN,
) : State<LoadState> {
    companion object {
        val initial = MainState(
            loadState = LoadState.SHOW_PIN,
            pinState = PinState(
                pin = "",
                pinButtonEnabled = false,
                pinError = false,
            ),
        )
    }
}
