package com.kevinschildhorn.fotopresenter.extension

fun List<Any>.getNextIndex(index: Int): Int {
    val nextIndex = index + 1
    return if (nextIndex >= this.count()) {
        0
    } else {
        nextIndex
    }
}

fun List<Any>.getPreviousIndex(index: Int): Int {
    val previousIndex = index - 1
    return if (previousIndex < 0) {
        this.count() - 1
    } else {
        previousIndex
    }
}
