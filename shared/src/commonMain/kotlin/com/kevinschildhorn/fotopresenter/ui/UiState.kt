package com.kevinschildhorn.fotopresenter.ui

sealed class UiState {
    data object IDLE : UiState()

    data object LOADING : UiState()

    data class ERROR(
        val message: String,
    ) : UiState()

    data object SUCCESS : UiState()
}
