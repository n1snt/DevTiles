package com.dev.tiles.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.tiles.R
import com.dev.tiles.tools.SecureSettings
import com.dev.tiles.ui.components.reusableSnackbar
import com.dev.tiles.ui.theme.DevTilesTheme


/**
 * This screen is displayed when the app is not granted the WRITE_SECURE_SETTINGS
 * @param navigateToMain
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PermissionScreen(navigateToMain: () -> Unit) {

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val secureSettings = SecureSettings(context.contentResolver, context)

    DevTilesTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val permissionString = stringResource(id = R.string.permission_not_granted_1)
            Scaffold(
                topBar = { TopAppBar(title = { Text(text = stringResource(R.string.permission_not_granted)) }) },
                snackbarHost = { SnackbarHost(snackbarHostState) },
                floatingActionButton = {
                    ExtendedFloatingActionButton(
                        onClick = {
                            if (secureSettings.allowed()) {
                                navigateToMain()
                            } else {
                                reusableSnackbar(permissionString, scope, snackbarHostState)
                            }
                        },
                        expanded = true,
                        icon = {
                            Icon(
                                ImageVector.vectorResource(id = R.drawable.outline_refresh_24),
                                stringResource(R.string.check_permission)
                            )
                        },
                        text = { Text(text = stringResource(R.string.check_permission)) },
                        modifier = Modifier.padding(20.dp))
                },
                floatingActionButtonPosition = FabPosition.End,
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
                    Text(text= stringResource(R.string.permission_instructions))
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview() {
    PermissionScreen {}
}
