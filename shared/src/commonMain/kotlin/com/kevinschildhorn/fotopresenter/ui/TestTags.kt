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

    enum class Directory(override val value: String) : TestTag {
        TOP_BAR("DirectoryTopBar"),
        TOP_BAR_OPTIONS("DirectoryTopBarOptions"),
        NAVIGATION_RAIL("AppNavigationRail"),
        NAVIGATION_RAIL_ITEM_PLAYLIST("AppNavigationRailItemPlaylist"),
        NAVIGATION_RAIL_ITEM_LOGOUT("AppNavigationRailItemLogout"),
        NAVIGATION_BAR("NavigationBar"),
        SORT_A_TO_Z("SortAToZ"),
        SORT_Z_TO_A("SortZToA"),
        SORT_TIME_CREATED_ASC("SortTimeAscending"),
        SORT_TIME_CREATED_DES("SortTimeDescending"),
    }
}

fun Modifier.testTag(tag: TestTag) = this.testTag(tag.value)
