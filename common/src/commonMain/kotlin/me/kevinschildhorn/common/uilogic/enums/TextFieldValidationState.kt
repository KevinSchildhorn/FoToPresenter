package me.kevinschildhorn.common.uilogic.enums

import me.kevinschildhorn.common.validation.EmailValidationState
import me.kevinschildhorn.common.validation.PasswordValidationState

enum class TextFieldValidationState {
    Empty,
    Valid,
    Invalid
}

val EmailValidationState.asTextFieldState: TextFieldValidationState
    get() = when (this) {
        EmailValidationState.Empty -> TextFieldValidationState.Empty
        EmailValidationState.Invalid -> TextFieldValidationState.Invalid
        EmailValidationState.Valid -> TextFieldValidationState.Valid
    }
val PasswordValidationState.asTextFieldState: TextFieldValidationState
    get() = when (this) {
        PasswordValidationState.Empty -> TextFieldValidationState.Empty
        PasswordValidationState.Valid -> TextFieldValidationState.Valid
        else -> TextFieldValidationState.Invalid
    }