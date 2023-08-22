package com.dev.tiles.tools

import android.content.ContentResolver
import android.content.Context
import android.content.pm.PackageManager
import android.provider.Settings
import androidx.core.content.PermissionChecker.checkCallingOrSelfPermission
import com.dev.tiles.callbacks.PermissionCallback


class SecureSettings(private val contentResolver: ContentResolver, private val context: Context) {

    fun getADB(): Int {
        return Settings.Global.getString(contentResolver, Settings.Global.ADB_ENABLED).toInt()
    }

    fun getDevOptions(): Int {
        return Settings.Global.getString(contentResolver, Settings.Global.DEVELOPMENT_SETTINGS_ENABLED).toInt()
    }

    fun toggleADB() {
        val adbStats = if (getADB() == 0) { 1 } else { 0 }
        Settings.Global.putString(
            contentResolver,
            Settings.Global.ADB_ENABLED,
            adbStats.toString())
    }

    fun toggleDevOptions() {
        val devStats = if (getDevOptions() == 0) { 1 } else { 0 }
        Settings.Global.putString(
            contentResolver,
            Settings.Global.DEVELOPMENT_SETTINGS_ENABLED,
            devStats.toString())
    }

    fun allowed(callback: PermissionCallback?): Boolean {
        val requiredPermission = "android.permission.WRITE_SECURE_SETTINGS"
        val checkVal = checkCallingOrSelfPermission(context, requiredPermission)
        val isAllowed = checkVal == PackageManager.PERMISSION_GRANTED
        if (isAllowed) {
            callback?.onAllowed()
        } else {
            callback?.onDenied()
        }
        return isAllowed
    }

}