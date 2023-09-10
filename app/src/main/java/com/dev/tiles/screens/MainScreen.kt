package com.dev.tiles.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.tiles.R
import com.dev.tiles.services.ADBToggleService
import com.dev.tiles.services.DevOptionsToggleService
import com.dev.tiles.tools.SecureSettings
import com.dev.tiles.tools.addToggleToQS
import com.dev.tiles.ui.components.SettingToggle
import com.dev.tiles.ui.theme.DevTilesTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {

    val context = LocalContext.current
    val secureSettings = SecureSettings(context.contentResolver, context)

    val adbMutable = remember { mutableStateOf(secureSettings.getADB()) }
    val devMutable = remember { mutableStateOf(secureSettings.getDevOptions()) }

    DevTilesTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Scaffold(
                topBar = { TopAppBar(title = { Text(text = stringResource(R.string.devtiles)) }) },
                modifier = Modifier.fillMaxSize()
            ) { paddingValues ->

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize()
                        .padding(horizontal = 25.dp)
                ) {

                    // ADB toggle
                    SettingToggle(
                        text = stringResource(R.string.adb),
                        switchState = adbMutable.value == 1,
                        switchStateCallback = {
                            secureSettings.toggleADB()
                            adbMutable.value = secureSettings.getADB()
                        },
                        iconOnClick = { addToggleToQS(context, ADBToggleService::class.java, context.resources.getString(R.string.adb_toggle)) }
                    )

                    // Developer options toggle
                    SettingToggle(
                        text = stringResource(R.string.developer_options),
                        switchState = devMutable.value == 1,
                        switchStateCallback = {
                            secureSettings.toggleDevOptions()
                            devMutable.value = secureSettings.getDevOptions()
                        },
                        iconOnClick = { addToggleToQS(context, DevOptionsToggleService::class.java,
                            context.resources.getString(R.string.developer_options_toggle)) }
                    )

                }
            }
        }
    }
}


@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview() {
    MainScreen()
}
