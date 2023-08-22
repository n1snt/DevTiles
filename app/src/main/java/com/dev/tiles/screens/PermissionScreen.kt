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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.tiles.R
import com.dev.tiles.ui.theme.DevTilesTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PermissionScreen() {

    DevTilesTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Scaffold(
                topBar = { TopAppBar(title = { Text(text = "Permission not granted ⚠️") }) },
                floatingActionButton = {
                    ExtendedFloatingActionButton(
                        onClick = {
                                  // TODO: Check if permission is granted
                                  // If perm is granted then go to main activity.
                        },
                        expanded = true,
                        icon = {
                            Icon(
                                ImageVector.vectorResource(id = R.drawable.outline_refresh_24),
                                "Check permission"
                            )
                        },
                        text = { Text(text = "Check permission") },
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
                    Text(text="Instructions here")
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview() {
    PermissionScreen()
}
