package me.kevinschildhorn.android.ui.logic

import android.graphics.drawable.Icon
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import me.kevinschildhorn.common.uilogic.TrailingIconState

val TrailingIconState.color: Color
    get() = when (this) {
        TrailingIconState.NONE -> Color.Transparent
        TrailingIconState.CLEAR -> Color.Gray
        TrailingIconState.ERROR -> Color.Red
    }

val TrailingIconState.vector: ImageVector
    get() = when (this) {
        TrailingIconState.NONE -> Icons.Filled.Done
        TrailingIconState.CLEAR -> Icons.Filled.Clear
        TrailingIconState.ERROR -> Icons.Filled.Warning
    }


@Composable
fun TrailingIconState.Icon() {
    if(this != TrailingIconState.NONE) {
        Icon(
            this.vector,
            "error",
            tint = this.color
        )
    }
}