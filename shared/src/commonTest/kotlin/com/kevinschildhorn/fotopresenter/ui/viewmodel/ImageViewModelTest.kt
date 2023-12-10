package com.kevinschildhorn.fotopresenter.ui.viewmodel

import app.cash.turbine.test
import com.kevinschildhorn.fotopresenter.data.ImageDirectory
import com.kevinschildhorn.fotopresenter.data.network.MockNetworkDirectoryDetails
import com.kevinschildhorn.fotopresenter.data.network.MockNetworkHandler
import com.kevinschildhorn.fotopresenter.testingModule
import com.kevinschildhorn.fotopresenter.ui.screens.common.DefaultImageViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.assertEquals
import kotlin.test.assertNull

/**
Testing [ImageViewModel]
 **/
@OptIn(ExperimentalCoroutinesApi::class)
class ImageViewModelTest : KoinTest {
    private val testDispatcher = StandardTestDispatcher()

    private val directories =
        listOf(
            ImageDirectory(MockNetworkDirectoryDetails("Peeng.png", 1)),
            ImageDirectory(MockNetworkDirectoryDetails("Jaypeg.jpg", 2)),
            ImageDirectory(MockNetworkDirectoryDetails("Photos/Peeng2.png", 3)),
            ImageDirectory(MockNetworkDirectoryDetails("Photos/Jaypeg2.jpg", 4)),
            ImageDirectory(MockNetworkDirectoryDetails("Photos/SubPhotos/Peeng3.png", 5)),
            ImageDirectory(MockNetworkDirectoryDetails("Photos/SubPhotos/Jaypeg3.jpg", 6)),
        )

    @BeforeTest
    fun startTest() =
        runBlocking {
            startKoin {
                modules(testingModule())
            }
            Dispatchers.setMain(testDispatcher)
            MockNetworkHandler.connectSuccessfully()
        }

    @AfterTest
    fun tearDown() =
        runBlocking {
            MockNetworkHandler.disconnect()
            stopKoin()
            Dispatchers.resetMain()
        }

    @Test
    fun `Set Selected Image`() =
        runTest(testDispatcher) {
            val viewModel = DefaultImageViewModel()
            viewModel.imageUiState.test {
                viewModel.setImageDirectories(directories)
                var state = awaitItem()
                while (state.imageDirectories.isEmpty()) {
                    state = awaitItem()
                }
                assertEquals(directories.count(), state.imageDirectories.count())
            }
        }

    @Test
    fun `Show Previous Image`() =
        runTest(testDispatcher) {
            val viewModel = DefaultImageViewModel()
            viewModel.imageUiState.test {
                viewModel.setImageDirectories(directories)
                var state = awaitItem()
                while (state.imageDirectories.isEmpty()) {
                    state = awaitItem()
                }
                assertEquals(directories.count(), state.imageDirectories.count())
                viewModel.setSelectedImage(1)
                state = awaitItem()
                assertEquals(1, state.selectedImageIndex)
                viewModel.showPreviousImage()
                state = awaitItem()
                assertEquals(0, state.selectedImageIndex)
                viewModel.showPreviousImage()
                state = awaitItem()
                assertEquals(5, state.selectedImageIndex)
            }
        }

    @Test
    fun `Show Next Image`() =
        runTest(testDispatcher) {
            val viewModel = DefaultImageViewModel()
            viewModel.imageUiState.test {
                viewModel.setImageDirectories(directories)
                var state = awaitItem()
                while (state.imageDirectories.isEmpty()) {
                    state = awaitItem()
                }
                assertEquals(directories.count(), state.imageDirectories.count())
                viewModel.setSelectedImage(4)
                state = awaitItem()
                assertEquals(4, state.selectedImageIndex)
                viewModel.showNextImage()
                state = awaitItem()
                assertEquals(5, state.selectedImageIndex)
                viewModel.showNextImage()
                state = awaitItem()
                assertEquals(0, state.selectedImageIndex)
            }
        }

    @Test
    fun `Clear Presented Image`() =
        runTest(testDispatcher) {
            val viewModel = DefaultImageViewModel()
            viewModel.imageUiState.test {
                viewModel.setImageDirectories(directories)
                var state = awaitItem()
                while (state.imageDirectories.isEmpty()) {
                    state = awaitItem()
                }
                assertEquals(directories.count(), state.imageDirectories.count())
                viewModel.setSelectedImage(4)
                state = awaitItem()
                assertEquals(4, state.selectedImageIndex)
                viewModel.clearPresentedImage()
                state = awaitItem()
                assertNull(state.selectedImageIndex)
            }
        }
}
