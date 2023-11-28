package com.kevinschildhorn.fotopresenter.data

/**
Credentials used for signing into an server
 **/
data class LoginCredentials(
    var hostname: String,
    var port: Int,
    var username: String,
    var password: String,
) {
    val isComplete: Boolean
        get() = hostname.isEmpty() &&
                port != 0 &&
                username.isNotBlank() &&
                password.isNotBlank()
}