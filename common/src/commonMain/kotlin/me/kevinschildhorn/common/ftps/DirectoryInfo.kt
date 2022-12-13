package me.kevinschildhorn.common.ftps

data class DirectoryInfo<T, R>(
    val path: String,
    val directories: List<T>,
    val files: List<R>
)