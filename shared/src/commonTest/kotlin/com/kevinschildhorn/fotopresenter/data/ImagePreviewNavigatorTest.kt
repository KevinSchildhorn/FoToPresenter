package com.kevinschildhorn.fotopresenter.data

import app.cash.turbine.test
import com.kevinschildhorn.fotopresenter.data.network.DefaultNetworkDirectoryDetails
import com.kevinschildhorn.fotopresenter.data.network.MockNetworkHandler
import com.kevinschildhorn.fotopresenter.testingModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

/**
 * Testing [ImagePreviewNavigator]
 */
@OptIn(ExperimentalCoroutinesApi::class)
class ImagePreviewNavigatorTest : KoinTest {
    private val testDispatcher = StandardTestDispatcher()
    private val imagePreviewNavigator: ImagePreviewNavigator by inject()

    // Sample image directories for testing
    private val imageDirectories =
        listOf(
            ImageDirectory(DefaultNetworkDirectoryDetails(Path("image1.jpg"), 1), null),
            ImageDirectory(DefaultNetworkDirectoryDetails(Path("image2.jpg"), 2), null),
            ImageDirectory(DefaultNetworkDirectoryDetails(Path("image3.jpg"), 3), null),
            ImageDirectory(DefaultNetworkDirectoryDetails(Path("image4.jpg"), 4), null),
        )

    @BeforeTest
    fun startTest() {
        startKoin {
            modules(testingModule())
        }
    }

    @AfterTest
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `set_folder_contents_success`() =
        runTest(testDispatcher) {
            MockNetworkHandler.connectSuccessfully()
            imagePreviewNavigator.imagePreviewState.test {
                // Initial state should be null
                assertNull(awaitItem())

                // Set folder contents
                imagePreviewNavigator.setFolderContents(imageDirectories)
                // State should still be null as no image is selected
                expectNoEvents()
            }
            MockNetworkHandler.disconnect()
        }

    @Test
    fun `set_image_index_success`() =
        runTest(testDispatcher) {
            MockNetworkHandler.connectSuccessfully()
            imagePreviewNavigator.imagePreviewState.test {
                // Initial state should be null
                assertNull(awaitItem())

                // Set folder contents
                imagePreviewNavigator.setFolderContents(imageDirectories)
                expectNoEvents() // Skip the null state after setFolderContents

                // Set image index to 1
                imagePreviewNavigator.setImageIndex(1)

                // Should emit the second image
                val imageDetails = awaitItem()
                assertEquals("image2", imageDetails?.name)
            }
            MockNetworkHandler.disconnect()
        }

    @Test
    fun `set_image_index_null_success`() =
        runTest(testDispatcher) {
            MockNetworkHandler.connectSuccessfully()
            imagePreviewNavigator.imagePreviewState.test {
                // Initial state should be null
                assertNull(awaitItem())

                // Set folder contents
                imagePreviewNavigator.setFolderContents(imageDirectories)
                expectNoEvents() // Skip the null state after setFolderContents

                // Set image index to 1
                imagePreviewNavigator.setImageIndex(1)
                awaitItem() // Skip the image2.jpg state

                // Set image index to null
                imagePreviewNavigator.setImageIndex(null)

                // Should emit null
                assertNull(awaitItem())
            }
            MockNetworkHandler.disconnect()
        }

    @Test
    fun `show_previous_image_success`() =
        runTest(testDispatcher) {
            MockNetworkHandler.connectSuccessfully()
            imagePreviewNavigator.imagePreviewState.test {
                // Initial state should be null
                assertNull(awaitItem())

                // Set folder contents
                imagePreviewNavigator.setFolderContents(imageDirectories)
                expectNoEvents() // Skip the null state after setFolderContents

                // Set image index to 1
                imagePreviewNavigator.setImageIndex(1)
                awaitItem() // Skip the image2.jpg state

                // Show previous image
                imagePreviewNavigator.showPreviousImage()

                // Should emit the first image
                val imageDetails = awaitItem()
                assertEquals("image1", imageDetails?.name)
            }
            MockNetworkHandler.disconnect()
        }

