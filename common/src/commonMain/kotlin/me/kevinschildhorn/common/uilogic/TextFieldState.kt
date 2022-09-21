package me.kevinschildhorn.common.uilogic

import me.kevinschildhorn.android.viewModel.EmailValidationState
import me.kevinschildhorn.common.ui.ColorOption


data class TextFieldState(
    val hint: String,
    private val defaultColor: ColorOption,
    var isError: Boolean = false,
    // UI Elements:
    var text: String = "",
    var currentBorderColor: ColorOption = defaultColor, // Border Color - Color
    var errorText: String? = null, // Error Text - Text, Color
    var trailingIconState: TrailingIconState = TrailingIconState.NONE, // Trailing Icon - Icon, Color
    var hasFocus: Boolean = false,
    private var lastValidationState: EmailValidationState = EmailValidationState.Empty
) {
    fun updateWithState(state: EmailValidationState, newText: String): TextFieldState  {
        this.text = newText
        return this.copy(text = text).refresh(state).copy(lastValidationState = state)
    }

    fun focusChanged(focus: Boolean): TextFieldState {
        hasFocus = focus
        val next = this.copy(hasFocus = focus).refresh()
        return next
    }

    private fun refresh(state: EmailValidationState = lastValidationState): TextFieldState {
        when (state) {
            EmailValidationState.Empty -> {
                return this.copy(
                    currentBorderColor = defaultColor,
                    isError = false,
                    errorText = null,
                    trailingIconState = TrailingIconState.NONE,
                )
            }
            EmailValidationState.Invalid -> {
                return if (hasFocus) {
                    this.copy(
                        currentBorderColor = defaultColor,
                        isError = false,
                        errorText = null,
                        trailingIconState = TrailingIconState.CLEAR_TEXT,
                    )
                } else {
                    this.copy(
                        currentBorderColor = ColorOption.ERROR,
                        isError = true,
                        errorText = "Not an Email!",
                        trailingIconState = TrailingIconState.ERROR,
                    )
                }
            }
            EmailValidationState.Valid -> {
                return this.copy(
                    currentBorderColor = defaultColor,
                    isError = false,
                    errorText = null,
                    trailingIconState = TrailingIconState.CHECKMARK,
                )
            }
        }
    }

    companion object {
        fun create(hint: String, defaultColor: ColorOption) =
            TextFieldState(hint = hint, defaultColor = defaultColor)
    }
}
