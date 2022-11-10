package me.kevinschildhorn.common.layers.ui.uistate.base

interface UiState {
    val isLoading: Boolean
    val errorMessage: String?
}