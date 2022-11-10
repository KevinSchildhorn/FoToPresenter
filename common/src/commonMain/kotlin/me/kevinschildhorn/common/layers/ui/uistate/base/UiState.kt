package me.kevinschildhorn.common.layers.ui.uistate.base

interface UiState<T>{
    val content:T
    val isLoading: Boolean
    val errorMessage: String?
}