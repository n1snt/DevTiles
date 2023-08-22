package com.dev.tiles.screens

import android.annotation.SuppressLint
import android.content.res.Resources
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.tiles.callbacks.PermissionCallback
import com.dev.tiles.R
import com.dev.tiles.ui.theme.DevTilesTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PermissionScreen(permissionCheck: (PermissionCallback) -> Unit, navigateToMain: () -> Unit) {

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    DevTilesTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Scaffold(
                topBar = { TopAppBar(title = { Text(text = stringResource(R.string.permission_not_granted)) }) },
                snackbarHost = { SnackbarHost(snackbarHostState) },
                floatingActionButton = {
                    ExtendedFloatingActionButton(
                        onClick = {
                            val callback = object: PermissionCallback {
                                override fun onAllowed() {
                                    navigateToMain()
                                }
                                override fun onDenied() {
                                    permissionSnackbar(Resources.getSystem().getString(R.string.permission_not_granted_1), scope, snackbarHostState)
                                }
                            }
                            permissionCheck(callback)
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
                    Text(text="TODO: Instructions here")
                }
            }
        }
    }
}

@SuppressLint("RememberReturnType", "CoroutineCreationDuringComposition")
fun permissionSnackbar(message: String, scope: CoroutineScope, snackbarHostState: SnackbarHostState) {
    scope.launch {
        snackbarHostState.showSnackbar(message)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview() {
    PermissionScreen({ _ -> }, {})
}
