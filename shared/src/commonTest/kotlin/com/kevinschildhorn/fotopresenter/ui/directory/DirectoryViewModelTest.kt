package com.kevinschildhorn.fotopresenter.ui.directory

import app.cash.turbine.TurbineTestContext
import app.cash.turbine.test
import co.touchlab.kermit.Logger
import com.kevinschildhorn.fotopresenter.data.FolderDirectory
import com.kevinschildhorn.fotopresenter.data.Path
import com.kevinschildhorn.fotopresenter.data.network.DefaultNetworkDirectoryDetails
import com.kevinschildhorn.fotopresenter.data.network.MockNetworkHandler
import com.kevinschildhorn.fotopresenter.testingModule
import com.kevinschildhorn.fotopresenter.ui.SortingType
import com.kevinschildhorn.fotopresenter.ui.TagSearchType
import com.kevinschildhorn.fotopresenter.ui.UiState
import com.kevinschildhorn.fotopresenter.ui.screens.directory.DirectoryAdvancedSearchUIState
import com.kevinschildhorn.fotopresenter.ui.screens.directory.DirectoryOverlayType
import com.kevinschildhorn.fotopresenter.ui.screens.directory.DirectoryOverlayUiState
import com.kevinschildhorn.fotopresenter.ui.screens.directory.DirectoryScreenUIState
import com.kevinschildhorn.fotopresenter.ui.screens.directory.DirectoryViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlinx.datetime.LocalDate
import org.junit.Test
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import kotlin.String
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

/**
Testing [DirectoryViewModel]
 **/
@OptIn(ExperimentalCoroutinesApi::class)
class DirectoryViewModelTest : KoinTest {
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
            val viewModel: DirectoryViewModel by inject()

