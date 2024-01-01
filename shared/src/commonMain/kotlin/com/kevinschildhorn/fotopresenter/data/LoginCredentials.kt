package com.kevinschildhorn.fotopresenter.data

/**
Credentials used for signing into an server
 **/
data class LoginCredentials(
    var hostname: String,
    var username: String,
    var password: String,
    var sharedFolder: String,
    val shouldAutoConnect: Boolean = false,
) {
    val isComplete: Boolean
        get() =
            hostname.isNotBlank() &&
                username.isNotBlank() &&
                password.isNotBlank() &&
                sharedFolder.isNotBlank()

    override fun toString(): String {
        return """
        hostname: $hostname
        username: $username
        password: $password
        sharedFolder: $sharedFolder
        shouldAutoConnect: $shouldAutoConnect
        """.trimIndent()
    }
}
