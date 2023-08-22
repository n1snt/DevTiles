package com.dev.tiles

import android.annotation.SuppressLint
import android.app.StatusBarManager
import android.content.ComponentName
import android.graphics.drawable.Icon
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dev.tiles.screens.MainScreen
import com.dev.tiles.screens.PermissionScreen
import com.dev.tiles.services.ADBToggleService
import com.dev.tiles.services.DevOptionsToggleService
import com.dev.tiles.tools.SecureSettings
import com.dev.tiles.ui.theme.StatusBarNavbarColors

class MainActivity : ComponentActivity() {

    private lateinit var secureSettings: SecureSettings

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        secureSettings = SecureSettings(contentResolver, this)

        val adbEnabled = secureSettings.getADB()
        val devOptions = secureSettings.getDevOptions()

        setContent {
            val navController = rememberNavController()
            val adbMutable = remember { mutableStateOf(adbEnabled) }
            val devMutable = remember { mutableStateOf(devOptions) }
            val startDestination = if (secureSettings.allowed(null))  {
                MAIN_SCREEN
            } else { PERMISSION_SCREEN }

            StatusBarNavbarColors()
            NavHost(navController = navController, startDestination = startDestination) {
                composable(MAIN_SCREEN) {
                    MainScreen(adbMutable,
                        devMutable,
                        { secureSettings.toggleADB() },
                        { secureSettings.toggleDevOptions() },
                        { addToggleToQS(ADBToggleService::class.java, getString(R.string.adb_toggle)) },
                        { addToggleToQS(DevOptionsToggleService::class.java,
                            getString(R.string.developer_options_toggle)) },
                        { updateStates(adbMutable, devMutable) }
                    )
                }
                composable(PERMISSION_SCREEN) {
                    PermissionScreen( { callback ->
                        secureSettings.allowed(callback)
                    }, { navController.navigate(MAIN_SCREEN) })
                }
            }
        }
    }

    private fun updateStates(adbMutable: MutableState<Int>, devMutable: MutableState<Int>) {
        adbMutable.value = secureSettings.getADB()
        devMutable.value = secureSettings.getDevOptions()
    }

    @SuppressLint("NewApi", "WrongConstant")
    private fun addToggleToQS(serviceClass: Class<*>, title: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val statusBarManager = getSystemService(STATUS_BAR_SERVICE) as StatusBarManager
            statusBarManager.requestAddTileService(
                ComponentName(
                    this,
                    serviceClass
                ),
                title,
                Icon.createWithResource(this, R.drawable.outline_usb_24),
                {}, {}
            )
        }
    }

    companion object {
        const val MAIN_SCREEN = "main"
        const val PERMISSION_SCREEN = "permission"
    }
}