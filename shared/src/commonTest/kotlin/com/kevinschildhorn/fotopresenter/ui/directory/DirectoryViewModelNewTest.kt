package com.kevinschildhorn.fotopresenter.ui.directory

import androidx.lifecycle.viewModelScope
import app.cash.turbine.test
import com.kevinschildhorn.fotopresenter.data.network.MockNetworkHandler
import com.kevinschildhorn.fotopresenter.testingModule
import com.kevinschildhorn.fotopresenter.ui.SortingType
import com.kevinschildhorn.fotopresenter.ui.UiState
import com.kevinschildhorn.fotopresenter.ui.screens.directory.DirectoryGridCellUIState
import com.kevinschildhorn.fotopresenter.ui.screens.directory.DirectoryOverlayType
import com.kevinschildhorn.fotopresenter.ui.screens.directory.DirectoryOverlayUiState
import com.kevinschildhorn.fotopresenter.ui.screens.directory.DirectoryViewModelNew
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Test
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import kotlin.io.path.Path
import kotlin.math.acos
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull

/**
Testing [DirectoryViewModelNew]
 **/
@OptIn(ExperimentalCoroutinesApi::class)
class DirectoryViewModelNewTest : KoinTest {
    private val testDispatcher = StandardTestDispatcher()

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
    fun onSearch() = runTest(testDispatcher) {
        val viewModel: DirectoryViewModelNew by inject()

    }

    @Test
    fun showOverlay() = runTest(testDispatcher) {
        val viewModel: DirectoryViewModelNew by inject()

    }

    @Test
    fun setSortType() = runTest(testDispatcher) {
        val viewModel: DirectoryViewModelNew by inject()

    }

    @Test
    fun setSelectedDirectory() = runTest(testDispatcher) {
        val viewModel: DirectoryViewModelNew by inject()

    }

    @Test
    fun logout() = runTest(testDispatcher) {
        val viewModel: DirectoryViewModelNew by inject()

    }

    @Test
    fun refreshScreen() = runTest {
        val viewModel: DirectoryViewModelNew by inject()

        viewModel.uiState.test {
            viewModel.refreshScreen()
            var item = awaitItem()
            item = awaitItem()
            assertEquals(expected = UiState.SUCCESS, actual = item.state)
            assertNotEquals(item.directoryGridUIState.allStates.count(), actual = 0)
        }
    }

    @Test
    fun navigateIntoDirectory() = runTest(testDispatcher) {
        val viewModel: DirectoryViewModelNew by inject()
        val photosName = "Photos"
        val subPhotosName = "SubPhotos"

        viewModel.uiState.test {
            viewModel.refreshScreen()
            var item = awaitItem()
            item = awaitItem()
            assertEquals(expected = UiState.SUCCESS, actual = item.state)

            // Navigate To Photos
            val photoDirectory = item.directoryGridUIState.folderStates.find { it.name == photosName }
            assertNotNull(photoDirectory)
            val currentState = item.directoryGridUIState
            viewModel.navigateIntoDirectory(photoDirectory.id)
            item = awaitItem()
            item = awaitItem()
            assertNotEquals(illegal = currentState, actual = item.directoryGridUIState)
            assertEquals(
                expected = photosName,
                actual = item.directoryGridUIState.currentPath.fileName
            )
            assertEquals(
                expected = subPhotosName,
                actual = item.directoryGridUIState.folderStates.first().name,
            )

            // Navigate To SubPhotos
            val subPhotoDirectory = item.directoryGridUIState.folderStates.find { it.name == subPhotosName }
            assertNotNull(subPhotoDirectory)
            viewModel.navigateIntoDirectory(subPhotoDirectory.id)
            item = awaitItem()
            assertEquals(
                expected = subPhotosName,
                actual = item.directoryGridUIState.currentPath.fileName
            )
        }
    }

    @Test
    fun navigateBackToDirectory() = runTest(testDispatcher) {
        val viewModel: DirectoryViewModelNew by inject()
        val photosName = "Photos"
        val subPhotosName = "SubPhotos"
        val subSubPhotosName = "SubSubPhotos"

        viewModel.uiState.test {
            viewModel.refreshScreen()
            var item = awaitItem()
            item = awaitItem()

            // Navigate To Photos
            val photoDirectory = item.directoryGridUIState.folderStates.find { it.name == photosName }
            assertNotNull(photoDirectory)
            viewModel.navigateIntoDirectory(photoDirectory.id)
            item = awaitItem()
            item = awaitItem()

            // Navigate To SubPhotos
            val subPhotoDirectory = item.directoryGridUIState.folderStates.find { it.name == subPhotosName }
            assertNotNull(subPhotoDirectory)
            viewModel.navigateIntoDirectory(subPhotoDirectory.id)
            item = awaitItem()
            assertEquals(
                expected = subPhotosName,
                actual = item.directoryGridUIState.currentPath.fileName
            )

            viewModel.navigateBackToDirectory(0)
            item = awaitItem()
            assertEquals(
                expected = photosName,
                actual = item.directoryGridUIState.currentPath.fileName
            )
        }
    }

}