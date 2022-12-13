package me.kevinschildhorn.common.deprecated.businesslogic.validation

import me.kevinschildhorn.common.deprecated.uilogic.enums.TextFieldValidationInterface
import me.kevinschildhorn.common.deprecated.uilogic.enums.TextFieldValidationState

enum class PasswordValidationState : BaseValidationState, TextFieldValidationInterface {
    Empty,
    Valid,
    NoNumber,
    NoLetter,
    NotTenCharacters;

    override val isValid: Boolean
        get() = this == Valid

    override val asTextFieldState: TextFieldValidationState
        get() = when (this) {
            Empty -> TextFieldValidationState.Empty
            Valid -> TextFieldValidationState.Valid
            else -> TextFieldValidationState.Invalid
        }
}