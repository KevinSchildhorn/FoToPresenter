package com.kevinschildhorn.fotopresenter.ui

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.kevinschildhorn.fotopresenter.ui.screens.common.ActionSheetAction

interface TestTag {
    val value: String
}

class TestTagImpl(override val value: String) : TestTag

object TestTags {
    val ERROR_VIEW = TestTagImpl("ErrorView")
    val FOTO_DIALOG = TestTagImpl("FotoDialog")
    val CONFIRM = TestTagImpl("Confirm")
    val DISMISS = TestTagImpl("Dismiss")
    val OVERLAY_SHADOW = TestTagImpl("OverlayShadow")
    val ACTION_SHEET = TestTagImpl("ActionSheet")
    fun ACTION_SHEET_ITEM(action: ActionSheetAction) = TestTagImpl("ActionSheetItem-${action.name}")

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

    object Directory {
        fun DIRECTORY(index: Int, name: String) = TestTagImpl("Directory-index$index-$name")
        fun IMAGE_PREVIEW(imageName: String) = TestTagImpl("ImagePreview-$imageName")
        val NAVIGATION_BAR = TestTagImpl("NavigationBar")

        enum class Sort(override val value: String) : TestTag {
            SORT_A_TO_Z("SortAToZ"),
            SORT_Z_TO_A("SortZToA"),
            SORT_TIME_CREATED_ASC("SortTimeAscending"),
            SORT_TIME_CREATED_DES("SortTimeDescending"),
        }

        enum class TopBar(override val value: String) : TestTag {
            TOP_BAR("DirectoryTopBar"),
            SEARCH_BAR("DirectorySearchBar"),
            OPTIONS("DirectoryTopBarOptions"),
            MENU("DirectoryTopBarMenu"),
        }

        enum class NavigationRail(override val value: String) : TestTag {
            NAVIGATION_RAIL("AppNavigationRail"),
            ITEM_PLAYLIST("AppNavigationRailItemPlaylist"),
            ITEM_LOGOUT("AppNavigationRailItemLogout"),
        }
    }
}

fun Modifier.testTag(tag: TestTag) = this.testTag(tag.value)
