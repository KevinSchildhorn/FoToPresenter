package com.kevinschildhorn.fotopresenter.ui.screens.slideshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import com.kevinschildhorn.fotopresenter.data.ImageDirectory
import com.kevinschildhorn.fotopresenter.data.ImagePreviewNavigator
import com.kevinschildhorn.fotopresenter.data.ImageSlideshowDetails
import com.kevinschildhorn.fotopresenter.data.PlaylistDetails
import com.kevinschildhorn.fotopresenter.data.network.DefaultNetworkDirectoryDetails
import com.kevinschildhorn.fotopresenter.data.repositories.DirectoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import java.util.Timer
import kotlin.concurrent.fixedRateTimer

class SlideshowViewModel(
    private val imagePreviewNavigator: ImagePreviewNavigator,
    private val directoryRepository: DirectoryRepository,
    private val logger: Logger,
) : ViewModel(),
    KoinComponent {
    val uiState: StateFlow<SlideshowScreenUiState> =
        imagePreviewNavigator.imagePreviewState
            .map { imagePreview ->
                if (imagePreview != null) {
                    SlideshowScreenUiState.Ready(selectedImageDirectory = imagePreview)
                } else {
                    SlideshowScreenUiState.Loading
                }
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = SlideshowScreenUiState.Loading,
            )

    var onGoFullScreen: () -> Unit = {}

    private var timer: Timer? = null

    fun setSlideshowFromPlaylist(playlistDetails: PlaylistDetails) {
        logger.i { "Starting playlist $playlistDetails" }

        logger.i { "Starting to get details from playlist ${playlistDetails.name}" }

        viewModelScope.launch(Dispatchers.Default) {
            val directories: List<ImageDirectory> =
                playlistDetails.items
                    .map { item ->
                        val directoryDetails =
                            DefaultNetworkDirectoryDetails(
                                id = item.directoryId,
                                fullPath = item.directoryPath,
                            )
                        if (item.directoryPath.isImagePath) {
                            listOf(
                                ImageDirectory(
                                    directoryDetails,
                                    null,
                                ),
                            )
                        } else {
                            val contents = directoryRepository.getDirectoryContents(directoryDetails.fullPath)
                            contents.images
                        }
                    }.flatten()

            val slideshow = ImageSlideshowDetails(directories)

            viewModelScope.launch(Dispatchers.Default) {
                startSlideshow(slideshow)
            }
        }
    }

    fun startSlideshow(details: ImageSlideshowDetails) {
        onGoFullScreen()
        imagePreviewNavigator.setFolderContents(details.directories)
        imagePreviewNavigator.setImageIndex(0)
        viewModelScope.launch(Dispatchers.Default) {
            startImageTimer()
        }
    }

    fun skipForward() {
        onGoFullScreen()
        stopImageTimer()
        imagePreviewNavigator.showNextImage()
        startImageTimer()
    }

    fun skipBackwards() {
        onGoFullScreen()
        stopImageTimer()
        imagePreviewNavigator.showPreviousImage()
        startImageTimer()
    }

    fun stopSlideshow() {
        stopImageTimer()
        imagePreviewNavigator.setFolderContents(emptyList())
    }

    private fun startImageTimer(seconds: Long = 5L) {
        val time = seconds * 1000
        timer =
            fixedRateTimer(period = time, initialDelay = time) {
                viewModelScope.launch(Dispatchers.Main) {
                    imagePreviewNavigator.showNextImage()
                }
            }
    }

    private fun stopImageTimer() {
        timer?.cancel()
        timer = null
    }
}
