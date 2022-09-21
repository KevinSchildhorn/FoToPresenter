package me.kevinschildhorn.common.businesslogic.validation

import me.kevinschildhorn.common.uilogic.enums.TextFieldValidationInterface
import me.kevinschildhorn.common.uilogic.enums.TextFieldValidationState

enum class EmailValidationState : BaseValidationState, TextFieldValidationInterface {
    Empty,
    Valid,
    Invalid;

    override val isValid: Boolean
        get() = this == Valid

    override val asTextFieldState: TextFieldValidationState
        get() = when (this) {
            Empty -> TextFieldValidationState.Empty
            Invalid -> TextFieldValidationState.Invalid
            Valid -> TextFieldValidationState.Valid
        }

}