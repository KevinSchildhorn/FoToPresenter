package me.kevinschildhorn.common.architecture.ui.uistate.base

interface UiState {
    val isLoading: Boolean
    val errorMessage: String?
}
