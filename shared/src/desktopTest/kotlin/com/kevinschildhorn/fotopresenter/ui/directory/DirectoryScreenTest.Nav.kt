package com.kevinschildhorn.fotopresenter.ui.directory

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runComposeUiTest
import com.kevinschildhorn.fotopresenter.data.network.MockNetworkHandler
import com.kevinschildhorn.fotopresenter.onNodeWithTag
import com.kevinschildhorn.fotopresenter.testingModule
import com.kevinschildhorn.fotopresenter.ui.TestTags
import com.kevinschildhorn.fotopresenter.ui.screens.directory.DirectoryScreen
import com.kevinschildhorn.fotopresenter.ui.screens.directory.DirectoryViewModelNew
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
    private val viewModel: DirectoryViewModelNew by inject()

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
    fun navigation() = runComposeUiTest {
        Dispatchers.setMain(Dispatchers.IO)

        setContent {
            DirectoryScreen(
                viewModel = viewModel,
                onLogout = {},
                onStartSlideshow = {},
                onShowPlaylists = {},
            )
        }

        // Initial State
        onNodeWithTag(TestTags.Directory.NAVIGATION_BAR).assertExists()
        onNodeWithTag(TestTags.Directory.TopBar.TOP_BAR).assertExists()
        onNodeWithTag(TestTags.Directory.NavigationRail.NAVIGATION_RAIL).assertExists()
        onNodeWithTag("NavItemHome").assertExists()

        /** See [MockNetworkHandler.networkContents] */
        onNodeWithTag(TestTags.Directory.DIRECTORY(0, "NewDirectory")).assertExists()
        onNodeWithTag(TestTags.Directory.DIRECTORY(1,"Photos")).assertExists()
        onNodeWithTag(TestTags.Directory.DIRECTORY(2,"Jaypeg")).assertExists()
        onNodeWithTag(TestTags.Directory.DIRECTORY(3,"Peeng")).assertExists()
        onNodeWithTag(TestTags.Directory.DIRECTORY(4,"textFile")).assertDoesNotExist()

        // Going Forward
        val directory = "Photos"
        onNodeWithTag(TestTags.Directory.DIRECTORY(1,directory)).performClick()
        waitForIdle()
        onNodeWithTag("NavItem$directory").assertExists()
        onNodeWithTag(TestTags.Directory.DIRECTORY(1,"Jaypeg2")).assertExists()
        onNodeWithTag(TestTags.Directory.DIRECTORY(2,"Peeng2")).assertExists()
        onNodeWithTag(TestTags.Directory.DIRECTORY(3,"textFile2")).assertDoesNotExist()

        // Going Forward Again
        val subDirectory = "SubPhotos"
        onNodeWithTag(TestTags.Directory.DIRECTORY(0,subDirectory)).performClick()
        waitForIdle()
        onNodeWithTag("NavItem$directory").assertExists()
        onNodeWithTag("NavItem$subDirectory").assertExists()
        onNodeWithTag(TestTags.Directory.DIRECTORY(1,"Peeng3")).assertExists()

        // Going Backward
        onNodeWithTag("NavItem$directory").performClick()
        waitForIdle()
        onNodeWithTag("NavItem$directory").assertExists()
        onNodeWithTag(TestTags.Directory.DIRECTORY(1,"Jaypeg2")).assertExists()
        onNodeWithTag(TestTags.Directory.DIRECTORY(2,"Peeng2")).assertExists()
        onNodeWithTag(TestTags.Directory.DIRECTORY(3,"textFile2")).assertDoesNotExist()
    }

    // TODO: Implement Test
    @Test
    fun searching() = runComposeUiTest {
        Dispatchers.setMain(Dispatchers.IO)

        setContent {
            DirectoryScreen(
                viewModel = viewModel,
                onLogout = {},
                onStartSlideshow = {},
                onShowPlaylists = {},
            )
        }
        // TODO: This is not implemented
    }

    /**
     * See [MockNetworkHandler.networkContents]
     **/
    @Test
    fun sorting() = runComposeUiTest {
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
        onNodeWithTag(TestTags.Directory.DIRECTORY(0,"NewDirectory")).assertExists()
        onNodeWithTag(TestTags.Directory.DIRECTORY(1,"Photos")).assertExists()
        onNodeWithTag(TestTags.Directory.DIRECTORY(2,"Jaypeg")).assertExists()
        onNodeWithTag(TestTags.Directory.DIRECTORY(3,"Peeng")).assertExists()
        onNodeWithTag(TestTags.Directory.DIRECTORY(4,"textFile")).assertDoesNotExist()

        // Open Sort Dialog and Change Sorting
        onNodeWithTag(TestTags.Directory.TopBar.OPTIONS).assertExists().performClick()
        waitForIdle()
        onNodeWithTag(TestTags.FOTO_DIALOG).assertExists()
        onNodeWithTag(TestTags.Directory.Sort.SORT_Z_TO_A).assertExists().performClick()
        onNodeWithTag(TestTags.CONFIRM).assertExists().performClick()
        waitForIdle()

        // Check Newly Sorted Directories
        onNodeWithTag(TestTags.Directory.Sort.SORT_A_TO_Z).assertDoesNotExist()
        onNodeWithTag(TestTags.Directory.DIRECTORY(0,"Photos")).assertExists()
        onNodeWithTag(TestTags.Directory.DIRECTORY(1,"NewDirectory")).assertExists()
        onNodeWithTag(TestTags.Directory.DIRECTORY(2,"Peeng")).assertExists()
        onNodeWithTag(TestTags.Directory.DIRECTORY(3,"Jaypeg")).assertExists()
        onNodeWithTag(TestTags.Directory.DIRECTORY(4,"textFile")).assertDoesNotExist()

        // Open Sort Dialog and Change Sorting
        onNodeWithTag(TestTags.Directory.TopBar.OPTIONS).assertExists().performClick()
        waitForIdle()
        onNodeWithTag(TestTags.FOTO_DIALOG).assertExists()
        onNodeWithTag(TestTags.Directory.Sort.SORT_TIME_CREATED_ASC).assertExists().performClick()
        onNodeWithTag(TestTags.CONFIRM).assertExists().performClick()
        waitForIdle()

        // Check Newly Sorted Directories
        onNodeWithTag(TestTags.Directory.Sort.SORT_TIME_CREATED_ASC).assertDoesNotExist()
        onNodeWithTag(TestTags.Directory.DIRECTORY(0,"Photos")).assertExists() // Day 1
        onNodeWithTag(TestTags.Directory.DIRECTORY(1,"NewDirectory")).assertExists() // Day 23
        onNodeWithTag(TestTags.Directory.DIRECTORY(2,"Jaypeg")).assertExists() // Day 10
        onNodeWithTag(TestTags.Directory.DIRECTORY(3,"Peeng")).assertExists() // Day 20

        // Open Sort Dialog and Change Sorting
        onNodeWithTag(TestTags.Directory.TopBar.OPTIONS).assertExists().performClick()
        waitForIdle()
        onNodeWithTag(TestTags.FOTO_DIALOG).assertExists()
        onNodeWithTag(TestTags.Directory.Sort.SORT_TIME_CREATED_DES).assertExists().performClick()
        onNodeWithTag(TestTags.CONFIRM).assertExists().performClick()
        waitForIdle()

        // Check Newly Sorted Directories
        onNodeWithTag(TestTags.Directory.Sort.SORT_TIME_CREATED_ASC).assertDoesNotExist()
        onNodeWithTag(TestTags.Directory.DIRECTORY(0,"NewDirectory")).assertExists()
        onNodeWithTag(TestTags.Directory.DIRECTORY(1,"Photos")).assertExists()
        onNodeWithTag(TestTags.Directory.DIRECTORY(2,"Peeng")).assertExists()
        onNodeWithTag(TestTags.Directory.DIRECTORY(3,"Jaypeg")).assertExists()
    }
}