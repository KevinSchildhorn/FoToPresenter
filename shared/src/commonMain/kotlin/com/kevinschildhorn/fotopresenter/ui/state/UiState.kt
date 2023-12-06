package com.kevinschildhorn.fotopresenter.ui.state

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kevinschildhorn.fotopresenter.ui.compose.common.ErrorView
import com.kevinschildhorn.fotopresenter.ui.compose.common.LoadingOverlay

sealed class UiState {
    data object IDLE : UiState()
    data object LOADING : UiState()
    data class ERROR(val message: String) : UiState()
    data object SUCCESS : UiState()

    @Composable
    fun asComposable(modifier: Modifier = Modifier) {
        when (this) {
            is LOADING -> LoadingOverlay()
            is ERROR -> ErrorView(message, modifier = modifier)
            else -> {}
        }
    }
}


