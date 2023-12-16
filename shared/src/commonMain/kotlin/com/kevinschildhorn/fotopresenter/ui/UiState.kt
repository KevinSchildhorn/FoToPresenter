package com.kevinschildhorn.fotopresenter.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.ErrorView
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.LoadingOverlay

sealed class UiState {
    data object IDLE : UiState()

    data object LOADING : UiState()

    data class ERROR(val message: String) : UiState()

    data object SUCCESS : UiState()
}