    @Test
    fun `show_previous_image_wrap_around_success`() =
        runTest(testDispatcher) {
            MockNetworkHandler.connectSuccessfully()
            imagePreviewNavigator.imagePreviewState.test {
                // Initial state should be null
                assertNull(awaitItem())

                // Set folder contents
                imagePreviewNavigator.setFolderContents(imageDirectories)
                expectNoEvents() // Skip the null state after setFolderContents

                // Set image index to 0
                imagePreviewNavigator.setImageIndex(0)
                awaitItem() // Skip the image1.jpg state

                // Show previous image (should wrap to the last image)
                imagePreviewNavigator.showPreviousImage()

                // Should emit the last image
                val imageDetails = awaitItem()
                assertEquals("image4", imageDetails?.name)
            }
            MockNetworkHandler.disconnect()
        }

    @Test
    fun `show_next_image_success`() =
        runTest(testDispatcher) {
            MockNetworkHandler.connectSuccessfully()
            imagePreviewNavigator.imagePreviewState.test {
                // Initial state should be null
                assertNull(awaitItem())

                // Set folder contents
                imagePreviewNavigator.setFolderContents(imageDirectories)
                expectNoEvents() // Skip the null state after setFolderContents

                // Set image index to 1
                imagePreviewNavigator.setImageIndex(1)
                awaitItem() // Skip the image2.jpg state

                // Show next image
                imagePreviewNavigator.showNextImage()

                // Should emit the third image
                val imageDetails = awaitItem()
                assertEquals("image3", imageDetails?.name)
            }
            MockNetworkHandler.disconnect()
        }

    @Test
    fun `show_next_image_wrap_around_success`() =
        runTest(testDispatcher) {
            MockNetworkHandler.connectSuccessfully()
            imagePreviewNavigator.imagePreviewState.test {
                // Initial state should be null
                assertNull(awaitItem())

                // Set folder contents
                imagePreviewNavigator.setFolderContents(imageDirectories)
                expectNoEvents() // Skip the null state after setFolderContents

                // Set image index to 3 (last image)
                imagePreviewNavigator.setImageIndex(3)
                awaitItem() // Skip the image4.jpg state

                // Show next image (should wrap to the first image)
                imagePreviewNavigator.showNextImage()

                // Should emit the first image
                val imageDetails = awaitItem()
                assertEquals("image1", imageDetails?.name)
            }
            MockNetworkHandler.disconnect()
        }

    @Test
    fun `show_next_previous_image_sequence_success`() =
        runTest(testDispatcher) {
            MockNetworkHandler.connectSuccessfully()
            imagePreviewNavigator.imagePreviewState.test {
                // Initial state should be null
                assertNull(awaitItem())

                // Set folder contents
                imagePreviewNavigator.setFolderContents(imageDirectories)
                expectNoEvents() // Skip the null state after setFolderContents

                // Set image index to 1
                imagePreviewNavigator.setImageIndex(1)
                awaitItem() // Skip the image2.jpg state

                // Show next image
                imagePreviewNavigator.showNextImage()
                val nextImage = awaitItem()
                assertEquals("image3", nextImage?.name)

                // Show previous image
                imagePreviewNavigator.showPreviousImage()
                val prevImage = awaitItem()
                assertEquals("image2", prevImage?.name)
            }
            MockNetworkHandler.disconnect()
        }

    @Test
    fun `empty_directory_list_success`() =
        runTest(testDispatcher) {
            MockNetworkHandler.connectSuccessfully()
            imagePreviewNavigator.imagePreviewState.test {
                // Initial state should be null
                assertNull(awaitItem())

                // Set empty folder contents
                imagePreviewNavigator.setFolderContents(emptyList())
                expectNoEvents() // Skip the null state after setFolderContents

                // Set image index to 0 (should have no effect)
                imagePreviewNavigator.setImageIndex(0)

                // State should still be null
                expectNoEvents()

                // Show next/previous image (should have no effect)
                imagePreviewNavigator.showNextImage()
                imagePreviewNavigator.showPreviousImage()

                // State should still be null
                expectNoEvents()
            }
            MockNetworkHandler.disconnect()
        }
}
