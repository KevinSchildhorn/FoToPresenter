package com.kevinschildhorn.fotopresenter.ui.state

import androidx.compose.ui.graphics.ImageBitmap
import com.kevinschildhorn.fotopresenter.data.network.NetworkDirectory

data class DirectoryUiState(
    val directories: List<DirectoryContents> = emptyList(),
    override val state: State = State.IDLE,
) : UiState

data class DirectoryContents(
    val associatedDirectory: NetworkDirectory,
    val bitmap: ImageBitmap? = null,
) {
    val name:String
        get() = associatedDirectory.name
}