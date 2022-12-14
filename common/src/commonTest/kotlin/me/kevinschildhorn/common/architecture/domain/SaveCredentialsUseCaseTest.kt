package me.kevinschildhorn.common.architecture.domain

import com.russhwolf.settings.MapSettings
import com.russhwolf.settings.Settings
import me.kevinschildhorn.common.architecture.data.datasources.CredentialsDataSource
import me.kevinschildhorn.common.architecture.data.repositories.CredentialsRepository
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

/**
Testing [SaveCredentialsUseCase]
 **/
class SaveCredentialsUseCaseTest : KoinTest {

    private val useCase: SaveCredentialsUseCase by inject()

    @BeforeTest
    fun startTest() {
        startKoin {
            modules(
                module {
                    single<Settings> { MapSettings() }
                    single { CredentialsDataSource(get()) }
                    single { CredentialsRepository(get()) }
                    single { SaveCredentialsUseCase(get()) }
                }
            )
        }
    }

    @Test
    fun `save credentials`() {
        val hostname = "google.com"
        val username = "John"
        val password = "secret"
        val result = useCase.invoke(hostname, username, password)
        assertTrue(result, "Failed to save credentials")
    }
}
