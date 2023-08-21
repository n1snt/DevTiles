package com.dev.tiles.services

import android.service.quicksettings.Tile
import android.service.quicksettings.TileService
import android.util.Log
import com.dev.tiles.SecureSettings

class ADBToggleService: TileService() {

    override fun onClick() {
        super.onClick()
        SecureSettings(contentResolver).toggleADB()
        updateQsState()
        Log.d("ADB TOGGLE", "CLICKED")
    }

    private fun updateQsState() {
        qsTile.state = if (SecureSettings(contentResolver).getADB() == 1) {
            Tile.STATE_ACTIVE
        } else { Tile.STATE_INACTIVE }
        qsTile.updateTile()
    }

    override fun onStartListening() {
        super.onStartListening()
        updateQsState()
    }

}