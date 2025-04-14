package com.kevinschildhorn.fotopresenter.data

import co.touchlab.kermit.Logger
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandlerException
import com.kevinschildhorn.fotopresenter.data.repositories.DirectoryRepository
import com.kevinschildhorn.fotopresenter.ui.SortingType
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/*
 * Handles Navigating up and down an FTP Path of Directories including going forward and backwards in the path,
 * and emits a flow of the current directories contents.
 */
class DirectoryNavigator(private val directoryRepository: DirectoryRepository, private val logger: Logger) {
    private val _currentDirectoryContents = MutableStateFlow(DirectoryContents())
    val currentDirectoryContents: StateFlow<DirectoryContents> =
        _currentDirectoryContents.asStateFlow()

    private var sortType: SortingType = SortingType.NAME_ASC
    private var searchText: String = ""

    var currentPath: Path = Path.EMPTY
        private set

    // Navigates to a parent directory X indexes back
    @Throws(NetworkHandlerException::class, CancellationException::class)
    suspend fun navigateBackToDirectory(directoryIndex: Int) {
        val finalPath = currentPath.navigateBackToPathAtIndex(directoryIndex)
        changeDirectoryToPath(finalPath)
    }

    // Navigates to a child directory with a uid
    @Throws(NetworkHandlerException::class, CancellationException::class)
    suspend fun navigateIntoDirectory(id: Long) {
        _currentDirectoryContents.value.allDirectories.find { it.id == id }?.let {
            changeDirectoryToPath(currentPath.addPath(it.details.name))
        }
    }

    suspend fun setSortType(sortType: SortingType) {
        this.sortType = sortType
        refreshDirectoryContents()
    }

    suspend fun setSearch(searchText: String) {
        this.searchText = searchText
        refreshDirectoryContents()
    }

    fun getDirectoryFromId(id: Long): Directory? = currentDirectoryContents.value.allDirectories.find { it.id == id }

    // Emits from to the Flow the current directories contents.
    // Used when the DirectoryScreen is first shown
    suspend fun refreshDirectoryContents() {
        val newDirectoryContents = getDirectoryContents(currentPath)
        logger.v { "Refreshing Contents With Sort Type: $sortType and filter: $searchText." }
        _currentDirectoryContents.update { newDirectoryContents.sorted(sortType).filtered(searchText) }
    }

    suspend fun getDirectoryContents(path: Path) = directoryRepository.getDirectoryContents(path)

    private suspend fun changeDirectoryToPath(path: Path) {
        currentPath = directoryRepository.changeDirectory(path)
        refreshDirectoryContents()
    }
}
