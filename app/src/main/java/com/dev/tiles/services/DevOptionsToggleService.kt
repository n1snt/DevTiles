package com.dev.tiles.services

import android.service.quicksettings.Tile
import android.service.quicksettings.TileService
import com.dev.tiles.tools.SecureSettings

class DevOptionsToggleService: TileService() {

    override fun onClick() {
        super.onClick()
        SecureSettings(contentResolver, this).toggleDevOptions()
        updateQsState()
    }

    private fun updateQsState() {
        if (SecureSettings(contentResolver, this).allowed()) {
            qsTile.state = if (SecureSettings(contentResolver, this).getDevOptions() == 1) {
                Tile.STATE_ACTIVE
            } else {
                Tile.STATE_INACTIVE
            }
        } else {
            qsTile.state = Tile.STATE_UNAVAILABLE
        }
        qsTile.updateTile()
    }

    override fun onStartListening() {
        super.onStartListening()
        updateQsState()
    }

}