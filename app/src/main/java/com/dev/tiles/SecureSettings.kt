package com.dev.tiles

import android.content.ContentResolver
import android.provider.Settings
import android.util.Log

class SecureSettings(private val contentResolver: ContentResolver) {

    fun getADB(): Int {
        return Settings.Global.getString(contentResolver, Settings.Global.ADB_ENABLED).toInt()
    }

    fun getDevOptions(): Int {
        return Settings.Global.getString(contentResolver, Settings.Global.DEVELOPMENT_SETTINGS_ENABLED).toInt()
    }

    fun toggleADB() {
        val adbStats = if (getADB() == 0) { 1 } else { 0 }
        Log.d("ADBSTATUS", adbStats.toString())
        Settings.Global.putString(
            contentResolver,
            Settings.Global.ADB_ENABLED,
            adbStats.toString())
    }

    fun toggleDevOptions() {
        val devStats = if (getDevOptions() == 0) { 1 } else { 0 }
        Log.d("DEVSTATUS", devStats.toString())
        Settings.Global.putString(
            contentResolver,
            Settings.Global.DEVELOPMENT_SETTINGS_ENABLED,
            devStats.toString())
    }
}