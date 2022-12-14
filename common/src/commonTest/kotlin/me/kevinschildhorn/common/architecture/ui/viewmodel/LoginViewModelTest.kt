package me.kevinschildhorn.common.architecture.ui.viewmodel

import com.russhwolf.settings.MapSettings
import com.russhwolf.settings.Settings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import me.kevinschildhorn.common.architecture.data.datasources.CredentialsDataSource
import me.kevinschildhorn.common.architecture.data.repositories.CredentialsRepository
import me.kevinschildhorn.common.architecture.domain.SaveCredentialsUseCase
import org.koin.core.context.startKoin
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
        startKoin {
            modules(
                module {
                    single<Settings> { settings }
                    single { CredentialsDataSource(get()) }
                    single { CredentialsRepository(get()) }
                    single { SaveCredentialsUseCase(get()) }
                    single { LoginViewModel(get()) }
                }
            )
        }
    }

    @Test
    fun `login`() = runTest {
        viewModel.updateHostname("google.com")
        viewModel.updateUsername("John")
        viewModel.updatePassword("Secret")
        viewModel.login()

        val state = viewModel.uiState.value
        assertFalse(state.isLoading)
        assertNull(state.errorMessage)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cancel()
    }

    companion object {
        private const val KEY_HOSTNAME = "hostname"
        private const val KEY_USERNAME = "username"
        private const val KEY_PASSWORD = "password"
    }
}
