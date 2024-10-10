package com.kevinschildhorn.fotopresenter.domain

import co.touchlab.kermit.Logger
import co.touchlab.kermit.LoggerConfig
import com.kevinschildhorn.fotopresenter.data.LoginCredentials
import com.kevinschildhorn.fotopresenter.domain.connection.SaveCredentialsUseCase
import com.kevinschildhorn.fotopresenter.testingModule
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.AfterTest
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
        val baseLogger = Logger.withTag("Test")

        startKoin {
            modules(testingModule())
        }
    }

    @AfterTest
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `save credentials success`() {
        val loginCredentials =
            LoginCredentials(
                "192.168.1.1",
                "admin",
                "password",
                "Public",
                shouldAutoConnect = false,
            )
        val result = useCase.invoke(loginCredentials)
        assertTrue(result, "Failed to save credentials")
    }
}
