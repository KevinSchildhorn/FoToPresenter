package me.kevinschildhorn.common.architecture.data.data

/**
Credentials used for signing into an FTP server
 **/
data class SignInCredentials(
    var address: String,
    var username: String,
    var password: String,
)