            viewModel.uiState.test {
                viewModel.refreshScreen()
                var item = awaitUntilHasDirectories()

                assertTrue(item.hasImage("Peeng"))
                assertTrue(item.hasImage("Jaypeg"))
                assertTrue(item.hasFolder("Photos"))
                assertTrue(item.hasFolder("NewDirectory"))

                // Searching "P"
                viewModel.onSearch("p")
                item = awaitItem()
                while (item.searchText != "p") {
                    item = awaitItem()
                }
                // Should only have one folder
                while (item.hasFolders(2)) {
                    item = awaitItem()
                }

                assertTrue(item.hasImage("Peeng"))
                assertTrue(item.hasImage("Jaypeg"))
                assertTrue(item.hasFolder("Photos"))
                assertFalse(item.hasFolder("NewDirectory"))

                // Searching "Pe"
                viewModel.onSearch("pe")
                item = awaitItem()
                while (item.searchText != "pe") {
                    item = awaitItem()
                }
                // Should not have any folders at this point
                while (!item.hasNoFolders()) {
                    item = awaitItem()
                }

                assertTrue(item.hasImage("Peeng"))
                assertTrue(item.hasImage("Jaypeg"))
                assertFalse(item.hasFolder("Photos"))
                assertFalse(item.hasFolder("NewDirectory"))
            }
        }

    @Test
    fun advancedSearchDate() = runTest(testDispatcher) {
        val viewModel: DirectoryViewModel by inject()

        viewModel.uiState.test {
            viewModel.refreshScreen()
            var item = awaitUntilHasDirectories()

            assertEquals(2, item.directoryGridUIState.imageStates.count())
            assertTrue(item.hasImage("Peeng"))
            assertTrue(item.hasImage("Jaypeg"))

            // No Search
            viewModel.setAdvancedSearch(
                tags = emptyList(),
                searchType = TagSearchType.ALL_TAGS,
                recursive = false,
                startDate = null,
                endDate = null,
            )
            item = awaitItem()
            while (item.directoryAdvancedSearchUIState is DirectoryAdvancedSearchUIState.LOADING) {
                item = awaitItem()
            }
            // Should only have one folder
            assertTrue(item.directoryAdvancedSearchUIState is DirectoryAdvancedSearchUIState.SUCCESS)
            var searchState = item.directoryAdvancedSearchUIState
            assertEquals(2, searchState.itemCount)



            // Searching Range
            viewModel.setAdvancedSearch(
                tags = emptyList(),
                searchType = TagSearchType.ALL_TAGS,
                recursive = false,
                startDate = LocalDate(2020, 1, 1),
                endDate = LocalDate(2024, 5, 15),
            )
            item = awaitItem()
            while (item.directoryAdvancedSearchUIState is DirectoryAdvancedSearchUIState.LOADING) {
                item = awaitItem()
            }
            // Should only have one folder
            assertTrue(item.directoryAdvancedSearchUIState is DirectoryAdvancedSearchUIState.SUCCESS)
            searchState = item.directoryAdvancedSearchUIState
            assertEquals(1, searchState.itemCount)
        }
    }
    @Test
    fun showOverlay() =
        runTest(testDispatcher) {
            val viewModel: DirectoryViewModel by inject()

            viewModel.uiState.test {
                viewModel.refreshScreen()
                var item = awaitUntilHasDirectories()
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
            val viewModel: DirectoryViewModel by inject()

            viewModel.uiState.test {
                viewModel.refreshScreen()
                var item = awaitUntilHasDirectories()

                // Assert starting in Name Ascending
                assertEquals(expected = "Jaypeg", actual = item.firstImageName)

                // Change Sorting to Name Descending
                viewModel.setSortType(SortingType.NAME_DESC)
                item = awaitItem()
                assertEquals(expected = "Peeng", actual = item.firstImageName)

                // Change Sorting to Time Descending
                viewModel.setSortType(SortingType.TIME_ASC)
                item = awaitItem()
                assertEquals(expected = "Jaypeg", actual = item.firstImageName)

                // Change Sorting to Time Ascending
                viewModel.setSortType(SortingType.TIME_DESC)
                item = awaitItem()
                assertEquals(expected = "Peeng", actual = item.firstImageName)
            }
        }

    @Test
    fun setSelectedDirectory() =
        runTest(testDispatcher) {
            val viewModel: DirectoryViewModel by inject()

            viewModel.uiState.test {
                viewModel.refreshScreen()
                var item = awaitUntilHasDirectories()

                viewModel.setSelectedDirectory(null)
                item = awaitItem()
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
            val viewModel: DirectoryViewModel by inject()
            logger.i { "Checking if connected" }
            assertTrue(MockNetworkHandler.isConnected)
            logger.i { "Logging out" }
            viewModel.logout()
            delay(5000)
            logger.i { "Assert Disconnected" }
            assertFalse(MockNetworkHandler.isConnected)
        }

    @Test
    fun refreshScreen() =
        runTest {
            val viewModel: DirectoryViewModel by inject()

            viewModel.uiState.test {
                viewModel.refreshScreen()
                var item = awaitUntilHasDirectories()
                assertEquals(expected = UiState.SUCCESS, actual = item.state)
                assertNotEquals(item.directoryGridUIState.allStates.count(), actual = 0)
            }
        }

    @Test
    fun navigateIntoDirectory() =
        runTest(testDispatcher) {
            val viewModel: DirectoryViewModel by inject()
            val photosName = "Photos"
            val subPhotosName = "SubPhotos"

            viewModel.uiState.test {
                viewModel.refreshScreen()
                var item = awaitUntilHasDirectories()
                assertEquals(expected = UiState.SUCCESS, actual = item.state)

                // Navigate To Photos
                val photoDirectory = item.findDirectory(photosName)
                assertNotNull(photoDirectory)
                val currentState = item.directoryGridUIState
                viewModel.navigateIntoDirectory(photoDirectory.id)
                item = awaitItem()
                while (!item.isPath(photosName)) {
                    item = awaitItem()
                }
                while (!item.hasFolder(subPhotosName)) {
                    item = awaitItem()
                }
                assertNotEquals(illegal = currentState, actual = item.directoryGridUIState)
                assertEquals(expected = photosName, actual = item.pathName)
                assertTrue(
                    item.hasFolder(subPhotosName),
                    message = item.directoryGridUIState.folderStates.joinToString(","),
                )

                // Navigate To SubPhotos
                val subPhotoDirectory =
                    item.directoryGridUIState.folderStates.find { it.name == subPhotosName }
                assertNotNull(subPhotoDirectory)
                viewModel.navigateIntoDirectory(subPhotoDirectory.id)
                item = awaitItem()
                while (!item.isPath(subPhotosName)) {
                    item = awaitItem()
                }

                assertEquals(
                    expected = subPhotosName,
                    actual = item.pathName,
                )
            }
        }

    @Test
    fun navigateBackToDirectory() =
        runTest(testDispatcher) {
            logger.i { "navigateBackToDirectory" }
            val viewModel: DirectoryViewModel by inject()
            val photosName = "Photos"
            val subPhotosName = "SubPhotos"
            val subSubPhotosName = "SubSubPhotos"

            viewModel.uiState.test {
                logger.i { "Refreshing Screen" }
                viewModel.refreshScreen()
                var item = awaitUntilHasDirectories()
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
                    actual = item.pathName,
                )

                // Navigate To SubSubPhotos
                item = navigateToFolder(name = subSubPhotosName, viewModel, uiState = item)

                // Navigate Back to Photos
                logger.i { "Navigating back to Directory at index 0" }
                viewModel.navigateBackToDirectory(0)
                item = awaitItem()
                assertEquals(
                    expected = photosName,
                    actual = item.pathName,
                )
                // Navigate To SubSubPhotos
                item = navigateToFolder(name = subPhotosName, viewModel, uiState = item)
                item = navigateToFolder(name = subSubPhotosName, viewModel, uiState = item)

                // Navigate Back to Home
                viewModel.navigateBackToDirectory(-1)
                item = awaitItem()
                assertEquals(
                    expected = "",
                    actual = item.pathName,
                )
            }
        }

    @Test
    fun startSlideShow() =
        runTest(testDispatcher) {
            logger.i { "startSlideShow" }
            val viewModel: DirectoryViewModel by inject()

            viewModel.uiState.test {
                logger.i { "Refreshing Screen" }
                viewModel.refreshScreen()
                var item = awaitUntilHasDirectories()
                logger.i { "Got Initial State With Screen Data" }

                val directory = FolderDirectory(DefaultNetworkDirectoryDetails(Path("Photos"), 1L))
                viewModel.startSlideShow(directory, withSubPhotos = false)

                while (item.slideshowDetails == null) {
                    item = awaitItem()
                }
                assertNotNull(item.slideshowDetails)
                assertEquals(2, item.slideshowDetails?.directories?.count())
                assertTrue(item.slideshowDetails?.directories?.any { it.name == "Peeng2" } ?: false)
                println(item)
            }
        }

    @Test
    fun startSlideShowWithSubPhotos() =
        runTest(testDispatcher) {
            logger.i { "startSlideShow" }
            val viewModel: DirectoryViewModel by inject()

            viewModel.uiState.test {
                logger.i { "Refreshing Screen" }
                viewModel.refreshScreen()
                var item = awaitUntilHasDirectories()
                logger.i { "Got Initial State With Screen Data" }

                val directory = FolderDirectory(DefaultNetworkDirectoryDetails(Path("Photos"), 1L))
                viewModel.startSlideShow(directory, withSubPhotos = true)

                while (item.slideshowDetails == null) {
                    item = awaitItem()
                }
                assertNotNull(item.slideshowDetails)
                assertEquals(2, item.slideshowDetails?.directories?.count())
                assertTrue(item.slideshowDetails?.directories?.any { it.name == "Peeng2" } ?: false)
                println(item)
            }
        }

    private suspend fun TurbineTestContext<DirectoryScreenUIState>.awaitUntilHasDirectories(): DirectoryScreenUIState {
        var item = awaitItem()
        while (item.hasNoDirectories()) {
            item = awaitItem()
        }
        return item
    }

    private suspend fun TurbineTestContext<DirectoryScreenUIState>.navigateToFolder(
        name: String,
        viewModel: DirectoryViewModel,
        uiState: DirectoryScreenUIState,
    ): DirectoryScreenUIState {
        logger.i { "navigateToFolder: $name from state:\n$uiState" }
        val newDirectory = uiState.findDirectory(name)
        logger.d { "newDirectory: $newDirectory" }
        assertNotNull(newDirectory)

        logger.d { "Navigating Into Directory" }
        viewModel.navigateIntoDirectory(newDirectory.id)
        var item = awaitItem()
        if (item.directoryGridUIState.currentPath == uiState.directoryGridUIState.currentPath) {
            logger.d { "Awaiting Again" }
            item = awaitItem()
        }
        if (item.isSame(uiState)) {
            logger.d { "Awaiting Again" }
            item = awaitItem()
        }

        logger.d { "Updated UiState: \n$item" }
        assertNotNull(item)
        logger.d { "asserting: '${item.pathName}' == '$name'" }
        assertEquals(
            expected = name,
            actual = item.pathName,
            message = "Was expecting $name but got '${item.pathName}'",
        )
        logger.d { "Returning Item" }
        return item
    }

    private val DirectoryScreenUIState.pathName: String
        get() = this.directoryGridUIState.currentPath.fileName

    private val DirectoryScreenUIState.firstImageName: String
        get() = this.directoryGridUIState.imageStates.first().name

    private fun DirectoryScreenUIState.isPath(path: String) = this.pathName == path

    private fun DirectoryScreenUIState.hasFolder(folderName: String) = this.directoryGridUIState.folderStates.any { it.name == folderName }

    private fun DirectoryScreenUIState.hasImage(imageName: String) = this.directoryGridUIState.imageStates.any { it.name == imageName }

    private fun DirectoryScreenUIState.hasFolders(count: Int) = this.directoryGridUIState.folderStates.count() == count

    private fun DirectoryScreenUIState.hasNoFolders() = this.directoryGridUIState.folderStates.isEmpty()

    private fun DirectoryScreenUIState.hasNoDirectories() = this.directoryGridUIState.allStates.isEmpty()

    private fun DirectoryScreenUIState.findDirectory(name: String) = this.directoryGridUIState.folderStates.find { it.name == name }

    private fun DirectoryScreenUIState.isSame(state: DirectoryScreenUIState) =
        this.directoryGridUIState.allStates.map { it.name }
            .containsAll(state.directoryGridUIState.allStates.map { it.name })
}
