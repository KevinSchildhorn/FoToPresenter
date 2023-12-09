package com.kevinschildhorn.fotopresenter.ui.viewmodel

import com.kevinschildhorn.fotopresenter.MainCoroutineRule
import com.kevinschildhorn.fotopresenter.ui.screens.directory.DirectoryViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.AfterTest

/**
Testing [DirectoryViewModel]
 **/
@OptIn(ExperimentalCoroutinesApi::class)
class DirectoryViewModelTest : KoinTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val viewModel: DirectoryViewModel by inject()

    @AfterTest
    fun tearDown() {
        stopKoin()
    }
/*
    @Test
    fun `UI State`() =
        runTest {
            startKoin {
                modules(testingModule())
            }

            MockNetworkHandler.connectSuccessfully()
            with(viewModel.uiState.value) {
                assertEquals("", currentPath)
                assertEquals(0, directoryContents.allDirectories.count())
            }

            viewModel.refreshScreen()
            advanceUntilIdle()

            with(viewModel.uiState.value) {
                // assertEquals("", currentPath)
                // assertEquals(2, directoryContents.allDirectories.count())
            }
            MockNetworkHandler.disconnect()
        }*/
}
