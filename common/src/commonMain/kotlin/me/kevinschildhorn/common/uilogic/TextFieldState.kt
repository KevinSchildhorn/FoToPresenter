package me.kevinschildhorn.common.uilogic

import me.kevinschildhorn.android.viewModel.EmailValidationState
import me.kevinschildhorn.common.ui.ColorOption


data class TextFieldState(
    val hint: String,
    private val defaultColor: ColorOption,
    var isError: Boolean = false,
    // UI Elements:
    var text: String = "",
    // Border Color - Color
    var currentBorderColor: ColorOption = defaultColor,
    // Error Text - Text, Color
    var errorText: String? = null,
    // Trailing Icon - Icon, Color
    var trailingIconState: TrailingIconState = TrailingIconState.NONE,
    var hasFocus:Boolean = false,
) {
    fun updateWithState(state: EmailValidationState, newText: String) {
        this.text = newText
        when (state) {
            EmailValidationState.Empty -> {
                currentBorderColor = defaultColor
                isError = false
                errorText = null
                trailingIconState = TrailingIconState.NONE
            }
            EmailValidationState.Invalid -> {
                if(hasFocus){
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
}
