package com.dev.tiles.tools

import android.annotation.SuppressLint
import android.app.StatusBarManager
import android.content.ComponentName
import android.content.Context
import android.graphics.drawable.Icon
import android.os.Build
import androidx.activity.ComponentActivity
import com.dev.tiles.R

/**
 * This function prompts user to add a QS tile.
 * @param context
 * @param title
 */
@SuppressLint("NewApi", "WrongConstant")
fun addToggleToQS(context: Context, serviceClass: Class<*>, title: String) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        val statusBarManager = context.getSystemService(ComponentActivity.STATUS_BAR_SERVICE) as StatusBarManager
        statusBarManager.requestAddTileService(
            ComponentName(
                context,
                serviceClass
            ),
            title,
            Icon.createWithResource(context, R.drawable.outline_usb_24),
            {}, {}
        )
    }
}
