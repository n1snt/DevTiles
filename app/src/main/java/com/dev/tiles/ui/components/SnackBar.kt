package com.dev.tiles.ui.components

import android.annotation.SuppressLint
import androidx.compose.material3.SnackbarHostState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * A reusable snackbar implementation.
 * @param message
 * @param scope
 * @param snackbarHostState
 */
@SuppressLint("RememberReturnType", "CoroutineCreationDuringComposition")
fun reusableSnackbar(message: String, scope: CoroutineScope, snackbarHostState: SnackbarHostState) {
    scope.launch {
        snackbarHostState.showSnackbar(message)
    }
}

