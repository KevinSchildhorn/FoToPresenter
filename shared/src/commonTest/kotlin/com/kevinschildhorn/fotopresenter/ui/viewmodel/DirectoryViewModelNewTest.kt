package com.kevinschildhorn.fotopresenter.ui.viewmodel

import app.cash.turbine.test
import com.kevinschildhorn.fotopresenter.data.network.MockNetworkHandler
import com.kevinschildhorn.fotopresenter.testingModule
import com.kevinschildhorn.fotopresenter.ui.UiState
import com.kevinschildhorn.fotopresenter.ui.screens.directory.DirectoryViewModel
import com.kevinschildhorn.fotopresenter.ui.screens.directory.DirectoryViewModelNew
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Test
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

/**
Testing [DirectoryViewModelNew]
 **/
@OptIn(ExperimentalCoroutinesApi::class)
class DirectoryViewModelNewTest : KoinTest {
    private val testDispatcher = StandardTestDispatcher()

    @BeforeTest
    fun startTest() =
        runBlocking {
            startKoin {
                modules(testingModule())
            }
            Dispatchers.setMain(testDispatcher)
            MockNetworkHandler.connectSuccessfully()
        }

    @AfterTest
    fun tearDown() =
        runBlocking {
            MockNetworkHandler.disconnect()
            stopKoin()
            Dispatchers.resetMain()
        }

    @Test
    fun signingInSuccessOld() = runTest {
        val viewModel: DirectoryViewModelNew by inject()

        viewModel.uiState.test {
            var item = awaitItem()
            viewModel.refreshScreen()
            item = awaitItem()
            item = awaitItem()
            assertEquals(item.state, UiState.SUCCESS)
            assertNotEquals(item.directoryGridUIState.allStates.count(), actual = 0)
        }
    }
}