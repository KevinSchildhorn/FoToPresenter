package com.kevinschildhorn.fotopresenter.extension

import com.kevinschildhorn.fotopresenter.data.supportedImageTypes

fun String.required(required: Boolean = true) = if (required) "$this*" else this

fun String.addPath(directoryName: String): String =
    if (this.isEmpty()) {
        directoryName
    } else if (directoryName.isEmpty()) {
        this.removeLastPath
    } else {
        "$this\\$directoryName"
    }

private val String.removeLastPath: String
    get() = this.split("\\").dropLast(1).joinToString("\\")

fun String.navigateBackToPathAtIndex(index: Int): String =
    if (index < -1) {
        ""
    } else {
        this
            .split("\\")
            .joinToString("\\", limit = index + 1, truncated = "")
            .dropLast(1)
    }

val String.isImagePath: Boolean
    get() {
        if (!this.contains(".")) return false

        val extension = this.split(".").last()
        return supportedImageTypes.contains(extension)
    }

