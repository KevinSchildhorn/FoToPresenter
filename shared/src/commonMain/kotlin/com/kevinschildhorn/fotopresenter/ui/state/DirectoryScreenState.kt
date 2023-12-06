package com.kevinschildhorn.fotopresenter.ui.state

import com.kevinschildhorn.fotopresenter.data.DirectoryContents

data class DirectoryScreenState(
    val currentPath: String = "",
    val directoryContents: DirectoryContents = DirectoryContents(emptyList(), emptyList()),
    override val state: UiState = UiState.IDLE,
) : ScreenState
