package com.kevinschildhorn.fotopresenter.ui.screens.directory

import co.touchlab.kermit.Logger
import com.kevinschildhorn.fotopresenter.data.DirectoryContents
import com.kevinschildhorn.fotopresenter.data.ImageSlideshowDetails
import com.kevinschildhorn.fotopresenter.data.State
import com.kevinschildhorn.fotopresenter.data.network.NetworkDirectoryDetails
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandlerException
import com.kevinschildhorn.fotopresenter.domain.RetrieveDirectoryContentsUseCase
import com.kevinschildhorn.fotopresenter.domain.connection.DisconnectFromServerUseCase
import com.kevinschildhorn.fotopresenter.domain.directory.ChangeDirectoryUseCase
import com.kevinschildhorn.fotopresenter.domain.image.RetrieveImageDirectoriesUseCase
import com.kevinschildhorn.fotopresenter.domain.image.RetrieveImageUseCase
import com.kevinschildhorn.fotopresenter.extension.addPath
import com.kevinschildhorn.fotopresenter.ui.UiState
import com.kevinschildhorn.fotopresenter.ui.screens.common.DefaultImageViewModel
import com.kevinschildhorn.fotopresenter.ui.screens.common.ImageViewModel
import com.kevinschildhorn.fotopresenter.ui.shared.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DirectoryViewModel(
    private val logger: Logger,
) : ViewModel(),
    ImageViewModel by DefaultImageViewModel(),
    KoinComponent {
    private val _uiState = MutableStateFlow(DirectoryScreenState())
    val uiState: StateFlow<DirectoryScreenState> = _uiState.asStateFlow()

    private val _directoryContentsState = MutableStateFlow(DirectoryContents())

    private val currentPath: String
        get() = uiState.value.currentPath

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
        viewModelScope.launch {
            logger.i { "Logging Out" }
            val logoutUseCase: DisconnectFromServerUseCase by inject()
            logoutUseCase()
            logger.d { "Setting loggedIn state to false" }
            _uiState.update { it.copy(loggedIn = false) }
        }
    }

    //endregion

    //region Image

    fun startSlideshow(directoryId: Int) {
        _directoryContentsState.value.folders.find { it.id == directoryId }?.let {
            viewModelScope.launch(Dispatchers.Default) {
                val retrieveImagesUseCase: RetrieveImageDirectoriesUseCase by inject()
                val images = retrieveImagesUseCase(it.details)
                _uiState.update { it.copy(slideshowDetails = ImageSlideshowDetails(images)) }
            }
        }
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

    //endregion

    //region Directory

    fun changeDirectory(id: Int) {
        _directoryContentsState.value.allDirectories.find { it.id == id }?.let {
            changeDirectory(it.details)
        }
    }

    private fun changeDirectory(directory: NetworkDirectoryDetails) {
        logger.i { "Changing Directory: ${directory.name}" }
        viewModelScope.launch(Dispatchers.Default) {
            val changeDirectoryUseCase: ChangeDirectoryUseCase by inject()
            try {
                logger.i { "Getting New Path" }
                val newPath = changeDirectoryUseCase(currentPath.addPath(directory.name))
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
        viewModelScope.launch(Dispatchers.Default) {
            val retrieveDirectoryUseCase: RetrieveDirectoryContentsUseCase by inject()

            logger.i { "Getting Directory Contents" }
            val directoryContents = retrieveDirectoryUseCase(currentPath)
            logger.i { "Got Directory Contents: ${directoryContents.allDirectories.count()}" }
            _directoryContentsState.update { directoryContents }

            logger.i { "Updating State to Success" }
            logger.i { "Setting Directories: $directoryContents" }
            setImageDirectories(directoryContents.images)
            val gridState = directoryContents.asDirectoryGridState
            logger.i { "New Grid State $gridState" }
            _uiState.update {
                it.copy(
                    directoryGridState = gridState,
                    state = UiState.SUCCESS,
                )
            }
            logger.i { "Current State ${uiState.value.state}" }
            updatePhotos()
        }
    }

    private fun updatePhotos() {
        imageUiState.value.imageDirectories.forEach { imageDirectory ->
            viewModelScope.launch(Dispatchers.Default) {
                val retrieveImagesUseCase: RetrieveImageUseCase by inject()

                retrieveImagesUseCase(imageDirectory) { newState ->
                    _uiState.update {
                        it.copyImageState(
                            imageDirectory.id,
                            state = newState,
                        )
                    }
                }
            }
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
}
