package com.kevinschildhorn.fotopresenter.ui.screens.playlist.composables

import androidx.compose.runtime.Composable
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.Overlay

@Composable
fun PlaylistOverlay(
    options: List<String> = emptyList(),
) {
    Overlay(5f) {
        PlaylistColumn(options)
    }
}