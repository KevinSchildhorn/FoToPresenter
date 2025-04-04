package com.kevinschildhorn.fotopresenter.ui

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag

interface TestTag {
    val value: String
}

class TestTagImpl(override val value: String) : TestTag

object TestTags {
    val ERROR_VIEW = TestTagImpl("ErrorView")

    enum class Login(override val value: String) : TestTag {
        HOST_NAME("HostNameText"),
        USERNAME("UsernameText"),
        PASSWORD("PasswordText"),
        SHARED_FOLDER("SharedFolderText"),
        AUTO_CONNECT("AutoConnectCheck"),
        LOGIN_BUTTON("LoginButton"),
        HIDE_SHOW_PASSWORD("HideShowPassword"),
        LINK("Link"),
    }
}

fun Modifier.testTag(tag: TestTag) = this.testTag(tag.value)
