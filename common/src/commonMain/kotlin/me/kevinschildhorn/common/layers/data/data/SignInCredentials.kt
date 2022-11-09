package me.kevinschildhorn.common.layers.data.data

/**
Credentials used for signing into an FTP server
 **/
data class SignInCredentials(
    val address: String,
    val username: String,
    val password: String,
)