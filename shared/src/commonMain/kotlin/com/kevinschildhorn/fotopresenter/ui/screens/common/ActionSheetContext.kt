package com.kevinschildhorn.fotopresenter.ui.screens.common

enum class ActionSheetAction(val title: String) {
    START_SLIDESHOW("Start A Slideshow"),
    NONE("Nothing"), // TEMP
}

data class ActionSheetContext(
    val action: ActionSheetAction,
    val id: Int,
)
