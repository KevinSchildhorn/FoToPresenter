package me.kevinschildhorn.common.viewmodel.ui

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*

/*
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
}*/