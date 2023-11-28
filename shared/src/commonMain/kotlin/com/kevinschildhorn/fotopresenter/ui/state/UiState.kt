package com.kevinschildhorn.fotopresenter.ui.state

interface UiState {
    val isLoading: Boolean
    val errorMessage: String?
}