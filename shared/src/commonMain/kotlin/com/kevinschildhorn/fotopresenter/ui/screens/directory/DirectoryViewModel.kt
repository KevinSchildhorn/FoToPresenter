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

/*
    private val slideshowScope: CoroutineScope = viewModelScope + Dispatchers.IO
    private val imageScope: CoroutineScope = viewModelScope + Dispatchers.IO

    val actionSheetContexts: List<ActionSheetContext>
        get() = uiState.value.selectedDirectory?.actionSheetContexts ?: emptyList()

    val selectedMetadata: MetadataFileDetails?
        get() = findSelectedImageDirectory()?.metaData

    init {
        setImageScope(viewModelScope + Dispatchers.Default)
    }


    fun logout() {
        cancelJobs()
        viewModelScope.launch(Dispatchers.Default) {
            logger.i { "Logging Out" }
            val logoutUseCase = UseCaseFactory.disconnectFromServerUseCase
            logoutUseCase()
        }
    }

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

    fun saveMetadata(metadata: String) {
        findSelectedImageDirectory()?.details?.fullPath?.let {
            viewModelScope.launch(Dispatchers.Default) {
                val saveMetadataForPathUseCase = UseCaseFactory.saveMetadataForPathUseCase
                if (saveMetadataForPathUseCase(it, metadata)) updateDirectories()
            }
        }
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


 */
}
