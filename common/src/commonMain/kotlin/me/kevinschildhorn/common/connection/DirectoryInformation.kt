package me.kevinschildhorn.common.connection

import me.kevinschildhorn.common.connection.data.Directory
import me.kevinschildhorn.common.connection.data.File

data class DirectoryInformation(
    val path: String,
    val directories: List<Directory>,
    val files: List<File>
)