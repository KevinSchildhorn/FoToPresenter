package com.kevinschildhorn.fotopresenter.ui.viewmodel

import co.touchlab.kermit.Logger
import com.kevinschildhorn.fotopresenter.data.DirectoryContents
import com.kevinschildhorn.fotopresenter.data.State
import com.kevinschildhorn.fotopresenter.data.network.NetworkDirectoryDetails
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandlerException
import com.kevinschildhorn.fotopresenter.domain.directory.ChangeDirectoryUseCase
import com.kevinschildhorn.fotopresenter.domain.connection.LogoutUseCase
import com.kevinschildhorn.fotopresenter.domain.directory.RetrieveDirectoryContentsUseCase
import com.kevinschildhorn.fotopresenter.domain.image.RetrieveImageUseCase
import com.kevinschildhorn.fotopresenter.extension.addPath
import com.kevinschildhorn.fotopresenter.ui.state.DirectoryGridState
import com.kevinschildhorn.fotopresenter.ui.state.DirectoryScreenState
import com.kevinschildhorn.fotopresenter.ui.state.FolderDirectoryGridCellState
import com.kevinschildhorn.fotopresenter.ui.state.ImageDirectoryGridCellState
import com.kevinschildhorn.fotopresenter.ui.state.UiState
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
) : ViewModel(), KoinComponent {
    private val _uiState = MutableStateFlow(DirectoryScreenState())
    val uiState: StateFlow<DirectoryScreenState> = _uiState.asStateFlow()

    private val _directoryContentsState = MutableStateFlow(DirectoryContents())

    private val currentPath: String
        get() = uiState.value.currentPath

    fun refreshScreen() {
        updateDirectories()
    }

    fun setLoggedIn() {
        _uiState.update { it.copy(loggedIn = true) }
    }

    fun logout() {
        viewModelScope.launch(Dispatchers.Default) {
            val logoutUseCase: LogoutUseCase by inject()
            logoutUseCase()
            _uiState.update { it.copy(loggedIn = false) }
        }
    }

    fun startSlideshow(directoryId:Int){
        _directoryContentsState.value.folders.find { it.id == directoryId }?.let {
            it.details.fullPath
        }
    }

    //region Image

    fun showPreviousImage() {
        val newIndex = _uiState.value.getPreviousImageIndex()
        _uiState.update { it.copy(selectedImageIndex = newIndex) }
        updateSelectedImage()
    }

    fun showNextImage() {
        val newIndex = _uiState.value.getNextImageIndex()
        _uiState.update { it.copy(selectedImageIndex = newIndex) }
        updateSelectedImage()
    }

    fun clearPresentedImage() {
        _uiState.update { it.copy(selectedImage = null, selectedImageIndex = null) }
    }

    fun setSelectedImageById(imageId: Int?) {
        var index: Int? = null
        imageId?.let {
            index = _uiState.value.getImageIndexFromId(it)
        }
        _uiState.update { it.copy(selectedImageIndex = index) }
        updateSelectedImage()
    }

    private fun updateSelectedImage() {
        _uiState.value.getImageStateByIndex()?.let { state ->
            _uiState.update { it.copy(selectedImage = state.value) }
        } ?: run {
            _uiState.update { it.copy(selectedImage = null) }
        }
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
            _uiState.update {
                it.copy(
                    directoryGridState = directoryContents.asDirectoryGridState,
                    state = UiState.SUCCESS,
                )
            }
            updatePhotos()
        }
    }

    private fun updatePhotos() {
        _directoryContentsState.value.images.forEach { imageDirectory ->
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
