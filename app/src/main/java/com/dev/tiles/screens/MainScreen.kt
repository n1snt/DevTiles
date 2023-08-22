package com.dev.tiles.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.tiles.R
import com.dev.tiles.ui.theme.DevTilesTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    adbMutable: MutableState<Int>,
    devMutable: MutableState<Int>,
    toggleAdb: () -> Unit,
    toggleDevOptions: () -> Unit,
    addADBToggleToQS: () -> Unit,
    addDevOptionsToggleToQS: () -> Unit,
    updateStates: () -> Unit
) {
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

                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        IconButton(onClick = { addADBToggleToQS() },
                            modifier = Modifier.padding(end = 10.dp)) {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.outline_pin_invoke_24),
                                contentDescription = stringResource(R.string.add_to_qs)
                            )
                        }
                        Text(text = stringResource(R.string.adb),
                            modifier = Modifier.fillMaxWidth(0.80F)
                        )
                        Switch(
                            checked = adbMutable.value == 1,
                            onCheckedChange = {
                                toggleAdb()
                                updateStates() },
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = { addDevOptionsToggleToQS() },
                            modifier = Modifier.padding(end = 10.dp)) {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.outline_pin_invoke_24),
                                contentDescription = stringResource(R.string.add_to_qs)
                            )
                        }
                        Text(text = stringResource(R.string.developer_options),
                            modifier = Modifier.fillMaxWidth(0.80F)
                        )
                        Switch(
                            checked = devMutable.value == 1,
                            onCheckedChange = {
                                toggleDevOptions()
                                updateStates() },
                        )
                    }
                }
            }
        }
    }
}


@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview() {
    MainScreen(mutableStateOf(0), mutableStateOf(0), {}, {}, {}, {}, {})
}
