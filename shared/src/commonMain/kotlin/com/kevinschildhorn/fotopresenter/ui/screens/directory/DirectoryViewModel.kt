package com.kevinschildhorn.fotopresenter.ui.screens.directory

import co.touchlab.kermit.Logger
import com.kevinschildhorn.fotopresenter.UseCaseFactory
import com.kevinschildhorn.fotopresenter.data.Directory
import com.kevinschildhorn.fotopresenter.data.DirectoryContents
import com.kevinschildhorn.fotopresenter.data.FolderDirectory
import com.kevinschildhorn.fotopresenter.data.ImageDirectory
import com.kevinschildhorn.fotopresenter.data.MetadataFileDetails
import com.kevinschildhorn.fotopresenter.data.Path
import com.kevinschildhorn.fotopresenter.data.PlaylistDetails
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandlerException
import com.kevinschildhorn.fotopresenter.data.repositories.PlaylistRepository
import com.kevinschildhorn.fotopresenter.extension.logLargeTitle
import com.kevinschildhorn.fotopresenter.ui.SortingType
import com.kevinschildhorn.fotopresenter.ui.UiState
import com.kevinschildhorn.fotopresenter.ui.screens.common.ActionSheetContext
import com.kevinschildhorn.fotopresenter.ui.screens.common.DefaultImageViewModel
import com.kevinschildhorn.fotopresenter.ui.screens.common.ImageViewModel
import com.kevinschildhorn.fotopresenter.ui.screens.playlist.PlaylistViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import org.koin.core.component.KoinComponent

