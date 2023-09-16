package com.dev.tiles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dev.tiles.screens.MainScreen
import com.dev.tiles.screens.PermissionScreen
import com.dev.tiles.tools.SecureSettings
import com.dev.tiles.ui.theme.StatusBarNavbarColors

class MainActivity : ComponentActivity() {

    private lateinit var secureSettings: SecureSettings

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        secureSettings = SecureSettings(contentResolver, this)
        setContent {
            val navController = rememberNavController()
            val startDestination = if (secureSettings.allowed())  {
                MAIN_SCREEN
            } else { PERMISSION_SCREEN }

            StatusBarNavbarColors()
            NavHost(navController = navController, startDestination = startDestination) {
                composable(MAIN_SCREEN) {
                    MainScreen()
                }
                composable(PERMISSION_SCREEN) {
                    PermissionScreen { navController.navigate(MAIN_SCREEN) }
                }
            }
        }
    }

    companion object {
        const val MAIN_SCREEN = "main"
        const val PERMISSION_SCREEN = "permission"
    }
}