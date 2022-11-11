package me.kevinschildhorn.common.ui

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import me.kevinschildhorn.common.atomic.molecules.TextFieldMolecule
import me.kevinschildhorn.common.uilogic.TextFieldState
import me.kevinschildhorn.common.uilogic.enums.TextFieldValidationInterface

data class TextFieldInputState<T : TextFieldValidationInterface>(
    val validationState: T,
    val text: String,
    val focus: Boolean = false
)

class TextFieldViewModel<T : TextFieldValidationInterface>(
    hint: String,
    validationState: StateFlow<T>,
    scope: CoroutineScope
) {
    private var _text: MutableStateFlow<String> = MutableStateFlow("")
    val text: StateFlow<String> = _text

    private var _focus: MutableStateFlow<Boolean> = MutableStateFlow(false)

    val uiState: StateFlow<TextFieldState> = validationState
        .combine(text) { validationState, text ->
            TextFieldInputState(validationState, text, false)
        }
        .combine(_focus) { inputState, focus -> inputState.copy(focus = focus) }
        .map {
            TextFieldState(hint).updateWithState(
                it.validationState.asTextFieldState,
                it.text,
                it.focus
            )
        }
        .stateIn(
            scope = scope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = TextFieldState(hint)
        )

    fun setText(text: String) {
        _text.value = text
    }

    fun setFocus(focus: Boolean) {
        _focus.value = focus
    }
}