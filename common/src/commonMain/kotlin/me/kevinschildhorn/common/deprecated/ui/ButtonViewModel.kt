package me.kevinschildhorn.common.deprecated.ui

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import me.kevinschildhorn.common.deprecated.businesslogic.CreatingProfileState
import me.kevinschildhorn.common.deprecated.uilogic.ButtonState

class ButtonViewModel(
    text: String,
    validationState: MutableStateFlow<CreatingProfileState>,
    scope: CoroutineScope
) {
    val uiState: StateFlow<ButtonState> = validationState
        .map { ButtonState(text).updateWithState(it) }
        .stateIn(
            scope = scope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ButtonState(text)
        )
}
