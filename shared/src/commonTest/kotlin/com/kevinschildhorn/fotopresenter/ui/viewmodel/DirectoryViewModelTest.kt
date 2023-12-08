package com.kevinschildhorn.fotopresenter.ui.viewmodel

import com.kevinschildhorn.fotopresenter.MainCoroutineRule
import com.kevinschildhorn.fotopresenter.data.network.MockNetworkHandler
import com.kevinschildhorn.fotopresenter.testingModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.AfterTest
import kotlin.test.assertEquals

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
