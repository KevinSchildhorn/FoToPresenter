package me.kevinschildhorn.common.layers.ui.viewmodel

import com.russhwolf.settings.MapSettings
import com.russhwolf.settings.Settings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import me.kevinschildhorn.common.layers.data.datasources.CredentialsDataSource
import me.kevinschildhorn.common.layers.data.repositories.CredentialsRepository
import me.kevinschildhorn.common.layers.domain.SaveCredentialsUseCase
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.*

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest : KoinTest {

    private val viewModel: LoginViewModel by inject()
    private val testDispatcher = StandardTestDispatcher()
    private val settings = MapSettings(
        KEY_ADDRESS to "defaultAddress",
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
        viewModel.address = "google.com"
        viewModel.username = "John"
        viewModel.password = "Secret"
        viewModel.login()

        val state = viewModel.uiState.value
        assertFalse(state.isLoading)
        assertNull(state.errorMessage)
    }

    @Test
    fun `fetch credentials`() = runTest {
        viewModel.fetchLoginCredentials()
        val state = viewModel.uiState.value
        assertEquals("defaultAddress",state.address)
        assertEquals("defaultUsername",state.username)
        assertEquals("defaultPassword",state.password)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cancel()
    }

    companion object {
        private const val KEY_ADDRESS = "address"
        private const val KEY_USERNAME = "username"
        private const val KEY_PASSWORD = "password"
    }
}