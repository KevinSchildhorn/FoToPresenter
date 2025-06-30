package com.kevinschildhorn.fotopresenter.ui.screens.directory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import com.kevinschildhorn.fotopresenter.UseCaseFactory
import com.kevinschildhorn.fotopresenter.data.Directory
import com.kevinschildhorn.fotopresenter.data.DirectoryContents
import com.kevinschildhorn.fotopresenter.data.DirectoryNavigator
import com.kevinschildhorn.fotopresenter.data.ImageDirectory
import com.kevinschildhorn.fotopresenter.data.ImagePreviewNavigator
import com.kevinschildhorn.fotopresenter.data.ImageSlideshowDetails
import com.kevinschildhorn.fotopresenter.data.Path
import com.kevinschildhorn.fotopresenter.data.datasources.ImageMetadataDataSource
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandler
import com.kevinschildhorn.fotopresenter.data.repositories.CredentialsRepository
import com.kevinschildhorn.fotopresenter.data.repositories.PlaylistRepository
import com.kevinschildhorn.fotopresenter.ui.ShuffleType
import com.kevinschildhorn.fotopresenter.ui.SortingType
import com.kevinschildhorn.fotopresenter.ui.TagSearchType
import com.kevinschildhorn.fotopresenter.ui.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import org.koin.core.component.KoinComponent

/*
 * ViewModel for Directory Screen
 */
