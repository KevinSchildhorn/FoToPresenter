package com.kevinschildhorn.fotopresenter.ui.compose

class NavigationState {
    val screen:Screen = Screen.LOGIN
}

enum class Screen {
    LOGIN,
    DIRECTORY,
}