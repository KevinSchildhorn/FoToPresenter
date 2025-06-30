package com.kevinschildhorn.fotopresenter.ui.directory

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.runComposeUiTest
import co.touchlab.kermit.Logger
import com.kevinschildhorn.fotopresenter.data.network.MockNetworkHandler
import com.kevinschildhorn.fotopresenter.onNodeWithTag
import com.kevinschildhorn.fotopresenter.testingModule
import com.kevinschildhorn.fotopresenter.ui.SortingType
import com.kevinschildhorn.fotopresenter.ui.TestTags
import com.kevinschildhorn.fotopresenter.ui.screens.directory.DirectoryScreen
import com.kevinschildhorn.fotopresenter.ui.screens.directory.DirectoryViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject

/**
Testing [DirectoryScreen], specifically navigating the Screen
Use Cases: [https://github.com/KevinSchildhorn/FoToPresenter/blob/main/docs/UX/USE_CASES_BROWSING.md]
Gets data from [MockNetworkHandler]
 **/
@OptIn(ExperimentalTestApi::class, ExperimentalCoroutinesApi::class)
class DirectoryScreenTestNav : KoinTest {
    private val viewModel: DirectoryViewModel by inject()
    private val logger = Logger.withTag("DirectoryScreenTestNav")

    @Before
    fun startTest() {
        startKoin {
            modules(testingModule())
        }
        runBlocking {
            MockNetworkHandler.connectSuccessfully()
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun navigation() =
        runComposeUiTest {
            logger.i { "navigation test" }
            Dispatchers.setMain(Dispatchers.IO)

            setContent {
                DirectoryScreen(
                    viewModel = viewModel,
                    onLogout = {},
                    onStartSlideshow = {},
                    onShowPlaylists = {},
                )
            }
            viewModel.setSortType(SortingType.NAME_ASC)

            // Initial State
            logger.i { "Asserting initial UI State" }
            onNodeWithTag(TestTags.Directory.NAVIGATION_BAR).assertExists()
            onNodeWithTag(TestTags.Directory.TopBar.TOP_BAR).assertExists()
            onNodeWithTag(TestTags.Directory.NavigationRail.NAVIGATION_RAIL).assertExists()
            onNodeWithTag("NavItemHome").assertExists()

            /** See [MockNetworkHandler.networkContents] */
            logger.i { "Asserting initial Directory UI State" }
            onNodeWithTag(TestTags.Directory.DIRECTORY(0, "NewDirectory")).assertExists()
            onNodeWithTag(TestTags.Directory.DIRECTORY(1, "Photos")).assertExists()
            onNodeWithTag(TestTags.Directory.DIRECTORY(2, "Jaypeg")).assertExists()
            onNodeWithTag(TestTags.Directory.DIRECTORY(3, "Peeng")).assertExists()
            onNodeWithTag(TestTags.Directory.DIRECTORY(4, "textFile")).assertDoesNotExist()

            // Going Forward
            logger.i { "Clicking on Photos" }
            val directory = "Photos"
            onNodeWithTag(TestTags.Directory.DIRECTORY(1, directory)).performClick()
            waitForIdle()
            onNodeWithTag("NavItem$directory").assertExists()
            onNodeWithTag(TestTags.Directory.DIRECTORY(1, "Jaypeg2")).assertExists()
            onNodeWithTag(TestTags.Directory.DIRECTORY(2, "Peeng2")).assertExists()
            onNodeWithTag(TestTags.Directory.DIRECTORY(3, "textFile2")).assertDoesNotExist()

            // Going Forward Again
            logger.i { "Clicking on SubPhotos" }
            val subDirectory = "SubPhotos"
            onNodeWithTag(TestTags.Directory.DIRECTORY(0, subDirectory)).performClick()
            waitForIdle()
            onNodeWithTag("NavItem$directory").assertExists()
            onNodeWithTag("NavItem$subDirectory").assertExists()
            val directoryName = TestTags.Directory.DIRECTORY(2, "Peeng3")
            onNodeWithTag(directoryName).assertExists()

            // Going Backward
            logger.i { "Clicking back to $directory on NavItem" }
            onNodeWithTag("NavItem$directory").performClick()
            waitForIdle()
            onNodeWithTag("NavItem$directory").assertExists()
            onNodeWithTag(TestTags.Directory.DIRECTORY(1, "Jaypeg2")).assertExists()
            onNodeWithTag(TestTags.Directory.DIRECTORY(2, "Peeng2")).assertExists()
            onNodeWithTag(TestTags.Directory.DIRECTORY(3, "textFile2")).assertDoesNotExist()
        }

    @Test
    fun searching() =
        runComposeUiTest {
            Dispatchers.setMain(Dispatchers.IO)

            setContent {
                DirectoryScreen(
                    viewModel = viewModel,
                    onLogout = {},
                    onStartSlideshow = {},
                    onShowPlaylists = {},
                )
            }

            onNodeWithTag(TestTags.Directory.DIRECTORY(0, "NewDirectory")).assertExists()
            onNodeWithTag(TestTags.Directory.DIRECTORY(1, "Photos")).assertExists()
            onNodeWithTag(TestTags.Directory.DIRECTORY(2, "Jaypeg")).assertExists()
            onNodeWithTag(TestTags.Directory.DIRECTORY(3, "Peeng")).assertExists()
            onNodeWithTag(TestTags.Directory.DIRECTORY(4, "textFile")).assertDoesNotExist()

            onNodeWithTag(TestTags.Directory.TopBar.SEARCH_BAR).assertExists().performTextInput("pe")
            onNodeWithTag(TestTags.Directory.DIRECTORY(0, "Jaypeg")).assertExists()
            onNodeWithTag(TestTags.Directory.DIRECTORY(1, "Peeng")).assertExists()
        }

    @Test
    fun sorting() =
        runComposeUiTest {
            /**
             * See [MockNetworkHandler.networkContents]
             **/
            Dispatchers.setMain(Dispatchers.IO)

            setContent {
                DirectoryScreen(
                    viewModel = viewModel,
                    onLogout = {},
                    onStartSlideshow = {},
                    onShowPlaylists = {},
                )
            }

            // Original View, should be A-Z
            onNodeWithTag(TestTags.Directory.DIRECTORY(0, "NewDirectory")).assertExists()
            onNodeWithTag(TestTags.Directory.DIRECTORY(1, "Photos")).assertExists()
            onNodeWithTag(TestTags.Directory.DIRECTORY(2, "Jaypeg")).assertExists()
            onNodeWithTag(TestTags.Directory.DIRECTORY(3, "Peeng")).assertExists()
            onNodeWithTag(TestTags.Directory.DIRECTORY(4, "textFile")).assertDoesNotExist()

            // Open Sort Dialog and Change Sorting
            onNodeWithTag(TestTags.Directory.TopBar.SORT).assertExists().performClick()
            waitForIdle()
            onNodeWithTag(TestTags.FOTO_DIALOG).assertExists()
            onNodeWithTag(TestTags.Directory.Sort.SORT_Z_TO_A).assertExists().performClick()
            onNodeWithTag(TestTags.CONFIRM).assertExists().performClick()
            waitForIdle()

            // Check Newly Sorted Directories
            onNodeWithTag(TestTags.Directory.Sort.SORT_A_TO_Z).assertDoesNotExist()
            onNodeWithTag(TestTags.Directory.DIRECTORY(0, "Photos")).assertExists()
            onNodeWithTag(TestTags.Directory.DIRECTORY(1, "NewDirectory")).assertExists()
            onNodeWithTag(TestTags.Directory.DIRECTORY(2, "Peeng")).assertExists()
            onNodeWithTag(TestTags.Directory.DIRECTORY(3, "Jaypeg")).assertExists()
            onNodeWithTag(TestTags.Directory.DIRECTORY(4, "textFile")).assertDoesNotExist()

            // Open Sort Dialog and Change Sorting
            onNodeWithTag(TestTags.Directory.TopBar.SORT).assertExists().performClick()
            waitForIdle()
            onNodeWithTag(TestTags.FOTO_DIALOG).assertExists()
            onNodeWithTag(TestTags.Directory.Sort.SORT_TIME_CREATED_ASC).assertExists().performClick()
            onNodeWithTag(TestTags.CONFIRM).assertExists().performClick()
            waitForIdle()

            // Check Newly Sorted Directories
            onNodeWithTag(TestTags.Directory.Sort.SORT_TIME_CREATED_ASC).assertDoesNotExist()
            onNodeWithTag(TestTags.Directory.DIRECTORY(0, "Photos")).assertExists() // Day 1
            onNodeWithTag(TestTags.Directory.DIRECTORY(1, "NewDirectory")).assertExists() // Day 23
            onNodeWithTag(TestTags.Directory.DIRECTORY(2, "Jaypeg")).assertExists() // Day 10
            onNodeWithTag(TestTags.Directory.DIRECTORY(3, "Peeng")).assertExists() // Day 20

            // Open Sort Dialog and Change Sorting
            onNodeWithTag(TestTags.Directory.TopBar.SORT).assertExists().performClick()
            waitForIdle()
            onNodeWithTag(TestTags.FOTO_DIALOG).assertExists()
            onNodeWithTag(TestTags.Directory.Sort.SORT_TIME_CREATED_DES).assertExists().performClick()
            onNodeWithTag(TestTags.CONFIRM).assertExists().performClick()
            waitForIdle()

            // Check Newly Sorted Directories
            onNodeWithTag(TestTags.Directory.Sort.SORT_TIME_CREATED_ASC).assertDoesNotExist()
            onNodeWithTag(TestTags.Directory.DIRECTORY(0, "NewDirectory")).assertExists()
            onNodeWithTag(TestTags.Directory.DIRECTORY(1, "Photos")).assertExists()
            onNodeWithTag(TestTags.Directory.DIRECTORY(2, "Peeng")).assertExists()
            onNodeWithTag(TestTags.Directory.DIRECTORY(3, "Jaypeg")).assertExists()
        }
}
