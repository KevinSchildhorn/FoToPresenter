package me.kevinschildhorn.common.architecture.data.data

/**
Credentials used for signing into an server
 **/
data class SignInCredentials(
    var address: String,
    var port: Int,
    var username: String,
    var password: String,
)
