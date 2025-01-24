package com.kevinschildhorn.fotopresenter.data

import com.kevinschildhorn.fotopresenter.data.repositories.DirectoryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/*
 * Handles Navigating up and down an FTP Path of Directories including going forward and backwards in the path,
 * and emits a flow of the current directories contents.
 */
class DirectoryNavigator(
    private val directoryRepository: DirectoryRepository,
) {
    private val _currentDirectoryContents = MutableStateFlow(DirectoryContents())
    val currentDirectoryContents: StateFlow<DirectoryContents> = _currentDirectoryContents.asStateFlow()

    var currentPath: Path = Path.EMPTY
        private set

    // Navigates to a parent directory X indexes back
    suspend fun navigateBackToDirectory(directoryIndex: Int) {
        val finalPath = currentPath.navigateBackToPathAtIndex(directoryIndex)
        changeDirectoryToPath(finalPath)
    }

    // Navigates to a child directory with a uid
    suspend fun navigateIntoDirectory(id: Long) {
        _currentDirectoryContents.value.allDirectories.find { it.id == id }?.let {
            changeDirectoryToPath(currentPath.addPath(it.details.name))
        }
    }

    // Emits from to the Flow the current directories contents.
    // Used when the DirectoryScreen is first shown
    suspend fun refreshDirectoryContents() {
        val newDirectoryContents = directoryRepository.getDirectoryContents(currentPath)
        _currentDirectoryContents.update { newDirectoryContents }
    }

    private suspend fun changeDirectoryToPath(path: Path) {
        currentPath = directoryRepository.changeDirectory(path)
        refreshDirectoryContents()
    }
}