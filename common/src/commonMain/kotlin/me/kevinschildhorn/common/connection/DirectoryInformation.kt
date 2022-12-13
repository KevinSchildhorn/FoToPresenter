package me.kevinschildhorn.common.connection

data class DirectoryInfo<T, R>(
    val path: String,
    val directories: List<T>,
    val files: List<R>
)