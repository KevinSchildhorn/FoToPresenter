package com.kevinschildhorn.fotopresenter.ui.state

interface UiState {
    val state: State
}

sealed class State {
    data object IDLE : State()
    data object LOADING : State()
    data class ERROR(val message: String) : State()
    data object SUCCESS : State()
}