package com.kevinschildhorn.fotopresenter.ui.viewmodel

import app.cash.turbine.test
import com.kevinschildhorn.fotopresenter.data.Path
import com.kevinschildhorn.fotopresenter.data.network.MockNetworkHandler
import com.kevinschildhorn.fotopresenter.testingModule
import com.kevinschildhorn.fotopresenter.ui.UiState
import com.kevinschildhorn.fotopresenter.ui.screens.directory.DirectoryGridCellState
import com.kevinschildhorn.fotopresenter.ui.screens.directory.DirectoryViewModel
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
import org.koin.test.inject
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

/**
Testing [DirectoryViewModel]
 **/
@OptIn(ExperimentalCoroutinesApi::class)
class DirectoryViewModelTest : KoinTest {
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
    fun `Refresh Screen`() =
        runTest(testDispatcher) {
            val viewModel: DirectoryViewModel by inject()

            viewModel.uiState.test {
                viewModel.refreshScreen()

                var state = awaitItem()
                assertEquals(UiState.IDLE, state.state)

                state = awaitItem()
                assertEquals(UiState.LOADING, state.state)

                state = awaitItem()
                assertEquals(UiState.SUCCESS, state.state)
                assertEquals(Path(""), state.currentPath)
                assertEquals(2, state.directoryGridState.imageStates.count())
                assertEquals(2, state.directoryGridState.folderStates.count())
                cancelAndIgnoreRemainingEvents()
            }
        }
/* TODO
    @Test
    fun logout() =
        runTest(testDispatcher) {
            val viewModel: DirectoryViewModel by inject()
            viewModel.uiState.test {
                var state = awaitItem()
                viewModel.logout()
                cancelAndIgnoreRemainingEvents()
            }
        }
*/
    @Test
    fun `change Directory`() =
        runTest(testDispatcher) {
            val viewModel: DirectoryViewModel by inject()
            viewModel.uiState.test {
                // Refreshing Screen
                var state = awaitItem()
                viewModel.refreshScreen()
                state = awaitItem()
                assertEquals(UiState.LOADING, state.state)
                state = awaitItem()
                assertEquals(UiState.SUCCESS, state.state)
                val firstId = state.directoryGridState.folderStates.first().id
                assertEquals(MockNetworkHandler.photoDirectoryId, firstId)

                // Changing Directory
                viewModel.changeDirectory(firstId)
                state = awaitItem()
                assertEquals(UiState.SUCCESS, state.state)
                while (state.currentPath.isEmpty) {
                    state = awaitItem()
                }
                assertEquals(Path("Photos"), state.currentPath)
                while (state.directoryGridState.folderStates.count() != 1) {
                    state = awaitItem()
                }
                assertEquals(2, state.directoryGridState.imageStates.count())
                assertEquals(1, state.directoryGridState.folderStates.count())
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `start Slideshow`() =
        runTest(testDispatcher) {
            /* TODO
            val viewModel: DirectoryViewModel by inject()
            viewModel.uiState.test {
                viewModel.refreshScreen()
                var state = awaitItem()
                assertEquals(UiState.IDLE, state.state)
                state = awaitItem()
                assertEquals(UiState.LOADING, state.state)
                state = awaitItem()
                assertEquals(UiState.SUCCESS, state.state)

                val directory =
                    DirectoryGridCellState.Folder(
                        name = "",
                        id = MockNetworkHandler.photoDirectoryId,
                    )
                viewModel.setSelectedDirectory(directory)
                viewModel.startSlideshow()
                while (state.slideshowDetails == null) {
                    state = awaitItem()
                }
                assertNotNull(state.slideshowDetails)
                while (state.slideshowDetails?.directories!!.count() != 4) {
                    state = awaitItem()
                }
                val list = state.slideshowDetails?.directories!!
                assertEquals(4, list.size)
                cancelAndIgnoreRemainingEvents()
            }*/
        }

    @Test
    fun `select Image by ID`() =
        runTest(testDispatcher) {
            val viewModel: DirectoryViewModel by inject()
            viewModel.uiState.test {
                viewModel.refreshScreen()
                var state = awaitItem()
                assertEquals(UiState.IDLE, state.state)
                state = awaitItem()
                assertEquals(UiState.LOADING, state.state)
                state = awaitItem()
                assertEquals(UiState.SUCCESS, state.state)
                cancelAndIgnoreRemainingEvents()
            }
            viewModel.imageUiState.test {
                viewModel.setSelectedImageById(75)
                var state = awaitItem()
                while (state.selectedImageIndex == null) {
                    state = awaitItem()
                }
                assertEquals(0, state.selectedImageIndex)
                cancelAndIgnoreRemainingEvents()
            }
        }
}
