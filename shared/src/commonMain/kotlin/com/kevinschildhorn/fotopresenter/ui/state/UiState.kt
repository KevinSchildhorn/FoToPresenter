package com.kevinschildhorn.fotopresenter.ui.state

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kevinschildhorn.fotopresenter.ui.compose.common.ErrorView
import com.kevinschildhorn.fotopresenter.ui.compose.common.LoadingOverlay

interface UiState {
    val state: State
}

sealed class State {
    data object IDLE : State()

    data object LOADING : State()

    data class ERROR(val message: String) : State()

    data object SUCCESS : State()

    val isLoading: Boolean
        get() = this == State.LOADING

    val asError: State.ERROR?
        get() = this as? ERROR

    @Composable
    fun asComposable(modifier: Modifier = Modifier) {
        when (this) {
            is LOADING -> LoadingOverlay()
            is ERROR -> ErrorView(message, modifier = modifier)
            else -> {}
        }
    }
}
