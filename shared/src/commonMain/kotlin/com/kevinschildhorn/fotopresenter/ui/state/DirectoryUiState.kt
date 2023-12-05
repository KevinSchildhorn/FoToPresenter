package com.kevinschildhorn.fotopresenter.ui.state

import com.kevinschildhorn.fotopresenter.data.DirectoryContents

data class DirectoryUiState(
    val currentPath: String = "",
    val directoryContents: DirectoryContents = DirectoryContents(emptyList(), emptyList()),
    override val state: State = State.IDLE,
) : UiState