package com.kevinschildhorn.fotopresenter.ui.login

import app.cash.turbine.test
import com.kevinschildhorn.fotopresenter.testingModule
import com.kevinschildhorn.fotopresenter.ui.UiState
import com.kevinschildhorn.fotopresenter.ui.screens.login.LoginViewModel
import com.russhwolf.settings.MapSettings
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
Testing [com.kevinschildhorn.fotopresenter.ui.screens.login.LoginViewModel]
 **/
@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest : KoinTest {
    private val viewModel: LoginViewModel by inject()
    private fun settings(autoLogin: Boolean = false) =
        MapSettings(
            KEY_HOSTNAME to "192.168.1.1",
            KEY_USERNAME to "admin",
            KEY_PASSWORD to "password",
            KEY_SHAREDFOLDER to "Public",
            KEY_AUTOCONNECT to autoLogin,
        )

    private val emptySettings = MapSettings()

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun uiStateTest() =
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
    fun uiStateAutoPopulate() =
        runTest {
            startKoin {
                modules(testingModule(settings = settings(autoLogin = false)))
            }

            with(viewModel.uiState.value) {
                assertEquals("192.168.1.1", hostname)
                assertEquals("admin", username)
                assertEquals("password", password)
                assertEquals("Public", sharedFolder)
                assertEquals(false, shouldAutoConnect)
                assertEquals(UiState.IDLE, state)
            }
        }

    @Test
    fun uiStateAutoLoginPopulate() = runTest {
        startKoin {
            modules(testingModule(settings = settings(autoLogin = true)))
        }

        with(viewModel.uiState.value) {
            assertEquals("192.168.1.1", hostname)
            assertEquals("admin", username)
            assertEquals("password", password)
            assertEquals("Public", sharedFolder)
            assertEquals(true, shouldAutoConnect)
            assertEquals(UiState.IDLE, state)
        }

        viewModel.uiState.test {
            var state = awaitItem()
            while (state.state == UiState.LOADING || state.state == UiState.IDLE) {
                state = awaitItem()
            }
            assertEquals(UiState.SUCCESS, state.state)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun loginButtonState() =
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
    fun loginFailure() =
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

            viewModel.uiState.test {
                var state = awaitItem()
                while (state.state == UiState.LOADING || state.state == UiState.IDLE) {
                    state = awaitItem()
                }
                assertTrue(state.state is UiState.ERROR)
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun loginError() = runTest {
        startKoin {
            modules(testingModule(settings = emptySettings))
        }
        viewModel.updateHost("throw")
        viewModel.updateUsername("throw")
        viewModel.updatePassword("throw")
        viewModel.updateSharedFolder("throw")
        viewModel.updateShouldAutoConnect(false)
        viewModel.login()

        viewModel.uiState.test {
            var state = awaitItem()
            while (state.state == UiState.LOADING || state.state == UiState.IDLE) {
                state = awaitItem()
            }
            assertTrue(state.state is UiState.ERROR)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun loginSuccess() =
        runTest {
            startKoin {
                modules(testingModule(settings = emptySettings))
            }
            viewModel.updateHost("192.168.1.1")
            viewModel.updateUsername("admin")
            viewModel.updatePassword("password")
            viewModel.updateSharedFolder("Public")
            viewModel.updateShouldAutoConnect(true)
            viewModel.login()

            viewModel.uiState.test {
                var state = awaitItem()
                while (state.state == UiState.LOADING || state.state == UiState.IDLE) {
                    state = awaitItem()
                }
                assertEquals(UiState.SUCCESS, state.state)
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun logout() = runTest {
        startKoin {
            modules(testingModule())
        }
        viewModel.updateHost("192.168.1.1")
        viewModel.updateUsername("admin")
        viewModel.updatePassword("password")
        viewModel.updateSharedFolder("Public")
        viewModel.updateShouldAutoConnect(true)


        viewModel.uiState.test {
            viewModel.login()
            var state = awaitItem()
            while (state.state == UiState.LOADING || state.state == UiState.IDLE) {
                state = awaitItem()
            }
            assertEquals(UiState.SUCCESS, state.state)

            viewModel.setLoggedOut()
            state = awaitItem()
            assertEquals(UiState.IDLE, state.state)

            cancelAndIgnoreRemainingEvents()
        }
    }

    companion object {
        private const val KEY_HOSTNAME = "hostname"
        private const val KEY_SHAREDFOLDER = "sharedFolder"
        private const val KEY_USERNAME = "username"
        private const val KEY_PASSWORD = "password"
        private const val KEY_AUTOCONNECT = "autoConnect"
    }
}
