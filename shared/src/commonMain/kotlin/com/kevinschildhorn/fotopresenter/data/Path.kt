package com.kevinschildhorn.fotopresenter.data

import kotlinx.serialization.Serializable

@JvmInline
@Serializable
value class Path(private val pathString: String) {
    val fileName: String
        get() = pathString.split("\\").last()

    val isRoot: Boolean
        get() = pathString.isEmpty()

    val isEmpty: Boolean
        get() = pathString.isEmpty()

    val isImagePath: Boolean
        get() {
            if (!pathString.contains(".")) return false

            val extension = pathString.split(".").last()
            return supportedImageTypes.contains(extension)
        }

    val pathList: List<Path>
        get() = pathString.split("\\").filter { it.isNotEmpty() }.map { Path(it) }

    private val removeLastPath: Path
        get() = Path(pathString.split("\\").dropLast(1).joinToString("\\"))

    override fun toString(): String = pathString.ifEmpty { "/" }

    fun addPath(directoryName: String): Path = addPath(Path(directoryName))

    private fun addPath(directoryName: Path): Path =
        if (pathString.isEmpty()) {
            directoryName
        } else if (directoryName.isEmpty) {
            removeLastPath
        } else {
            Path("$pathString\\$directoryName")
        }

    /*
     * Returns the path to the parent directory that is X directories back.
     * If the index is -1 then return to home directory
     * for example: "Users/Kevin/Photos/New" navigateBackToPathAtIndex(2) will return "Users/Kevin"
     */
    fun navigateBackToPathAtIndex(index: Int): Path {
        val pathString =
            if (index < -1) {
                ""
            } else {
                val count = pathString.split("\\").count()
                if (index > count) {
                    throw Exception(
                        "Index was out of bounds. There are $count directories and you tried to navigate to $index",
                    )
                }

                pathString
                    .split("\\")
                    .joinToString("\\", limit = index + 1, truncated = "")
                    .dropLast(1)
            }
        return Path(pathString)
    }

    companion object {
        val EMPTY = Path("")
    }
}
