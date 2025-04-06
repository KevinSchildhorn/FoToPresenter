package com.kevinschildhorn.fotopresenter.ui.directory

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runComposeUiTest
import app.cash.turbine.test
import app.cash.turbine.turbineScope
import co.touchlab.kermit.Logger
import com.kevinschildhorn.fotopresenter.data.DirectoryNavigator
import com.kevinschildhorn.fotopresenter.data.ImagePreviewNavigator
import com.kevinschildhorn.fotopresenter.data.Path
import com.kevinschildhorn.fotopresenter.data.datasources.ImageMetadataDataSource
import com.kevinschildhorn.fotopresenter.data.network.DefaultNetworkDirectoryDetails
import com.kevinschildhorn.fotopresenter.data.network.MockNetworkHandler
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandler
import com.kevinschildhorn.fotopresenter.data.repositories.CredentialsRepository
import com.kevinschildhorn.fotopresenter.onNodeWithTag
import com.kevinschildhorn.fotopresenter.testingModule
import com.kevinschildhorn.fotopresenter.ui.TestTags
import com.kevinschildhorn.fotopresenter.ui.UiState
import com.kevinschildhorn.fotopresenter.ui.screens.directory.DirectoryOverlayUiState
import com.kevinschildhorn.fotopresenter.ui.screens.directory.DirectoryScreen
import com.kevinschildhorn.fotopresenter.ui.screens.directory.DirectoryScreenUIState
import com.kevinschildhorn.fotopresenter.ui.screens.directory.DirectoryViewModelNew
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

/**
Testing [com.kevinschildhorn.fotopresenter.ui.screens.directory.DirectoryScreen]
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
    fun navigatingForwardAndBackward() = runComposeUiTest {
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

        val directory = "Photos"
        onNodeWithTag("Directory$directory").performClick()
        waitForIdle()
        onNodeWithTag("NavItem$directory").assertExists()
        onNodeWithTag("Directory$directory/Peeng2").assertExists()
        onNodeWithTag("Directory$directory/Jaypeg2").assertExists()
        onNodeWithTag("Directory$directory/textFile2").assertDoesNotExist()

        val subDirectory = "SubPhotos"
        onNodeWithTag("Directory$directory/$subDirectory").performClick()
        waitForIdle()
        onNodeWithTag("NavItem$directory").assertExists()
        onNodeWithTag("NavItem$subDirectory").assertExists()
        onNodeWithTag("Directory$directory/$subDirectory/Peeng3").assertExists()
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