package com.kevinschildhorn.fotopresenter.ui.screens.directory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import com.kevinschildhorn.fotopresenter.data.Directory
import com.kevinschildhorn.fotopresenter.data.DirectoryContents
import com.kevinschildhorn.fotopresenter.data.DirectoryNavigator
import com.kevinschildhorn.fotopresenter.data.ImagePreviewNavigator
import com.kevinschildhorn.fotopresenter.data.ImageSlideshowDetails
import com.kevinschildhorn.fotopresenter.data.Path
import com.kevinschildhorn.fotopresenter.data.datasources.ImageMetadataDataSource
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandler
import com.kevinschildhorn.fotopresenter.data.repositories.CredentialsRepository
import com.kevinschildhorn.fotopresenter.ui.SortingType
import com.kevinschildhorn.fotopresenter.ui.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
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
    private val dataSource: ImageMetadataDataSource,
    private val logger: Logger,
) : ViewModel(), KoinComponent {
    /*
     * UI State
     */
    @Suppress("ktlint:standard:property-naming")
    private val _uiState = MutableStateFlow(DirectoryScreenUIState())
    val uiState: StateFlow<DirectoryScreenUIState> =
        _uiState
            .combine(directoryNavigator.currentDirectoryContents) { uiState, directoryContents ->
                imagePreviewNavigator.setFolderContents(directoryContents.images)
                uiState.copy(
                    directoryGridUIState =
                        directoryContents.asDirectoryGridUIState(
                            directoryNavigator.currentPath,
                        ),
                    state = if (uiState.state is UiState.ERROR) uiState.state else UiState.SUCCESS,
                )
            }
            .combine(imagePreviewNavigator.imagePreviewState) { uiState, imagePreview ->
                logger.v { "New Image Preview State: $imagePreview" }

                when (uiState.overlayUiState) {
                    is DirectoryOverlayUiState.ImagePreview,
                    DirectoryOverlayUiState.None,
                        -> {
                        val selectionState =
                            if (imagePreview != null) {
                                DirectoryOverlayUiState.ImagePreview(imagePreview)
                            } else {
                                DirectoryOverlayUiState.None
                            }

                        uiState.copy(overlayUiState = selectionState)
                    }

                    else -> uiState
                }
            }
            .stateIn(
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
        }
        logger.i { "Current overlay state: ${_uiState.value.overlayUiState}" }
    }

    fun setSortType(sortingType: SortingType) =
        viewModelScope.launch(Dispatchers.Default) {
            logger.i { "Setting Sort Type" }
            directoryNavigator.setSortType(sortingType)
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

    //endregion

    //region Image Preview

    fun setSelectedImageById(imageId: Long?) =
        imagePreviewNavigator.setImageIndex(uiState.value.getImageIndexFromId(imageId))

    fun clearPresentedImage() = imagePreviewNavigator.setImageIndex(null)

    fun showPreviousImage() = imagePreviewNavigator.showPreviousImage()

    fun showNextImage() = imagePreviewNavigator.showNextImage()

    //endregion

    //region Actions

    fun startSlideShow(directory: Directory) =
        viewModelScope.launch(Dispatchers.Default) {
            val images = directoryNavigator.getDirectoryContents(directory.details.fullPath).images
            _uiState.update {
                it.copy(slideshowDetails = ImageSlideshowDetails(images))
            }
        }

    fun clearSlideshow() {
        _uiState.update {
            it.copy(slideshowDetails = null)
        }
    }

    fun addLocationToPlaylist(dynamic: Boolean) {} // TODO

    fun startEditingMetadata() =
        viewModelScope.launch(Dispatchers.Default) {
            uiState.value.overlayUiState.castTo<DirectoryOverlayUiState.Actions>()
                ?.let { actionState ->
                    _uiState.update {
                        it.copy(
                            overlayUiState =
                                DirectoryOverlayUiState.Actions.EditMetaData(
                                    metadata = dataSource.readMetadataFromFile(actionState.directory.details.fullPath),
                                    directoryUiState = actionState.directoryUiState,
                                    directory = actionState.directory,
                                ),
                        )
                    }
                }
        }

    fun saveMetadata(metadata: String) =
        viewModelScope.launch(Dispatchers.IO) {
            uiState.value.overlayUiState.castTo<DirectoryOverlayUiState.Actions>()
                ?.let { actionState ->
                    dataSource.writeMetadataToFile(metadata, actionState.directory.details.fullPath)
                    clearOverlay()
                }
        }

    fun clearOverlay() = _uiState.update { it.copy(overlayUiState = DirectoryOverlayUiState.None) }

    //endregion

    private suspend fun tryCatch(block: suspend () -> Unit) =
        try {
            block()
        } catch (e: Exception) {
            _uiState.update { it.copy(state = UiState.ERROR(e.localizedMessage ?: "")) }
        }

    private fun DirectoryContents.asDirectoryGridUIState(path: Path): DirectoryGridUIState =
        DirectoryGridUIState(
            currentPath = path,
            folderStates =
                folders.map {
                    logger.i { "Folder Map: ${it.name} : ${it.id}" }
                    DirectoryGridCellUIState.Folder(it.name, it.id)
                },
            imageStates =
                images.map {
                    logger.i { "Image Map: ${it.name} : ${it.id}" }
                    DirectoryGridCellUIState.Image(it.details, it.name, it.id)
                }.toMutableList(),
        )
}
