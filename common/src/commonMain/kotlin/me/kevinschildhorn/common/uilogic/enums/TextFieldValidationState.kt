package me.kevinschildhorn.common.uilogic.enums

enum class TextFieldValidationState {
    Empty,
    Valid,
    Invalid
}

interface TextFieldValidationInterface {
    val asTextFieldState: TextFieldValidationState
}
