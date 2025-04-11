package com.kevinschildhorn.fotopresenter.ui.directory

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.longClick
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.runComposeUiTest
import com.kevinschildhorn.fotopresenter.data.ImageDirectory
import com.kevinschildhorn.fotopresenter.data.network.MockNetworkHandler
import com.kevinschildhorn.fotopresenter.onNodeWithTag
import com.kevinschildhorn.fotopresenter.testingModule
import com.kevinschildhorn.fotopresenter.ui.TestTags
import com.kevinschildhorn.fotopresenter.ui.TestTags.OVERLAY_SHADOW
import com.kevinschildhorn.fotopresenter.ui.screens.common.ActionSheetAction
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
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
Testing [DirectoryScreen], specifically navigating from the main screen to other sub-screens
Use Cases: [https://github.com/KevinSchildhorn/FoToPresenter/blob/main/docs/UX/USE_CASES_BROWSING.md]
Gets data from [MockNetworkHandler]
 **/
@OptIn(ExperimentalTestApi::class, ExperimentalCoroutinesApi::class)
class DirectoryScreenTestActions : KoinTest {
    private val viewModel: DirectoryViewModel by inject()

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
    fun viewingPhoto() =
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

            onNodeWithTag(TestTags.Directory.DIRECTORY(2, "Jaypeg")).assertExists().performClick()
            waitForIdle()
            onNodeWithTag(TestTags.Directory.IMAGE_PREVIEW("Jaypeg")).assertExists()
            onNodeWithTag(OVERLAY_SHADOW).assertExists().performClick()
            waitForIdle()
            onNodeWithTag(TestTags.Directory.IMAGE_PREVIEW("Jaypeg")).assertDoesNotExist()
        }

    @Test
    fun startingSlideshow() =
        runComposeUiTest {
            Dispatchers.setMain(Dispatchers.IO)

            var slideshowStarted: Boolean = false
            var directories: List<ImageDirectory> = emptyList()
            setContent {
                DirectoryScreen(
                    viewModel = viewModel,
                    onLogout = {},
                    onStartSlideshow = {
                        directories = it.directories
                        slideshowStarted = true
                    },
                    onShowPlaylists = {},
                )
            }

            onNodeWithTag(TestTags.Directory.DIRECTORY(1, "Photos"))
                .assertExists()
                .performTouchInput { longClick() }
            waitForIdle()
            onNodeWithTag(TestTags.ACTION_SHEET).assertExists()
            assertFalse(slideshowStarted)
            assert(directories.isEmpty())
            onNodeWithTag(TestTags.ACTION_SHEET_ITEM(ActionSheetAction.START_SLIDESHOW)).assertExists().performClick()
            waitForIdle()
            assertEquals(expected = 2, actual = directories.count())
            assertTrue(slideshowStarted)
        }

    // TODO: Implement Test
    @Test
    fun navigatingToLibraries() =
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
        }

    // TODO: Implement Test
    @Test
    fun selectingLibrary() =
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
        }

    @Test
    fun logout() =
        runComposeUiTest {
            Dispatchers.setMain(Dispatchers.IO)
            var logoutCalled = false
            setContent {
                DirectoryScreen(
                    viewModel = viewModel,
                    onLogout = {
                        logoutCalled = true
                    },
                    onStartSlideshow = {},
                    onShowPlaylists = {},
                )
            }

            onNodeWithTag(TestTags.Directory.TopBar.TOP_BAR).assertExists()
            onNodeWithTag(TestTags.Directory.TopBar.MENU).assertExists().performClick()
            onNodeWithTag(TestTags.Directory.NavigationRail.ITEM_LOGOUT).assertExists().performClick()
            onNodeWithTag(TestTags.FOTO_DIALOG).assertExists()
            onNodeWithTag(TestTags.DISMISS).assertExists().performClick()
            assertFalse(logoutCalled)

            onNodeWithTag(TestTags.Directory.TopBar.TOP_BAR).assertExists()
            onNodeWithTag(TestTags.Directory.TopBar.MENU).assertExists().performClick()
            onNodeWithTag(TestTags.Directory.NavigationRail.ITEM_LOGOUT).assertExists().performClick()
            onNodeWithTag(TestTags.FOTO_DIALOG).assertExists()
            onNodeWithTag(TestTags.CONFIRM).assertExists().performClick()
            assertTrue(logoutCalled)
        }
}
