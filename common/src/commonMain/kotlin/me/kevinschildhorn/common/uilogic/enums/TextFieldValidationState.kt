package me.kevinschildhorn.common.uilogic.enums

import me.kevinschildhorn.common.businesslogic.validation.EmailValidationState
import me.kevinschildhorn.common.businesslogic.validation.PasswordValidationState

enum class TextFieldValidationState {
    Empty,
    Valid,
    Invalid
}

interface TextFieldValidationInterface {
    val asTextFieldState: TextFieldValidationState
}