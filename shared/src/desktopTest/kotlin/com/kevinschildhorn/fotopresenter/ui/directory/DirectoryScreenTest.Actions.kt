package com.kevinschildhorn.fotopresenter.ui.directory

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runComposeUiTest
import com.kevinschildhorn.fotopresenter.data.network.MockNetworkHandler
import com.kevinschildhorn.fotopresenter.onNodeWithTag
import com.kevinschildhorn.fotopresenter.testingModule
import com.kevinschildhorn.fotopresenter.ui.TestTags
import com.kevinschildhorn.fotopresenter.ui.TestTags.DIRECTORY
import com.kevinschildhorn.fotopresenter.ui.TestTags.IMAGE_PREVIEW
import com.kevinschildhorn.fotopresenter.ui.TestTags.OVERLAY_SHADOW
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
Testing [DirectoryScreen], specifically navigating from the main screen to other sub-screens
Use Cases: [https://github.com/KevinSchildhorn/FoToPresenter/blob/main/docs/UX/USE_CASES_BROWSING.md]
Gets data from [MockNetworkHandler]
 **/
@OptIn(ExperimentalTestApi::class, ExperimentalCoroutinesApi::class)
class DirectoryScreenTestActions : KoinTest {
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

    // COMPLETE
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

        onNodeWithTag(DIRECTORY(2,"Jaypeg")).assertExists().performClick()
        waitForIdle()
        onNodeWithTag(IMAGE_PREVIEW("Jaypeg")).assertExists()
        onNodeWithTag(OVERLAY_SHADOW).assertExists().performClick()
        waitForIdle()
        onNodeWithTag(IMAGE_PREVIEW("Jaypeg")).assertDoesNotExist()
    }

    @Test
    fun startingSlideshow() = runComposeUiTest {
        Dispatchers.setMain(Dispatchers.IO)

        setContent {
            DirectoryScreen(
                viewModel = viewModel,
                onLogout = {},
                onStartSlideshow = {},
                onShowPlaylists = {},
            )
        }

        // Open Sort Dialog and Change Sorting
        onNodeWithTag(TestTags.Directory.TOP_BAR_OPTIONS).assertExists().performClick()
        waitForIdle()
        onNodeWithTag(TestTags.FOTO_DIALOG).assertExists()
        onNodeWithTag(TestTags.Directory.SORT_Z_TO_A).assertExists().performClick()
        onNodeWithTag(TestTags.CONFIRM).assertExists().performClick()
        waitForIdle()
    }

    @Test
    fun logout() = runComposeUiTest {
        Dispatchers.setMain(Dispatchers.IO)

        setContent {
            DirectoryScreen(
                viewModel = viewModel,
                onLogout = {},
                onStartSlideshow = {},
                onShowPlaylists = {},
            )
        }
    }
}