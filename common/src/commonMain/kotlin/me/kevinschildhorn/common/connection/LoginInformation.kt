package me.kevinschildhorn.common.connection


data class LoginInformation(
    val host: String,
    val port: Int,
    val username: String,
    val password: String,
)