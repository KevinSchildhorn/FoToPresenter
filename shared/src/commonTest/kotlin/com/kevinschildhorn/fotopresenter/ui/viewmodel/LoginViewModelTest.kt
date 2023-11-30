package com.kevinschildhorn.fotopresenter.ui.viewmodel

import co.touchlab.kermit.Logger
import co.touchlab.kermit.LoggerConfig
import com.kevinschildhorn.fotopresenter.data.datasources.CredentialsDataSource
import com.kevinschildhorn.fotopresenter.data.network.MockNetworkHandler
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandler
import com.kevinschildhorn.fotopresenter.data.repositories.CredentialsRepository
import com.kevinschildhorn.fotopresenter.domain.ConnectToServerUseCase
import com.kevinschildhorn.fotopresenter.domain.SaveCredentialsUseCase
import com.kevinschildhorn.fotopresenter.testingModule
import com.kevinschildhorn.fotopresenter.ui.state.State
import com.russhwolf.settings.MapSettings
import com.russhwolf.settings.Settings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest : KoinTest {

    private val viewModel: LoginViewModel by inject()
    private val testDispatcher = StandardTestDispatcher()
    private val settings = MapSettings(
        KEY_HOSTNAME to "defaultHostname",
        KEY_USERNAME to "defaultUsername",
        KEY_PASSWORD to "defaultPassword",
    )

    @BeforeTest
    fun startTest() {
        Dispatchers.setMain(testDispatcher)
        val baseLogger = Logger(LoggerConfig.default)

        startKoin {
            modules(testingModule(settings = settings))
        }
    }

    @AfterTest
    fun tearDown() {
        stopKoin()
        Dispatchers.resetMain()
        testDispatcher.cancel()
    }

    @Test
    fun `login`() = runTest {
        viewModel.updateHost("google.com")
        viewModel.updateUsername("John")
        viewModel.updatePassword("Secret")
        viewModel.login()

        val state = viewModel.uiState.value
        assertEquals(state.state, State.LOADING)
    }

    companion object {
        private const val KEY_HOSTNAME = "hostname"
        private const val KEY_USERNAME = "username"
        private const val KEY_PASSWORD = "password"
    }
}