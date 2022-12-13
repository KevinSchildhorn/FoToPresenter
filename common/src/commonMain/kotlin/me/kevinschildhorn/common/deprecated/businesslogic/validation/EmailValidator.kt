package me.kevinschildhorn.common.deprecated.businesslogic.validation

object EmailValidator {
    private val simpleEmailRegex = "^\\S+@\\S+\\.\\S+\$".toRegex()

    fun verifyEmail(email: String): EmailValidationState =
        when {
            email.isEmpty() -> EmailValidationState.Empty
            simpleEmailRegex.matches(email) -> EmailValidationState.Valid
            else -> EmailValidationState.Invalid
        }
}
