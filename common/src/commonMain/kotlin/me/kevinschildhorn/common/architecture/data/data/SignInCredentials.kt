package me.kevinschildhorn.common.architecture.data.data

/**
Credentials used for signing into an server
 **/
data class SignInCredentials(
    var address: String,
    var port: String,
    var username: String,
    var password: String,
) {
    val isComplete: Boolean
        get() = address.isEmpty() &&
            port.isNotBlank() &&
            username.isNotBlank() &&
            password.isNotBlank()
}
