package me.kevinschildhorn.common.uilogic.enums

import me.kevinschildhorn.android.viewModel.EmailValidationState
import me.kevinschildhorn.common.uilogic.TrailingIconState


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
    private var lastValidationState: EmailValidationState = EmailValidationState.Empty
) {
    fun updateWithState(state: EmailValidationState, newText: String): TextFieldState =
        this.copy(text = newText).refresh(state).copy(lastValidationState = state)

    fun focusChanged(focus: Boolean): TextFieldState =
        copy(hasFocus = focus).refresh()

    private fun refresh(state: EmailValidationState = lastValidationState): TextFieldState =
        when (state) {
            EmailValidationState.Empty -> {
                this.copy(
                    currentBorderColor = if (hasFocus) ColorOption.NORMAL else ColorOption.HINT,
                    currentSharedBorderColor = if (hasFocus) SharedColorOption.NORMAL else SharedColorOption.HINT,
                    isError = false,
                    errorText = null,
                    trailingIconState = TrailingIconState.NONE,
                )
            }
            EmailValidationState.Invalid -> {
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
                        errorText = "Not an Email!",
                        trailingIconState = TrailingIconState.ERROR,
                    )
                }
            }
            EmailValidationState.Valid -> {
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