class DirectoryViewModel(
    playlistRepository: PlaylistRepository,
    private val logger: Logger,
) : PlaylistViewModel(playlistRepository, logger),
    ImageViewModel by DefaultImageViewModel(logger),
    KoinComponent {
    private val slideshowScope: CoroutineScope = viewModelScope + Dispatchers.IO
    private val imageScope: CoroutineScope = viewModelScope + Dispatchers.IO

    @Suppress("ktlint:standard:property-naming")
    private val _directoryContentsState = MutableStateFlow(DirectoryContents())

    @Suppress("ktlint:standard:property-naming")
    private val _uiState = MutableStateFlow(DirectoryScreenState())
    val uiState: StateFlow<DirectoryScreenState> = _uiState.asStateFlow()

    private val currentPath: Path
        get() = uiState.value.currentPath

    val actionSheetContexts: List<ActionSheetContext>
        get() = uiState.value.selectedDirectory?.actionSheetContexts ?: emptyList()

    val selectedMetadata: MetadataFileDetails?
        get() = findSelectedImageDirectory()?.metaData

    init {
        setImageScope(viewModelScope + Dispatchers.Default)
    }

    fun refreshScreen() {
        if(currentPath.isRoot) logger.logLargeTitle("Directory Shown")
        updateDirectories()
    }

    //region Connection

    fun logout() {
        cancelJobs()
        viewModelScope.launch(Dispatchers.Default) {
            logger.i { "Logging Out" }
            val logoutUseCase = UseCaseFactory.disconnectFromServerUseCase
            logoutUseCase()
        }
    }

    //endregion

    //region Image

    fun startSlideshow() {
        logger.i { "Starting Slideshow" }
        cancelJobs()
        logger.d { "Checking for Selected Directory" }
        uiState.value.selectedDirectory?.id?.let { id ->
            logger.d { "Finding Folder" }
            _directoryContentsState.value.folders.find { it.id == id }?.let {
                logger.d { "Folder found, starting to retrieve images" }
                slideshowScope.launch {
                    val retrieveImagesUseCase = UseCaseFactory.retrieveImageDirectoriesUseCase
                    val images = retrieveImagesUseCase(it.details)
                    logger.v { "Retrieved images, copying them to state" }
                    // _uiState.update { it.copy(slideshowDetails = ImageSlideshowDetails(images)) }
                }
            }
        } ?: run {
            logger.w { "No Directory Selected!" }
        }
    }

    fun clearSlideshow() {
        _uiState.update { it.copy(slideshowDetails = null) }
    }

    fun setSelectedImageById(imageId: Long?) {
        logger.i { "Set Image with ID: $imageId" }
        var index: Int? = null
        imageId?.let {
            index = _uiState.value.getImageIndexFromId(it)
        }
        logger.d { "Set Image with Index: $index" }
        setSelectedImage(index)
    }

    fun setSelectedDirectory(directory: DirectoryGridCellState?) {
        _uiState.update { it.copy(selectedDirectory = directory) }
    }

    //endregion

    //region Directory

    fun navigateToFolder(folderIndex: Int) {
        logger.i { "Getting path at index $folderIndex" }
        cancelJobs()
        val finalPath = currentPath.navigateBackToPathAtIndex(folderIndex)
        changeDirectoryToPath(finalPath)
    }

    fun changeDirectory(id: Long) {
        _directoryContentsState.value.allDirectories.find { it.id == id }?.let {
            changeDirectoryToPath(currentPath.addPath(it.details.name))
        }
    }

    private fun changeDirectoryToPath(path: Path) {
        logger.i { "Changing directory to path '$path'" }

        cancelJobs()
        viewModelScope.launch(Dispatchers.Default) {
            val changeDirectoryUseCase = UseCaseFactory.changeDirectoryUseCase
            try {
                logger.d { "Getting New Path" }
                val newPath = changeDirectoryUseCase(path)
                _uiState.update { it.copy(currentPath = newPath) }
                logger.d { "New Path got: $newPath" }
                updateDirectories()
            } catch (e: NetworkHandlerException) {
                logger.e(e) { "Error Occurred Getting new path" }
                _uiState.update {
                    it.copy(
                        state = UiState.ERROR(e.message ?: "An Unknown Network Error Occurred"),
                    )
                }
            } catch (e: Exception) {
                logger.e(e) { "Something went wrong" }
                _uiState.update {
                    it.copy(state = UiState.ERROR(e.message ?: "Something Went Wrong"))
                }
            }
        }
    }

    private fun updateDirectories() {
        logger.i { "Updating Directories for path '$currentPath'" }
        _uiState.update { it.copy(state = UiState.LOADING) }
        viewModelScope.launch(Dispatchers.Default) {
            val retrieveDirectoryUseCase = UseCaseFactory.retrieveDirectoryContentsUseCase
            val directoryContents = retrieveDirectoryUseCase(currentPath)
            logger.d { "Got Directory Contents: ${directoryContents.allDirectories.count()} directories found" }
            _directoryContentsState.update { directoryContents }

            updateGrid()
            logger.d { "Current State: ${uiState.value.state}" }
        }
    }

    private fun updateGrid() =
        with(_directoryContentsState.value) {
            logger.d { "Updating Grid: Updating State to Success" }
            logger.v { "Setting Directories: $this" }
            setImageDirectories(this.images)
            val gridState = this.asDirectoryGridState
            logger.v { "New Grid State $gridState" }
            _uiState.update {
                it.copy(
                    directoryGridState = gridState,
                    state = UiState.SUCCESS,
                )
            }
        }

    private val DirectoryContents.asDirectoryGridState: DirectoryGridState
        get() =
            DirectoryGridState(
                folderStates =
                    folders.map {
                        DirectoryGridCellState.Folder(
                            it.name,
                            it.id,
                        )
                    },
                imageStates =
                    images.map {
                        DirectoryGridCellState.Image(
                            it.details,
                            it.name,
                            it.id,
                        )
                    }.toMutableList(),
            )

    //endregion

    //region Playlist

    fun addSelectedDirectoryToPlaylist(playlist: PlaylistDetails) {
        uiState.value.selectedDirectory?.let { selectedDirectory ->
            with(_directoryContentsState.value) {
                logger.i { "Inserting Playlist Image ${playlist.id} as ${uiState.value.selectedDirectory}" }

                val states: List<Directory> =
                    if (selectedDirectory is DirectoryGridCellState.Image) {
                        this.images
                    } else {
                        this.folders
                    }

                states.find { it.id == selectedDirectory.id }
                    ?.let { directory ->
                        addToPlaylist(directory, playlist)
                    } ?: run {
                    logger.w { "Could not find image directory" }
                }
            }
        } ?: run {
            logger.w { "Selected Directory Not found" }
        }
    }
    //endregion

    fun saveMetadata(metadata: String) {
        findSelectedImageDirectory()?.details?.fullPath?.let {
            viewModelScope.launch(Dispatchers.Default) {
                val saveMetadataForPathUseCase = UseCaseFactory.saveMetadataForPathUseCase
                if (saveMetadataForPathUseCase(it, metadata)) updateDirectories()
            }
        }
    }

    fun setFilterType(sortingType: SortingType) {
        logger.i { "Setting Filter Type" }
        _directoryContentsState.update {
            it.sorted(sortingType)
        }
        updateGrid()
    }

    private fun cancelJobs() {
        logger.d { "Cancelling Jobs!" }
        cancelImageJobs()
        slideshowScope.coroutineContext.cancelChildren()
        imageScope.coroutineContext.cancelChildren()
        logger.v { "Finished Cancelling Jobs!" }
    }

    private fun findSelectedFolderDirectory(): FolderDirectory? =
        uiState.value.selectedDirectory?.id?.let { id ->
            logger.d { "Finding Selected Directory" }
            return _directoryContentsState.value.folders.find { it.id == id }
        }

    private fun findSelectedImageDirectory(): ImageDirectory? =
        uiState.value.selectedDirectory?.id?.let { id ->
            logger.d { "Finding Selected Directory" }
            return _directoryContentsState.value.images.find { it.id == id }
        }
}
