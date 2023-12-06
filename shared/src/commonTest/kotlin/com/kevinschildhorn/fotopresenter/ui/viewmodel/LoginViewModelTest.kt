package com.kevinschildhorn.fotopresenter.ui.viewmodel

import com.kevinschildhorn.fotopresenter.testingModule
import com.kevinschildhorn.fotopresenter.ui.state.State
import com.kevinschildhorn.fotopresenter.ui.state.UiState
import com.russhwolf.settings.MapSettings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest : KoinTest {
    private val viewModel: LoginViewModel by inject()
    private val testDispatcher = StandardTestDispatcher()
    private val settings =
        MapSettings(
            KEY_HOSTNAME to "defaultHostname",
            KEY_USERNAME to "defaultUsername",
            KEY_PASSWORD to "defaultPassword",
        )
    private val emptySettings = MapSettings()

    @BeforeTest
    fun startTest() {
        Dispatchers.setMain(testDispatcher)
    }

    @AfterTest
    fun tearDown() {
        stopKoin()
        Dispatchers.resetMain()
        testDispatcher.cancel()
    }

    @Test
    fun `UI State`() =
        runTest {
            startKoin {
                modules(testingModule(settings = emptySettings))
            }
            with(viewModel.uiState.value) {
                assertEquals(hostname, "")
                assertEquals(username, "")
                assertEquals(password, "")
                assertEquals(sharedFolder, "")
                assertEquals(shouldAutoConnect, false)
                assertEquals(state, UiState.IDLE)
            }

            viewModel.updateHost("google.com")
            viewModel.updateUsername("John")
            viewModel.updatePassword("Secret")
            viewModel.updateSharedFolder("Public")
            viewModel.updateShouldAutoConnect(true)

            with(viewModel.uiState.value) {
                assertEquals(hostname, "google.com")
                assertEquals(username, "John")
                assertEquals(password, "Secret")
                assertEquals(sharedFolder, "Public")
                assertEquals(shouldAutoConnect, true)
                assertEquals(state, UiState.IDLE)
            }
        }

    @Test
    fun `UI State Auto Populate`() =
        runTest {
            startKoin {
                modules(testingModule(settings = settings))
                with(viewModel.uiState.value) {
                    assertEquals(hostname, "defaultHostname")
                    assertEquals(username, "defaultUsername")
                    assertEquals(password, "defaultPassword")
                    assertEquals(sharedFolder, "")
                    assertEquals(shouldAutoConnect, false)
                    assertEquals(state, UiState.IDLE)
                }
            }
        }

    @Test
    fun `Login Button State`() =
        runTest {
            startKoin {
                modules(testingModule(settings = emptySettings))
            }
            val host = "google.com"
            val username = "John"
            val password = "Secret"
            val sharedFolder = "Public"

            val uiState = viewModel.uiState
            assertFalse(uiState.value.isLoginButtonEnabled)

            viewModel.updateHost(host)
            viewModel.updateUsername(username)
            viewModel.updatePassword(password)
            viewModel.updateSharedFolder(sharedFolder)
            viewModel.updateShouldAutoConnect(true)
            assertTrue(uiState.value.isLoginButtonEnabled)

            // Host
            viewModel.updateHost("")
            assertFalse(uiState.value.isLoginButtonEnabled)

            // Username
            viewModel.updateHost(host)
            assertTrue(uiState.value.isLoginButtonEnabled)
            viewModel.updateUsername("")
            assertFalse(uiState.value.isLoginButtonEnabled)

            // Password
            viewModel.updateUsername(username)
            assertTrue(uiState.value.isLoginButtonEnabled)
            viewModel.updatePassword("")
            assertFalse(uiState.value.isLoginButtonEnabled)

            // Shared Folder
            viewModel.updatePassword(password)
            assertTrue(uiState.value.isLoginButtonEnabled)
            viewModel.updateSharedFolder("")
            assertFalse(uiState.value.isLoginButtonEnabled)

            // Password
            viewModel.updateSharedFolder(sharedFolder)
            assertTrue(uiState.value.isLoginButtonEnabled)

            viewModel.updateShouldAutoConnect(false)
            assertTrue(uiState.value.isLoginButtonEnabled)
        }

    @Test
    fun `Login Failure`() =
        runTest {
            startKoin {
                modules(testingModule(settings = emptySettings))
            }
            viewModel.updateHost("google.com")
            viewModel.updateUsername("John")
            viewModel.updatePassword("Secret")
            viewModel.updateSharedFolder("Public")
            viewModel.updateShouldAutoConnect(true)
            viewModel.login()

            with(viewModel.uiState.value) {
                assertEquals(UiState.LOADING, state)
            }

            delay(2000)
            with(viewModel.uiState.value) {
                assertTrue(state is UiState.ERROR)
            }
        }

    @Test
    fun `Login Success`() =
        runTest {
            startKoin {
                modules(testingModule(settings = emptySettings))
            }
            viewModel.updateHost("192.168.1.1")
            viewModel.updateUsername("admin")
            viewModel.updatePassword("password")
            viewModel.updateSharedFolder("Public")
            viewModel.updateShouldAutoConnect(false)
            viewModel.login()

            with(viewModel.uiState.value) {
                assertEquals(UiState.LOADING, state)
            }

            delay(2000)
            with(viewModel.uiState.value) {
                assertEquals(UiState.SUCCESS, state)
            }
        }

    companion object {
        private const val KEY_HOSTNAME = "hostname"
        private const val KEY_USERNAME = "username"
        private const val KEY_PASSWORD = "password"
    }
}
