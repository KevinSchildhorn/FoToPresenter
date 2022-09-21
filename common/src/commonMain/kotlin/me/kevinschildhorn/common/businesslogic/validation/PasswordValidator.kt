package me.kevinschildhorn.common.businesslogic.validation

object PasswordValidator {

    private val letterRegex = ".*[a-zA-Z].*".toRegex()
    private val numberRegex = "\\d".toRegex()

    fun verifyPassword(password: String): PasswordValidationState =
        when {
            password.isEmpty() -> PasswordValidationState.Empty
            !letterRegex.matches(password) -> PasswordValidationState.NoLetter
            !numberRegex.matches(password) -> PasswordValidationState.NoNumber
            password.length < 10 -> PasswordValidationState.NotTenCharacters
            else -> PasswordValidationState.Valid
        }
}