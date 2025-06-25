package com.kevinschildhorn.fotopresenter.ui.screens.common

enum class ActionSheetAction(
    val title: String,
) {
    START_SLIDESHOW("Start A Slideshow"),
    ADD_STATIC_LOCATION("Add to a Playlist"),
    ADD_DYNAMIC_LOCATION("Add dynamically to a Playlist"),
    SET_METADATA("Set image metadata"),
    NONE("Nothing"), // TEMP
}

data class ActionSheetContext(
    val action: ActionSheetAction,
    val id: Int,
)
