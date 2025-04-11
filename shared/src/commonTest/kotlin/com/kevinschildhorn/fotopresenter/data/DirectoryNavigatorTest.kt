package com.kevinschildhorn.fotopresenter.data

import app.cash.turbine.test
import com.kevinschildhorn.fotopresenter.data.network.MockNetworkHandler
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandlerError
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandlerException
import com.kevinschildhorn.fotopresenter.testingModule
import com.kevinschildhorn.fotopresenter.ui.SortingType
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
import kotlin.test.assertNotNull
import kotlin.test.assertTrue
import kotlin.test.fail

/**
 * Testing [DirectoryNavigator]
 */
@OptIn(ExperimentalCoroutinesApi::class)
class DirectoryNavigatorTest : KoinTest {
    private val testDispatcher = StandardTestDispatcher()
    private val directoryNavigator: DirectoryNavigator by inject()

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
    fun `refresh_directory_contents_success`() =
        runTest(testDispatcher) {
            MockNetworkHandler.connectSuccessfully()
            directoryNavigator.currentDirectoryContents.test {
                directoryNavigator.refreshDirectoryContents()
                awaitItem()
                val contents = awaitItem()

                assertEquals(2, contents.folders.size)
                assertEquals(2, contents.images.size)
                assertEquals(4, contents.allDirectories.size)
            }
            MockNetworkHandler.disconnect()
        }

    @Test
    fun `navigate_into_directory_success`() =
        runTest(testDispatcher) {
            MockNetworkHandler.connectSuccessfully()
            directoryNavigator.currentDirectoryContents.test {
                directoryNavigator.refreshDirectoryContents()
                awaitItem()
                val initialContents = awaitItem()
                val photosDirectory = initialContents.folders.find { it.name == "Photos" }
                assertNotNull(photosDirectory, "Photos directory should exist")

                directoryNavigator.navigateIntoDirectory(photosDirectory.id)
                val contents = awaitItem()

                assertEquals(1, contents.folders.size) // SubPhotos folder
                assertEquals(2, contents.images.size) // Peeng2.png, Jaypeg2.jpg
            }
            MockNetworkHandler.disconnect()
        }

    @Test
    fun `navigate_back_to_directory_success`() =
        runTest(testDispatcher) {
            MockNetworkHandler.connectSuccessfully()
            directoryNavigator.currentDirectoryContents.test {
                // First navigate into Photos directory
                directoryNavigator.refreshDirectoryContents()
                awaitItem()
                val initialContents = awaitItem()
                val photosDirectory = initialContents.folders.find { it.name == "Photos" }
                assertNotNull(photosDirectory, "Photos directory should exist")
                directoryNavigator.navigateIntoDirectory(photosDirectory.id)
                awaitItem() // Skip the Photos directory contents

                // Then navigate back to root
                directoryNavigator.navigateBackToDirectory(-1)
                val contents = awaitItem()

                assertEquals(2, contents.folders.size)
                assertEquals(2, contents.images.size)
            }
            MockNetworkHandler.disconnect()
        }

    @Test
    fun `set_sort_type_success`() =
        runTest(testDispatcher) {
            MockNetworkHandler.connectSuccessfully()
            directoryNavigator.currentDirectoryContents.test {
                directoryNavigator.refreshDirectoryContents()
                awaitItem() // Initial state

                // Test ascending sort
                directoryNavigator.setSortType(SortingType.NAME_ASC)
                val ascendingContents = awaitItem()
                assertTrue(ascendingContents.folders[0].name < ascendingContents.folders[1].name)

                // Test descending sort
                directoryNavigator.setSortType(SortingType.NAME_DESC)
                val descendingContents = awaitItem()
                assertTrue(descendingContents.folders[0].name > descendingContents.folders[1].name)
            }
            MockNetworkHandler.disconnect()
        }

    @Test
    fun `set_search_success`() =
        runTest(testDispatcher) {
            MockNetworkHandler.connectSuccessfully()
            directoryNavigator.currentDirectoryContents.test {
                directoryNavigator.refreshDirectoryContents()
                awaitItem()
                awaitItem() // Initial state

                // Search for "pe"
                directoryNavigator.setSearch("pe")
                val filteredContents = awaitItem()
                assertEquals(2, filteredContents.allDirectories.size) // Should only show Peeng.png and Jaypeg.jpg

                // Clear search
                directoryNavigator.setSearch("")
                val unfilteredContents = awaitItem()
                assertEquals(4, unfilteredContents.allDirectories.size) // Should show all items again
            }
            MockNetworkHandler.disconnect()
        }

    @Test
    fun `get_directory_from_id_success`() =
        runTest(testDispatcher) {
            MockNetworkHandler.connectSuccessfully()
            directoryNavigator.currentDirectoryContents.test {
                directoryNavigator.refreshDirectoryContents()
                awaitItem()
                val contents = awaitItem()
                val photosDirectory = contents.folders.find { it.name == "Photos" }
                assertNotNull(photosDirectory, "Photos directory should exist")

                val foundDirectory = directoryNavigator.getDirectoryFromId(photosDirectory.id)
                assertNotNull(foundDirectory)
                assertEquals("Photos", foundDirectory.name)
            }
            MockNetworkHandler.disconnect()
        }

    @Test
    fun `navigate_into_directory_failure`() =
        runTest(testDispatcher) {
            MockNetworkHandler.connectSuccessfully()
            directoryNavigator.currentDirectoryContents.test {
                directoryNavigator.refreshDirectoryContents()
                awaitItem() // Initial state
                awaitItem()
                try {
                    directoryNavigator.navigateIntoDirectory(999L) // Non-existent ID
                    expectNoEvents()
                } catch (e: Exception) {
                    // Expected behavior
                }
            }
            MockNetworkHandler.disconnect()
        }

    @Test
    fun `navigate_back_to_directory_failure`() =
        runTest(testDispatcher) {
            MockNetworkHandler.connectSuccessfully()
            directoryNavigator.currentDirectoryContents.test {
                directoryNavigator.refreshDirectoryContents()
                awaitItem() // Initial state
                awaitItem()

                try {
                    directoryNavigator.navigateBackToDirectory(5) // Invalid index
                    fail("Should have thrown an exception")
                } catch (e: Exception) {
                    // Expected behavior
                }
            }
            MockNetworkHandler.disconnect()
        }

    @Test
    fun `disconnected_network_failure`() =
        runTest(testDispatcher) {
            MockNetworkHandler.disconnect()

            try {
                directoryNavigator.refreshDirectoryContents()
                fail("Should have thrown NetworkHandlerException")
            } catch (e: NetworkHandlerException) {
                assertEquals(NetworkHandlerError.NOT_CONNECTED.message, e.message)
            }
        }
}
