package com.kevinschildhorn.fotopresenter.extension

import kotlinx.coroutines.flow.MutableStateFlow

fun <T> MutableStateFlow<T>.update(block: (T) -> T) {
    this.value = block(this.value)
}
