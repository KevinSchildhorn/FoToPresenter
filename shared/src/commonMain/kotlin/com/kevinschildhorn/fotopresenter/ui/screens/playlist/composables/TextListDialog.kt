package com.kevinschildhorn.fotopresenter.ui.screens.playlist.composables

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.FotoDialog

@Composable
fun TextListDialog(
    list: List<String>,
    onDismissRequest: () -> Unit,
) {
    FotoDialog(
        dialogTitle = "Playlist Items",
        onDismissRequest = onDismissRequest,
    ) {
        LazyColumn {
            items(list){
                Text(it)
            }
        }
    }
}