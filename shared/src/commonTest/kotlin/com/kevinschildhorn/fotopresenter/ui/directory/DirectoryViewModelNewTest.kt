package com.kevinschildhorn.fotopresenter.ui.directory

import app.cash.turbine.TurbineTestContext
import app.cash.turbine.test
import co.touchlab.kermit.Logger
import com.kevinschildhorn.fotopresenter.data.network.MockNetworkHandler
import com.kevinschildhorn.fotopresenter.testingModule
import com.kevinschildhorn.fotopresenter.ui.SortingType
import com.kevinschildhorn.fotopresenter.ui.UiState
import com.kevinschildhorn.fotopresenter.ui.screens.directory.DirectoryOverlayType
import com.kevinschildhorn.fotopresenter.ui.screens.directory.DirectoryOverlayUiState
import com.kevinschildhorn.fotopresenter.ui.screens.directory.DirectoryScreenUIState
import com.kevinschildhorn.fotopresenter.ui.screens.directory.DirectoryViewModelNew
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
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
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

/**
Testing [DirectoryViewModelNew]
 **/
@OptIn(ExperimentalCoroutinesApi::class)
class DirectoryViewModelNewTest : KoinTest {
    private val testDispatcher = StandardTestDispatcher()

    private val logger = Logger.withTag("DirectoryViewModelNewTest")

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
    fun onSearch() =
        runTest(testDispatcher) {
            val viewModel: DirectoryViewModelNew by inject()

            viewModel.uiState.test {
                awaitItem()
                awaitItem()
                viewModel.refreshScreen()
                var item = awaitItem()

                assertTrue(item.directoryGridUIState.imageStates.any { it.name == "Peeng" })
                assertTrue(item.directoryGridUIState.imageStates.any { it.name == "Jaypeg" })
                assertTrue(item.directoryGridUIState.folderStates.any { it.name == "Photos" })
                assertTrue(item.directoryGridUIState.folderStates.any { it.name == "NewDirectory" })

                // Searching "P"
                viewModel.onSearch("p")
                awaitItem()
                item = awaitItem()

                assertTrue(item.directoryGridUIState.imageStates.any { it.name == "Peeng" })
                assertTrue(item.directoryGridUIState.imageStates.any { it.name == "Jaypeg" })
                assertTrue(item.directoryGridUIState.folderStates.any { it.name == "Photos" })
                assertFalse(item.directoryGridUIState.folderStates.any { it.name == "NewDirectory" })

                // Searching "Pe"
                viewModel.onSearch("pe")
                awaitItem()
                item = awaitItem()

                assertTrue(item.directoryGridUIState.imageStates.any { it.name == "Peeng" })
                assertTrue(item.directoryGridUIState.imageStates.any { it.name == "Jaypeg" })
                assertFalse(item.directoryGridUIState.folderStates.any { it.name == "Photos" })
                assertFalse(item.directoryGridUIState.folderStates.any { it.name == "NewDirectory" })
            }
        }

    @Test
    fun showOverlay() =
        runTest(testDispatcher) {
            val viewModel: DirectoryViewModelNew by inject()

            viewModel.uiState.test {
                awaitItem()
                awaitItem()
                viewModel.refreshScreen()
                var item = awaitItem()
                assertTrue(item.overlayUiState is DirectoryOverlayUiState.None)

                // Show Sort
                viewModel.showOverlay(DirectoryOverlayType.SORT)
                item = awaitItem()
                assertTrue(item.overlayUiState is DirectoryOverlayUiState.Sort)

                // Clear Overlay
                viewModel.showOverlay(DirectoryOverlayType.NONE)
                item = awaitItem()
                assertTrue(item.overlayUiState is DirectoryOverlayUiState.None)

                // Show Logout
                viewModel.showOverlay(DirectoryOverlayType.LOGOUT_CONFIRMATION)
                item = awaitItem()
                assertTrue(item.overlayUiState is DirectoryOverlayUiState.LogoutConfirmation)
            }
        }

