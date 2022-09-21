package me.kevinschildhorn.common.businesslogic.validation

enum class PasswordValidationState {
    Empty,
    Valid,
    NoNumber,
    NoLetter,
    NotTenCharacters
}