package com.dev.tiles

import android.annotation.SuppressLint
import android.app.StatusBarManager
import android.content.ComponentName
import android.graphics.drawable.Icon
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.tiles.ui.theme.DevTilesTheme

class MainActivity : ComponentActivity() {

    private var adbEnabled: Int = 0
    private var devOptions: Int = 0
    private lateinit var secureSettings: SecureSettings

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        secureSettings = SecureSettings(contentResolver)

        adbEnabled = secureSettings.getADB()
        devOptions = secureSettings.getDevOptions()

        setContent {
            val adbMutable = remember { mutableStateOf(adbEnabled) }
            val devMutable = remember { mutableStateOf(devOptions) }
            UI(adbMutable, devMutable, { secureSettings.toggleADB() }, { secureSettings.toggleDevOptions() },
            {addToggleToQS()}) { updateStates(adbMutable, devMutable) }
        }
    }

    fun updateStates(adbMutable: MutableState<Int>, devMutable: MutableState<Int>,) {
        adbMutable.value = secureSettings.getADB()
        devMutable.value = secureSettings.getDevOptions()
    }


    @SuppressLint("NewApi", "WrongConstant")
    fun addToggleToQS() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val statusBarManager = getSystemService(STATUS_BAR_SERVICE) as StatusBarManager
            statusBarManager.requestAddTileService(
                ComponentName(
                    "com.dev.tiles",
                    "com.dev.tiles.services.ADBToggleService",
                ),
                "ADB Toggle",
                Icon.createWithResource(this, R.drawable.outline_usb_24),
                {}, {}
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UI(
    adbMutable: MutableState<Int>,
    devMutable: MutableState<Int>,
    toggleAdb: () -> Unit,
    toggleDevOptions: () -> Unit,
    addToggleToQS: () -> Unit,
    updateStates: () -> Unit
) {
    DevTilesTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Scaffold(
                topBar = { TopAppBar(title = { Text(text = "DevTiles") }) },
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

                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(text = "ADB",
                            modifier = Modifier.fillMaxWidth(0.85F)
                        )
                        Switch(
                            checked = adbMutable.value == 1,
                            onCheckedChange = {
                                toggleAdb()
                                updateStates()
                                              },
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Developer Options",
                            modifier = Modifier.fillMaxWidth(0.85F)
                        )
                        Switch(
                            checked = devMutable.value == 1,
                            onCheckedChange = {
                                toggleDevOptions()
                                updateStates()
                                              },
                        )
                    }

                    Button(onClick = {
                                     addToggleToQS()
                                     },
                        modifier = Modifier.padding(top = 20.dp)) {
                        Text(text = "Add tiles to Quick Settings")
                    }

                }

            }
            
            

        }
    }
}


@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    UI(mutableStateOf(0), mutableStateOf(0), {}, {}, {}) {}
}