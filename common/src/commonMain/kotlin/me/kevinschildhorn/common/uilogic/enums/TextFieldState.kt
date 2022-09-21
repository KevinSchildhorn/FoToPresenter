package me.kevinschildhorn.common.uilogic.enums

import me.kevinschildhorn.common.uilogic.TrailingIconState
import me.kevinschildhorn.common.validation.EmailValidationState


data class TextFieldState(
    val hint: String,
    var isError: Boolean = false,
    // UI Elements:
    var text: String = "",
    var currentBorderColor: ColorOption = ColorOption.NORMAL, // Border Color - Color
    var currentSharedBorderColor: SharedColorOption = SharedColorOption.NORMAL,
    var errorText: String? = null, // Error Text - Text, Color
    var trailingIconState: TrailingIconState = TrailingIconState.NONE, // Trailing Icon - Icon, Color
    var hasFocus: Boolean = false,
    private var lastValidationState: TextFieldValidationState = TextFieldValidationState.Empty
) {
    fun updateWithState(state: TextFieldValidationState, newText: String): TextFieldState =
        this.copy(text = newText).refresh(state).copy(lastValidationState = state)

    fun focusChanged(focus: Boolean): TextFieldState =
        copy(hasFocus = focus).refresh()

    private fun refresh(state: TextFieldValidationState = lastValidationState): TextFieldState =
        when (state) {
            TextFieldValidationState.Empty -> {
                this.copy(
                    currentBorderColor = if (hasFocus) ColorOption.NORMAL else ColorOption.HINT,
                    currentSharedBorderColor = if (hasFocus) SharedColorOption.NORMAL else SharedColorOption.HINT,
                    isError = false,
                    errorText = null,
                    trailingIconState = TrailingIconState.NONE,
                )
            }
            TextFieldValidationState.Invalid -> {
                if (hasFocus) {
                    this.copy(
                        currentBorderColor = ColorOption.NORMAL,
                        currentSharedBorderColor = SharedColorOption.NORMAL,
                        isError = false,
                        errorText = null,
                        trailingIconState = TrailingIconState.CLEAR_TEXT,
                    )
                } else {
                    this.copy(
                        currentBorderColor = ColorOption.ERROR,
                        currentSharedBorderColor = SharedColorOption.ERROR,
                        isError = true,
                        errorText = "Invalid!",
                        trailingIconState = TrailingIconState.ERROR,
                    )
                }
            }
            TextFieldValidationState.Valid -> {
                this.copy(
                    currentBorderColor = ColorOption.SUCCESS,
                    currentSharedBorderColor = SharedColorOption.SUCCESS,
                    isError = false,
                    errorText = null,
                    trailingIconState = TrailingIconState.CHECKMARK,
                )
            }
        }

    companion object {
        fun create(hint: String) =
            TextFieldState(hint = hint)
    }
}
