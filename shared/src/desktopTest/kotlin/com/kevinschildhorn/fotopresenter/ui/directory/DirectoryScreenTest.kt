package com.kevinschildhorn.fotopresenter.ui.directory

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runComposeUiTest
import app.cash.turbine.test
import com.kevinschildhorn.fotopresenter.data.network.MockNetworkHandler
import com.kevinschildhorn.fotopresenter.onNodeWithTag
import com.kevinschildhorn.fotopresenter.testingModule
import com.kevinschildhorn.fotopresenter.ui.TestTags
import com.kevinschildhorn.fotopresenter.ui.UiState
import com.kevinschildhorn.fotopresenter.ui.screens.directory.DirectoryScreen
import com.kevinschildhorn.fotopresenter.ui.screens.directory.DirectoryViewModelNew
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

/**
Testing [com.kevinschildhorn.fotopresenter.ui.screens.directory.DirectoryScreen]
 See: [This](https://github.com/KevinSchildhorn/FoToPresenter/blob/main/docs/UX/USE_CASES_BROWSING.md)
 **/
class DirectoryScreenTest : KoinTest {
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

    @OptIn(ExperimentalTestApi::class)
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
        onNodeWithTag(TestTags.Directory.TOP_BAR).assertExists()
        onNodeWithTag(TestTags.Directory.NAVIGATION_RAIL).assertExists()
        onNodeWithTag("NavItemHome").assertExists()

        /** See [MockNetworkHandler.networkContents] */
        onNodeWithTag("DirectoryNewDirectory").assertExists()
        onNodeWithTag("DirectoryPhotos").assertExists()
        onNodeWithTag("DirectoryPeeng").assertExists()
        onNodeWithTag("DirectoryJaypeg").assertExists()
        onNodeWithTag("DirectorytextFile").assertDoesNotExist()

        // Going Forward
        val directory = "Photos"
        onNodeWithTag("Directory$directory").performClick()
        waitForIdle()
        onNodeWithTag("NavItem$directory").assertExists()
        onNodeWithTag("DirectoryPeeng2").assertExists()
        onNodeWithTag("DirectoryJaypeg2").assertExists()
        onNodeWithTag("DirectorytextFile2").assertDoesNotExist()

        // Going Forward Again
        val subDirectory = "SubPhotos"
        onNodeWithTag("Directory$subDirectory").performClick()
        waitForIdle()
        onNodeWithTag("NavItem$directory").assertExists()
        onNodeWithTag("NavItem$subDirectory").assertExists()
        onNodeWithTag("DirectoryPeeng3").assertExists()

        // Going Backward
        onNodeWithTag("NavItem$directory").performClick()
        waitForIdle()
        onNodeWithTag("NavItem$directory").assertExists()
        onNodeWithTag("DirectoryPeeng2").assertExists()
        onNodeWithTag("DirectoryJaypeg2").assertExists()
        onNodeWithTag("DirectorytextFile2").assertDoesNotExist()
    }

    @OptIn(ExperimentalTestApi::class)
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

    @OptIn(ExperimentalTestApi::class)
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

        // TODO: This is not implemented
        onNodeWithTag(TestTags.Directory.TOP_BAR_OPTIONS).assertExists().performClick()
        waitForIdle()
        onNodeWithTag(TestTags.FOTO_DIALOG).assertExists()
        onNodeWithTag(TestTags.Directory.SORT_A_TO_Z).assertExists().performClick()
        onNodeWithTag(TestTags.CONFIRM).assertExists().performClick()

        onNodeWithTag(TestTags.Directory.SORT_A_TO_Z).assertDoesNotExist()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun viewingPhoto() = runComposeUiTest {
        Dispatchers.setMain(Dispatchers.IO)

        setContent {
            DirectoryScreen(
                viewModel = viewModel,
                onLogout = {},
                onStartSlideshow = {},
                onShowPlaylists = {},
            )
        }

        onNodeWithTag("DirectoryPeeng").performClick()

        onNodeWithTag(TestTags.Directory.TOP_BAR_OPTIONS).assertExists().performClick()
        waitForIdle()
    }


    @OptIn(ExperimentalTestApi::class)
    @Test
    fun signingInSuccessOld() = runTest {
        // TODO: Move to DirectoryViewModelTest
        /*
        setContent {
            DirectoryScreen(
                viewModel = viewModel,
                onLogout = {},
                onStartSlideshow = {},
                onShowPlaylists = {},
            )
        }*/

        viewModel.uiState.test {
            var item = awaitItem()
            viewModel.refreshScreen()
            item = awaitItem()
            item = awaitItem()
            assertEquals(item.state, UiState.SUCCESS)
            assertNotEquals(item.directoryGridUIState.allStates.count(), actual = 0)
        }

        /*
                // Initial State
                onNodeWithTag(TestTags.Directory.NAVIGATION_BAR).assertExists()
                onNodeWithTag(TestTags.Directory.TOP_BAR).assertExists()
                onNodeWithTag(TestTags.Directory.NAVIGATION_RAIL).assertExists()*/
    }
}