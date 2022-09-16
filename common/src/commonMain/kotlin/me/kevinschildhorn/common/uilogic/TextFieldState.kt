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
    fun updateWithState(state: EmailValidationState, newText: String) {
        this.text = newText
        refresh(state)
        lastValidationState = state
    }

    fun focusChanged(focus: Boolean) {
        hasFocus = focus
        refresh()
    }

    private fun refresh(state:EmailValidationState = lastValidationState) {
        when (state) {
            EmailValidationState.Empty -> {
                currentBorderColor = defaultColor
                isError = false
                errorText = null
                trailingIconState = TrailingIconState.NONE
            }
            EmailValidationState.Invalid -> {
                if (hasFocus) {
                    currentBorderColor = defaultColor
                    isError = false
                    errorText = null
                    trailingIconState = TrailingIconState.CLEAR_TEXT
                } else {
                    currentBorderColor = ColorOption.ERROR
                    isError = true
                    errorText = "Not an Email!"
                    trailingIconState = TrailingIconState.ERROR
                }
            }
            EmailValidationState.Valid -> {
                currentBorderColor = defaultColor
                isError = false
                errorText = null
                trailingIconState = TrailingIconState.CHECKMARK
            }
        }
    }

    companion object {
        fun create(hint:String, defaultColor: ColorOption) = TextFieldState(hint = hint, defaultColor = defaultColor)
    }
}
