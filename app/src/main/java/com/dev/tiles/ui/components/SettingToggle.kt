package com.dev.tiles.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.tiles.R

/**
 * A settings toggle component which displays a button, text & toggle.
 * @param text
 * @param switchState
 * @param switchStateCallback
 */
@Composable
fun SettingToggle(text: String, switchState: Boolean, switchStateCallback: ((Boolean) -> Unit), iconOnClick: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(onClick = iconOnClick,
            modifier = Modifier.padding(end = 10.dp)) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.outline_pin_invoke_24),
                contentDescription = stringResource(R.string.add_to_qs)
            )
        }
        Text(text = text,
            modifier = Modifier.fillMaxWidth(0.80F)
        )
        Switch(
            checked = switchState,
            onCheckedChange = switchStateCallback
        )
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SettingTogglePreview() {
    SettingToggle(
        text = "Black Hole",
            switchState = true,
            switchStateCallback = {},
            iconOnClick = {}
        )
}