    @Test
    fun setSortType() =
        runTest(testDispatcher) {
            /**
             View [MockNetworkHandler]
             **/
            val viewModel: DirectoryViewModelNew by inject()

            viewModel.uiState.test {
                awaitItem()
                awaitItem()
                viewModel.refreshScreen()
                var item = awaitItem()

                // Assert starting in Name Ascending
                var firstImageName = item.directoryGridUIState.imageStates.first().name
                assertEquals(expected = "Jaypeg", actual = firstImageName)

                // Change Sorting to Name Descending
                viewModel.setSortType(SortingType.NAME_DESC)
                item = awaitItem()
                firstImageName = item.directoryGridUIState.imageStates.first().name
                assertEquals(expected = "Peeng", actual = firstImageName)

                // Change Sorting to Time Descending
                viewModel.setSortType(SortingType.TIME_ASC)
                item = awaitItem()
                firstImageName = item.directoryGridUIState.imageStates.first().name
                assertEquals(expected = "Jaypeg", actual = firstImageName)

                // Change Sorting to Time Ascending
                viewModel.setSortType(SortingType.TIME_DESC)
                item = awaitItem()
                firstImageName = item.directoryGridUIState.imageStates.first().name
                assertEquals(expected = "Peeng", actual = firstImageName)
            }
        }

    @Test
    fun setSelectedDirectory() =
        runTest(testDispatcher) {
            val viewModel: DirectoryViewModelNew by inject()

            viewModel.uiState.test {
                awaitItem()
                awaitItem()
                viewModel.refreshScreen()
                awaitItem()
                viewModel.setSelectedDirectory(null)
                var item = awaitItem()
                assertTrue(item.state is UiState.ERROR)
                assertEquals(
                    expected = "Selected Directory Error",
                    actual = (item.state as UiState.ERROR).message,
                )
                assertEquals(expected = DirectoryOverlayUiState.None, actual = item.overlayUiState)

                val directory = item.directoryGridUIState.imageStates.first()
                viewModel.setSelectedDirectory(directory)
                item = awaitItem()
                assertTrue(item.state is UiState.SUCCESS)
                assertTrue(item.overlayUiState is DirectoryOverlayUiState.Actions)
            }
        }

    @Test
    fun logout() =
        runTest(testDispatcher) {
            val viewModel: DirectoryViewModelNew by inject()

            assertTrue(MockNetworkHandler.isConnected)
            viewModel.logout()
            delay(1000)
            assertFalse(MockNetworkHandler.isConnected)
        }

    @Test
    fun refreshScreen() =
        runTest {
            val viewModel: DirectoryViewModelNew by inject()

            viewModel.uiState.test {
                viewModel.refreshScreen()
                awaitItem()
                var item = awaitItem()
                assertEquals(expected = UiState.SUCCESS, actual = item.state)
                assertNotEquals(item.directoryGridUIState.allStates.count(), actual = 0)
            }
        }

    @Test
    fun navigateIntoDirectory() =
        runTest(testDispatcher) {
            val viewModel: DirectoryViewModelNew by inject()
            val photosName = "Photos"
            val subPhotosName = "SubPhotos"

            viewModel.uiState.test {
                viewModel.refreshScreen()
                awaitItem()
                var item = awaitItem()
                assertEquals(expected = UiState.SUCCESS, actual = item.state)

                // Navigate To Photos
                val photoDirectory =
                    item.directoryGridUIState.folderStates.find { it.name == photosName }
                assertNotNull(photoDirectory)
                val currentState = item.directoryGridUIState
                viewModel.navigateIntoDirectory(photoDirectory.id)
                awaitItem()
                item = awaitItem()
                assertNotEquals(illegal = currentState, actual = item.directoryGridUIState)
                assertEquals(
                    expected = photosName,
                    actual = item.directoryGridUIState.currentPath.fileName,
                )
                assertEquals(
                    expected = subPhotosName,
                    actual = item.directoryGridUIState.folderStates.first().name,
                )

                // Navigate To SubPhotos
                val subPhotoDirectory =
                    item.directoryGridUIState.folderStates.find { it.name == subPhotosName }
                assertNotNull(subPhotoDirectory)
                viewModel.navigateIntoDirectory(subPhotoDirectory.id)
                item = awaitItem()
                assertEquals(
                    expected = subPhotosName,
                    actual = item.directoryGridUIState.currentPath.fileName,
                )
            }
        }

