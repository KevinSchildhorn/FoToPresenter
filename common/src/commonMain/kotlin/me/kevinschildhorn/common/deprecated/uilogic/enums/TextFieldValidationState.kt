package me.kevinschildhorn.common.deprecated.uilogic.enums

enum class TextFieldValidationState {
    Empty,
    Valid,
    Invalid
}

interface TextFieldValidationInterface {
    val asTextFieldState: TextFieldValidationState
}
