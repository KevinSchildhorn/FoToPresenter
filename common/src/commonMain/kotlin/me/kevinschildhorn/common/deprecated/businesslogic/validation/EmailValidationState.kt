package me.kevinschildhorn.common.deprecated.businesslogic.validation

import me.kevinschildhorn.common.deprecated.uilogic.enums.TextFieldValidationInterface
import me.kevinschildhorn.common.deprecated.uilogic.enums.TextFieldValidationState

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
