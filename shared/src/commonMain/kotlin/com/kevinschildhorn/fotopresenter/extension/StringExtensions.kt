package com.kevinschildhorn.fotopresenter.extension

fun String.required(required: Boolean = true) = if (required) "$this*" else this
