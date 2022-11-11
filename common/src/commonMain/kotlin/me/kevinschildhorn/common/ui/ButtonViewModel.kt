package me.kevinschildhorn.common.ui

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import me.kevinschildhorn.common.businesslogic.CreatingProfileState
import me.kevinschildhorn.common.uilogic.ButtonState
import me.kevinschildhorn.common.uilogic.TextFieldState

class ButtonViewModel(
    text:String,
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