package me.kevinschildhorn.common.validation

enum class PasswordValidationState {
    Empty,
    Valid,
    NoNumber,
    NoLetter,
    NotTenCharacters
}