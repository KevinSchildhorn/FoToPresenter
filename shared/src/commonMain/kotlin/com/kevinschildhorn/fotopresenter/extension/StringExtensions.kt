package com.kevinschildhorn.fotopresenter.extension

fun String.required(required: Boolean = true) = if (required) "$this*" else this

fun String.addPath(directoryName: String): String =
    if (this.isEmpty())
        directoryName
    else if (directoryName.isEmpty())
        this.removeLastPath
    else
        "$this\\$directoryName"


private val String.removeLastPath: String
    get() = this.split("\\").dropLast(1).joinToString("\\")