    @Test
    fun navigateBackToDirectory() =
        runTest(testDispatcher) {
            logger.i { "navigateBackToDirectory" }
            val viewModel: DirectoryViewModelNew by inject()
            val photosName = "Photos"
            val subPhotosName = "SubPhotos"
            val subSubPhotosName = "SubSubPhotos"

            viewModel.uiState.test {
                logger.i { "Refreshing Screen" }
                var item = awaitItem()
                logger.i { "Got Initial Empty State" }
                viewModel.refreshScreen()
                item = awaitItem()
                logger.i { "Got Initial State With Screen Data" }

                // Navigate To Photos
                logger.i { "NAVIGATION 1" }
                item = navigateToFolder(name = photosName, viewModel, uiState = item)

                // Navigate To SubPhotos
                logger.i { "NAVIGATION 2" }
                item = navigateToFolder(name = subPhotosName, viewModel, uiState = item)

                // Navigate To SubSubPhotos
                logger.i { "NAVIGATION 3" }
                item = navigateToFolder(name = subSubPhotosName, viewModel, uiState = item)

                // Navigate Back to SubPhotos
                logger.i { "NAVIGATION back to Directory at index 1" }
                viewModel.navigateBackToDirectory(1)
                item = awaitItem()
                assertEquals(
                    expected = subPhotosName,
                    actual = item.directoryGridUIState.currentPath.fileName,
                )

                // Navigate To SubSubPhotos
                item = navigateToFolder(name = subSubPhotosName, viewModel, uiState = item)

                // Navigate Back to Photos
                logger.i { "Navigating back to Directory at index 0" }
                viewModel.navigateBackToDirectory(0)
                item = awaitItem()
                assertEquals(
                    expected = photosName,
                    actual = item.directoryGridUIState.currentPath.fileName,
                )
                // Navigate To SubSubPhotos
                item = navigateToFolder(name = subPhotosName, viewModel, uiState = item)
                item = navigateToFolder(name = subSubPhotosName, viewModel, uiState = item)

                // Navigate Back to Home
                viewModel.navigateBackToDirectory(-1)
                item = awaitItem()
                assertEquals(
                    expected = "",
                    actual = item.directoryGridUIState.currentPath.fileName,
                )
            }
        }

    private suspend fun TurbineTestContext<DirectoryScreenUIState>.navigateToFolder(
        name: String,
        viewModel: DirectoryViewModelNew,
        uiState: DirectoryScreenUIState,
    ): DirectoryScreenUIState {
        logger.i { "navigateToFolder: $name from state:\n$uiState" }
        val newDirectory =
            uiState.directoryGridUIState.folderStates.find { it.name == name }
        logger.d { "newDirectory: $newDirectory" }
        assertNotNull(newDirectory)

        logger.d { "Navigating Into Directory" }
        viewModel.navigateIntoDirectory(newDirectory.id)
        var item = awaitItem()
        if (item.directoryGridUIState.currentPath == uiState.directoryGridUIState.currentPath) {
            logger.d { "Awaiting Again" }
            item = awaitItem()
        }
        logger.d { "Updated UiState: \n$item" }
        assertNotNull(item)
        logger.d { "asserting: '${item.directoryGridUIState.currentPath.fileName}' == '$name'" }
        assertEquals(
            expected = name,
            actual = item.directoryGridUIState.currentPath.fileName,
            message = "Was expecting $name but got '${item.directoryGridUIState.currentPath.fileName}'",
        )
        logger.d { "Returning Item" }
        return item
    }
}
