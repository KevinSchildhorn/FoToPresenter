package me.kevinschildhorn.android.ui.extensions

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import me.kevinschildhorn.common.uilogic.enums.TrailingIconState

val TrailingIconState.color: Color
    get() = when (this) {
        TrailingIconState.NONE -> Color.Transparent
        TrailingIconState.CLEAR_TEXT -> Color.Gray
        TrailingIconState.ERROR -> Color.Red
        TrailingIconState.CHECKMARK -> Color.Green
    }

val TrailingIconState.vector: ImageVector
    get() = when (this) {
        TrailingIconState.NONE -> Icons.Filled.Done
        TrailingIconState.CLEAR_TEXT -> Icons.Filled.Clear
        TrailingIconState.ERROR -> Icons.Filled.Warning
        TrailingIconState.CHECKMARK -> Icons.Filled.Done
    }


@Composable
fun TrailingIconState.Icon(clearCallback: () -> Unit) {
    val icon = Icon(
        this.vector,
        "icon",
        tint = this.color
    )
    when (this) {
        TrailingIconState.NONE -> return
        TrailingIconState.CLEAR_TEXT -> {
            IconButton(
                onClick = clearCallback
            ) {
                icon
            }
        }
        else -> icon
    }
}