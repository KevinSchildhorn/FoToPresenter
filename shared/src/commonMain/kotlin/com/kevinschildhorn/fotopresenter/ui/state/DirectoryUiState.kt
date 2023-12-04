package com.kevinschildhorn.fotopresenter.ui.state

import com.kevinschildhorn.fotopresenter.data.network.NetworkDirectory

data class DirectoryUiState(
    val directories: List<NetworkDirectory> = emptyList(),
    override val state: State = State.IDLE,
) : UiState