package com.kevinschildhorn.fotopresenter.ui.screens.directory

import co.touchlab.kermit.Logger
import com.kevinschildhorn.fotopresenter.data.Directory
import com.kevinschildhorn.fotopresenter.data.DirectoryContents
import com.kevinschildhorn.fotopresenter.data.ImageSlideshowDetails
import com.kevinschildhorn.fotopresenter.data.PlaylistDetails
import com.kevinschildhorn.fotopresenter.data.State
import com.kevinschildhorn.fotopresenter.UseCaseFactory
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandlerException
import com.kevinschildhorn.fotopresenter.data.repositories.PlaylistRepository
import com.kevinschildhorn.fotopresenter.extension.addPath
import com.kevinschildhorn.fotopresenter.extension.navigateBackToPathAtIndex
import com.kevinschildhorn.fotopresenter.ui.SortingType
import com.kevinschildhorn.fotopresenter.ui.UiState
import com.kevinschildhorn.fotopresenter.ui.screens.common.ActionSheetContext
import com.kevinschildhorn.fotopresenter.ui.screens.common.DefaultImageViewModel
import com.kevinschildhorn.fotopresenter.ui.screens.common.ImageViewModel
import com.kevinschildhorn.fotopresenter.ui.screens.playlist.PlaylistViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class DirectoryViewModel(
    private val playlistRepository: PlaylistRepository,
    private val logger: Logger,
) : PlaylistViewModel(playlistRepository, logger),
    ImageViewModel by DefaultImageViewModel(logger),
    KoinComponent {

    private val _uiState = MutableStateFlow(DirectoryScreenState())
    val uiState: StateFlow<DirectoryScreenState> = _uiState.asStateFlow()

    private val _directoryContentsState = MutableStateFlow(DirectoryContents())

    private val downloadedImageSet: MutableSet<Int> = mutableSetOf()
    private val jobs: MutableList<Job> = mutableListOf<Job>()

    private val currentPath: String
        get() = uiState.value.currentPath

    val actionSheetContexts: List<ActionSheetContext>
        get() = uiState.value.selectedDirectory?.actionSheetContexts ?: emptyList()

    init {
        setImageScope(viewModelScope)
    }

    fun refreshScreen() {
        updateDirectories()
    }

    //region Connection

    fun setLoggedIn() {
        _uiState.update { it.copy(loggedIn = true) }
    }

    fun logout() {
        cancelJobs()
        viewModelScope.launch(Dispatchers.Default) {
            logger.i { "Logging Out" }
            val logoutUseCase = UseCaseFactory.disconnectFromServerUseCase
            logoutUseCase()
            logger.d { "Setting loggedIn state to false" }
            _uiState.update { it.copy(loggedIn = false) }
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
                val job = viewModelScope.launch(Dispatchers.Default) {
                    val retrieveImagesUseCase = UseCaseFactory.retrieveImageDirectoriesUseCase
                    val images = retrieveImagesUseCase(it.details)
                    logger.v { "Retrieved images, copying them to state" }
                    _uiState.update { it.copy(slideshowDetails = ImageSlideshowDetails(images)) }
                }
                jobs.add(job)
            }
        } ?: run {
            logger.w { "No Directory Selected!" }
        }
    }

    fun clearSlideshow() {
        _uiState.update { it.copy(slideshowDetails = null) }
    }

    fun setSelectedImageById(imageId: Int?) {
        logger.i { "Set Image with ID: $imageId" }
        var index: Int? = null
        imageId?.let {
            index = _uiState.value.getImageIndexFromId(it)
        }
        logger.i { "Set Image with Index: $index" }
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

    fun changeDirectory(id: Int) {
        _directoryContentsState.value.allDirectories.find { it.id == id }?.let {
            changeDirectoryToPath(currentPath.addPath(it.details.name))
        }
    }

    private fun changeDirectoryToPath(path: String) {
        logger.i { "Changing directory to path $path" }

        cancelJobs()
        viewModelScope.launch(Dispatchers.Default) {
            val changeDirectoryUseCase = UseCaseFactory.changeDirectoryUseCase
            try {
                logger.i { "Getting New Path" }
                val newPath = changeDirectoryUseCase(path)
                logger.i { "New Path got: $newPath" }
                _uiState.update { it.copy(currentPath = newPath) }
                updateDirectories()
            } catch (e: NetworkHandlerException) {
                logger.e(e) { "Error Occurred Getting new path" }
                _uiState.update {
                    it.copy(
                        state =
                        UiState.ERROR(
                            e.message ?: "An Unknown Network Error Occurred",
                        ),
                    )
                }
            } catch (e: Exception) {
                logger.e(e) { "Something went wrong" }
                _uiState.update {
                    it.copy(
                        state =
                        UiState.ERROR(
                            e.message ?: "Something Went Wrong",
                        ),
                    )
                }
            }
        }
    }

    private fun updateDirectories() {
        logger.i { "Updating Directories" }
        _uiState.update { it.copy(state = UiState.LOADING) }
        val job = viewModelScope.launch(Dispatchers.Default) {
            val retrieveDirectoryUseCase = UseCaseFactory.retrieveDirectoryContentsUseCase

            logger.i { "Getting Directory Contents" }
            val directoryContents = retrieveDirectoryUseCase(currentPath)
            logger.i { "Got Directory Contents: ${directoryContents.allDirectories.count()}" }
            _directoryContentsState.update { directoryContents }

            updateGrid()
            logger.i { "Current State ${uiState.value.state}" }
            updatePhotos()
        }
        jobs.add(job)
    }

    private fun updatePhotos() {
        val count = imageUiState.value.imageDirectories.count()
        downloadedImageSet.clear()

        _uiState.update { it.copy(totalImageCount = count, currentImageCount = 0) }

        logger.i { "Updating Photos" }
        imageUiState.value.imageDirectories.forEachIndexed { index, imageDirectory ->
            val job = viewModelScope.launch(Dispatchers.Default) {
                val retrieveImagesUseCase = UseCaseFactory.retrieveImageUseCase

                retrieveImagesUseCase(imageDirectory) { newState ->

                    downloadedImageSet.add(index)
                    _uiState.update {
                        it.copyImageState(
                            imageDirectory.id,
                            state = newState,
                        ).copy(
                            currentImageCount = downloadedImageSet.size
                        )
                    }
                }
            }.awaitAll()

            // TODO: STORE LARGEST IMAGES IN CHUNKS
        }
    }

    private fun updateGrid() = with(_directoryContentsState.value) {
        logger.i { "Updating State to Success" }
        logger.i { "Setting Directories: $this" }
        setImageDirectories(this.images)
        val gridState = this.asDirectoryGridState
        logger.i { "New Grid State $gridState" }
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
                folderStates = this.folders.map { FolderDirectoryGridCellState(it.name, it.id) },
                imageStates =
                this.images.map {
                    ImageDirectoryGridCellState(
                        State.IDLE,
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
                    if (selectedDirectory.isImageGridCell) this.images
                    else this.folders

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
        jobs.forEach {
            it.cancel()
        }
        jobs.clear()
        logger.v { "Finished Cancelling Jobs!" }

    }
}