class DirectoryViewModel(
    private val directoryNavigator: DirectoryNavigator,
    private val imagePreviewNavigator: ImagePreviewNavigator,
    // Used for logging
    private val credentialsRepository: CredentialsRepository,
    private val networkHandler: NetworkHandler,
    // Used for MetaData
    private val imageMetadataDataSource: ImageMetadataDataSource,
    private val playlistRepository: PlaylistRepository,
    private val logger: Logger,
) : ViewModel(),
    KoinComponent {
    /*
     * UI State
     */
    @Suppress("ktlint:standard:property-naming")
    private val _uiState = MutableStateFlow(DirectoryScreenUIState())
    val uiState: StateFlow<DirectoryScreenUIState> =
        _uiState
            .combine(directoryNavigator.currentDirectoryContents) { uiState, directoryContents ->

                if (uiState.directoryAdvancedSearchUIState != DirectoryAdvancedSearchUIState.IDLE) {
                    uiState.copy(
                        state =
                            when (uiState.state) {
                                // UiState.LOADING -> uiState.state
                                is UiState.ERROR -> uiState.state
                                else -> UiState.SUCCESS
                            },
                    )
                } else {
                    imagePreviewNavigator.setFolderContents(directoryContents.images)
                    uiState.copy(
                        directoryGridUIState =
                            directoryContents.asDirectoryGridUIState(
                                directoryNavigator.currentPath,
                            ),
                        state =
                            when (uiState.state) {
                                // UiState.LOADING -> uiState.state
                                is UiState.ERROR -> uiState.state
                                else -> UiState.SUCCESS
                            },
                    )
                }
            }.combine(imagePreviewNavigator.imagePreviewState) { uiState, imagePreview ->
                logger.v { "New Image Preview State: $imagePreview" }

                if (imagePreview != null && uiState.overlayUiState is DirectoryOverlayUiState.None) {
                    uiState.copy(overlayUiState = DirectoryOverlayUiState.ImagePreview(imagePreview))
                } else if (imagePreview == null && uiState.overlayUiState is DirectoryOverlayUiState.ImagePreview) {
                    uiState.copy(overlayUiState = DirectoryOverlayUiState.None)
                } else {
                    uiState
                }
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = DirectoryScreenUIState(),
            )

    fun onSearch(searchText: String) =
        viewModelScope.launch(Dispatchers.Default) {
            logger.i { "Setting Search Text" }
            _uiState.update { it.copy(searchText = searchText) }
            directoryNavigator.setSearch(searchText)
        }

    fun showOverlay(state: DirectoryOverlayType) {
        logger.i { "Showing overlay: $state" }
        when (state) {
            DirectoryOverlayType.ACTION_SHEET -> {
                // The action sheet state is already set by setSelectedDirectory
                // No need to update overlayUiState here
            }

            DirectoryOverlayType.IMAGE -> {
                // The image preview state is handled by the imagePreviewNavigator
                // No need to update overlayUiState here
            }

            DirectoryOverlayType.LOGOUT_CONFIRMATION -> {
                logger.i { "Setting logout confirmation overlay" }
                _uiState.update { it.copy(overlayUiState = DirectoryOverlayUiState.LogoutConfirmation) }
            }

            DirectoryOverlayType.SORT -> {
                logger.i { "Setting sort overlay" }
                _uiState.update { it.copy(overlayUiState = DirectoryOverlayUiState.Sort) }
            }

            DirectoryOverlayType.NONE -> {
                logger.i { "Clearing overlay" }
                _uiState.update { it.copy(overlayUiState = DirectoryOverlayUiState.None) }
            }

            DirectoryOverlayType.ADVANCED_SEARCH -> {
                logger.i { "Advanced Search" }
                _uiState.update { it.copy(overlayUiState = DirectoryOverlayUiState.AdvancedSearch) }
            }

            DirectoryOverlayType.DIRECTORY_ACTION_SHEET -> {
                logger.i { "Directory Action Sheet" }
                _uiState.update { it.copy(overlayUiState = DirectoryOverlayUiState.DirectoryOptions) }
            }
        }
        logger.i { "Current overlay state: ${_uiState.value.overlayUiState}" }
    }

    fun setSortType(sortingType: SortingType) =
        viewModelScope.launch(Dispatchers.Default) {
            logger.i { "Setting Sort Type" }
            directoryNavigator.setSortType(sortingType)
        }

    fun setAdvancedSearch(
        tags: List<String>,
        searchType: TagSearchType,
        recursive: Boolean,
        startDate: LocalDate?,
        endDate: LocalDate?,
    ) {
        val path = uiState.value.directoryGridUIState.currentPath
        viewModelScope.launch(Dispatchers.Default) {
            _uiState.update {
                it.copy(
                    directoryAdvancedSearchUIState = DirectoryAdvancedSearchUIState.LOADING,
                )
            }

            val images =
                UseCaseFactory.retrieveImageDirectoriesUseCase(
                    path = path,
                    recursively = recursive,
                    tags = tags,
                    inclusiveTags = searchType == TagSearchType.ALL_TAGS,
                    startDate = startDate,
                    endDate = endDate,
                )
            val newState =
                DirectoryContents(
                    images = images,
                ).asDirectoryGridUIState(path)
            _uiState.update {
                it.copy(
                    directoryAdvancedSearchUIState =
                        DirectoryAdvancedSearchUIState.SUCCESS(
                            tags = tags,
                            allTags = searchType == TagSearchType.ALL_TAGS,
                            itemCount = images.size,
                        ),
                    directoryGridUIState = newState,
                )
            }
            imagePreviewNavigator.setFolderContents(images)
        }
    }

    /*
     * Sets the selected directory to display the possible actions that can be taken on it.
     */
    fun setSelectedDirectory(directoryUiState: DirectoryGridCellUIState?) =
        _uiState.update { uiState ->
            val directory =
                directoryUiState?.id?.let { directoryId ->
                    directoryNavigator.getDirectoryFromId(directoryId)
                }
            if (directory != null) {
                uiState.copy(
                    overlayUiState =
                        DirectoryOverlayUiState.Actions.Sheet(
                            directoryUiState = directoryUiState,
                            directory = directory,
                        ),
                    state = UiState.SUCCESS,
                )
            } else {
                uiState.copy(state = UiState.ERROR("Selected Directory Error"))
            }
        }

    fun logout() =
        viewModelScope.launch(Dispatchers.Default) {
            logger.i { "Logging Out" }
            networkHandler.disconnect()
            credentialsRepository.clearAutoConnect()
        }

    //region Navigation

    fun refreshScreen() =
        viewModelScope.launch(Dispatchers.Default) {
            directoryNavigator.refreshDirectoryContents()
        }

    fun navigateBackToDirectory(directoryIndex: Int) =
        viewModelScope.launch(Dispatchers.Default) {
            _uiState.update { it.copy(state = UiState.LOADING, searchText = "") }
            tryCatch { directoryNavigator.navigateBackToDirectory(directoryIndex) }
        }

    fun navigateIntoDirectory(id: Long) =
        viewModelScope.launch(Dispatchers.Default) {
            _uiState.update { it.copy(state = UiState.LOADING, searchText = "") }
            tryCatch { directoryNavigator.navigateIntoDirectory(id) }
        }

    fun getAllImagesOnScreen(): List<ImageDirectory> =
        directoryNavigator.currentDirectoryContents.value.images
            .toMutableList()

    //endregion

    //region Image Preview

    fun setSelectedImageById(imageId: Long?) = imagePreviewNavigator.setImageIndex(uiState.value.getImageIndexFromId(imageId))

    fun clearPresentedImage() = imagePreviewNavigator.setImageIndex(null)

    fun showPreviousImage() = imagePreviewNavigator.showPreviousImage()

    fun showNextImage() = imagePreviewNavigator.showNextImage()

    //endregion

    //region Actions

    fun startSlideShow(
        directory: Directory,
        withSubPhotos: Boolean,
        shuffleType: ShuffleType,
    ) = viewModelScope.launch(Dispatchers.Default) {
        var images =
            UseCaseFactory.retrieveImageDirectoriesUseCase(
                path = directory.details.fullPath,
                recursively = withSubPhotos,
            )

        val groupedImages: Map<String, List<ImageDirectory>> =
            images.groupBy {
                it.details.fullPath.folderPath
            }

        when (shuffleType) {
            ShuffleType.SHUFFLE_ALL -> images = images.shuffled()
            ShuffleType.SHUFFLE_IMAGES_IN_FOLDERS -> {
                images = groupedImages.flatMap { it.value.shuffled() }
            }

            ShuffleType.SHUFFLE_FOLDERS -> {
                images =
                    groupedImages.values
                        .toList()
                        .shuffled()
                        .flatten()
            }

            ShuffleType.SHUFFLE_ALL_KEEPING_GROUPING -> {
                images =
                    groupedImages
                        .mapValues { it.value.shuffled() }
                        .values
                        .toList()
                        .shuffled()
                        .flatten()
            }

            ShuffleType.NONE -> images
        }
        _uiState.update {
            it.copy(slideshowDetails = ImageSlideshowDetails(images))
        }
    }

    fun clearSlideshow() {
        _uiState.update {
            it.copy(slideshowDetails = null)
        }
    }

    fun showPlaylistOverlay(dynamic: Boolean) {
        viewModelScope.launch(Dispatchers.Default) {
            uiState.value.overlayUiState
                .castTo<DirectoryOverlayUiState.Actions>()
                ?.let { actionState ->
                    _uiState.update {
                        it.copy(
                            overlayUiState =
                                DirectoryOverlayUiState.Actions.AddToPlaylist(
                                    playlists = playlistRepository.getAllPlaylists(),
                                    directoryUiState = actionState.directoryUiState,
                                    directory = actionState.directory,
                                ),
                        )
                    }
                }
        }
    }

    fun showSlideshowOverlay() {
        viewModelScope.launch(Dispatchers.Default) {
            uiState.value.overlayUiState
                .castTo<DirectoryOverlayUiState.Actions>()
                ?.let { actionState ->
                    _uiState.update {
                        it.copy(
                            overlayUiState =
                                DirectoryOverlayUiState.Actions.StartSlideshow(
                                    directoryUiState = actionState.directoryUiState,
                                    directory = actionState.directory,
                                ),
                        )
                    }
                }
        }
    }

    fun addItemToPlaylist(
        playlistId: Long,
        directory: Directory,
    ) {
        viewModelScope.launch(Dispatchers.Default) {
            playlistRepository.insertPlaylistImage(playlistId, directory = directory)
            _uiState.update { it.copy(overlayUiState = DirectoryOverlayUiState.None) }
        }
    }

    fun startEditingMetadata() =
        viewModelScope.launch(Dispatchers.Default) {
            uiState.value.overlayUiState
                .castTo<DirectoryOverlayUiState.Actions>()
                ?.let { actionState ->
                    _uiState.update {
                        it.copy(
                            overlayUiState =
                                DirectoryOverlayUiState.Actions.EditMetaData(
                                    metadata =
                                        imageMetadataDataSource.readMetadataFromFile(
                                            actionState.directory.details.fullPath,
                                        ),
                                    directoryUiState = actionState.directoryUiState,
                                    directory = actionState.directory,
                                ),
                        )
                    }
                }
        }

    fun saveMetadata(metadata: String) =
        viewModelScope.launch(Dispatchers.IO) {
            uiState.value.overlayUiState
                .castTo<DirectoryOverlayUiState.Actions>()
                ?.let { actionState ->
                    imageMetadataDataSource.writeMetadataToFile(
                        metadata,
                        actionState.directory.details.fullPath,
                    )
                    clearOverlay()
                }
        }

    fun clearOverlay() = _uiState.update { it.copy(overlayUiState = DirectoryOverlayUiState.None) }

    fun clearSearch() {
        _uiState.update { it.copy(directoryAdvancedSearchUIState = DirectoryAdvancedSearchUIState.IDLE) }
        refreshScreen()
    }
    //endregion

    private suspend fun tryCatch(block: suspend () -> Unit) =
        try {
            block()
        } catch (e: Exception) {
            _uiState.update { it.copy(state = UiState.ERROR(e.localizedMessage ?: "")) }
        }

    private fun DirectoryContents.asDirectoryGridUIState(path: Path): DirectoryGridUIState {
        val newState =
            DirectoryGridUIState(
                currentPath = path,
                currentState =
                    if (currentDirectory != null) {
                        DirectoryGridCellUIState.Folder(
                            currentDirectory.name,
                            currentDirectory.id,
                        )
                    } else {
                        print("Error")
                        null
                    },
                folderStates =
                    folders.map {
                        logger.i { "Folder Map: ${it.name} : ${it.id}" }
                        DirectoryGridCellUIState.Folder(it.name, it.id)
                    },
                imageStates =
                    images
                        .map {
                            logger.i { "Image Map: ${it.name} : ${it.id}" }
                            DirectoryGridCellUIState.Image(it.details, it.name, it.id)
                        }.toMutableList(),
            )
        if (currentDirectory == null || newState.currentState == null) {
            Logger.i { "KEVIN ERROR" }
        }
        return newState
    }
}
