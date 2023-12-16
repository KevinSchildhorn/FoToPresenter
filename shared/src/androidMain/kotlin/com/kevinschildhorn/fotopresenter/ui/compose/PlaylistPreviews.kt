package com.kevinschildhorn.fotopresenter.ui.compose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.kevinschildhorn.fotopresenter.Playlist
import com.kevinschildhorn.fotopresenter.ui.screens.playlist.composables.PlaylistColumn
import com.kevinschildhorn.fotopresenter.ui.screens.playlist.composables.PlaylistOverlay
import com.kevinschildhorn.fotopresenter.ui.screens.playlist.composables.PlaylistScreenCreateRow
import com.kevinschildhorn.fotopresenter.ui.screens.playlist.composables.PlaylistScreenPlaylistRow


@Composable
@Preview
fun PlaylistRowsPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        PlaylistScreenPlaylistRow("Playlist 1", onClick = {}, onDelete = {}, onEdit = {})
        PlaylistScreenPlaylistRow("Playlist 2", onClick = {}, onDelete = {}, onEdit = {})
        PlaylistScreenCreateRow(onClick = {})
    }
}

@Composable
@Preview
fun PlaylistColumnPreview() {
    PlaylistColumn(
        listOf(Playlist(1,"Playlist 1"), Playlist(2, "Playlist 2")),
        {},
        {},
        {},
        {}
    )
}

@Composable
@Preview
fun PlaylistOverlayPreview() {
    var state by remember {
        mutableStateOf(false)
    }
    Column(Modifier.fillMaxSize().background(Color.Red)) {
        Button({
            state = !state
        }) {
            Text("Toggle")
        }
        PlaylistOverlay(
            listOf(Playlist(1,"Playlist 1"), Playlist(2, "Playlist 2")),
            {},
            {},
            {},
            {}
        )

    }
}
