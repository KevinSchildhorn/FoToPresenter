package com.kevinschildhorn.fotopresenter.data

data class LoginUiState(
    val hostName: String = "",
    val username: String = "",
    val password: String = "",
    val isSuccess: Boolean = false
)
