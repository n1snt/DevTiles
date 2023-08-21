package com.dev.tiles.services

import android.service.quicksettings.Tile
import android.service.quicksettings.TileService
import android.util.Log
import com.dev.tiles.SecureSettings

class DevOptionsToggleService: TileService() {

    override fun onClick() {
        super.onClick()
        SecureSettings(contentResolver).toggleDevOptions()
        updateQsState()
        Log.d("DEV OPTIONS TOGGLE", "CLICKED")
    }

    private fun updateQsState() {
        qsTile.state = if (SecureSettings(contentResolver).getDevOptions() == 1) {
            Tile.STATE_ACTIVE
        } else { Tile.STATE_INACTIVE }
        qsTile.updateTile()
    }

    override fun onStartListening() {
        super.onStartListening()
        updateQsState()
    }

}