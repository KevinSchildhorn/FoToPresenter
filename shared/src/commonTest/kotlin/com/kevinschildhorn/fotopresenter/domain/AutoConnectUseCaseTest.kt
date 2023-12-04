package com.kevinschildhorn.fotopresenter.domain

import com.kevinschildhorn.fotopresenter.testingModule
import com.russhwolf.settings.MapSettings
import kotlinx.coroutines.runBlocking
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
Testing [AutoConnectUseCase]
 **/
class AutoConnectUseCaseTest : KoinTest {
    private val useCase: AutoConnectUseCase by inject()

    @AfterTest
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `connect to server success`() =
        runBlocking {
            startKoin {
                modules(
                    testingModule(
                        settings =
                        MapSettings(
                            KEY_HOSTNAME to "192.168.1.1",
                            KEY_USERNAME to "admin",
                            KEY_PASSWORD to "password",
                            KEY_SHAREDFOLDER to "Public",
                            KEY_AUTOCONNECT to false,
                        ),
                    ),
                )
            }

            val result = useCase()
            assertTrue(result, "Failed to Connect to Server")
        }

    @Test
    fun `connect to server failure`() =
        runBlocking {
            startKoin {
                modules(
                    testingModule(
                        settings =
                        MapSettings(
                            KEY_HOSTNAME to "google.com",
                            KEY_USERNAME to "admin",
                            KEY_PASSWORD to "password",
                            KEY_SHAREDFOLDER to "Public",
                            KEY_AUTOCONNECT to false,
                        ),
                    ),
                )
            }
            val result = useCase()
            assertFalse(result, "Should not have Connected to Server")
        }

    @Test
    fun `connect to server empty failure`() =
        runBlocking {
            startKoin {
                modules(testingModule())
            }
            val result = useCase()
            assertFalse(result, "Should not have Connected to Server")
        }

    @Test
    fun `connect to server throw`() =
        runBlocking {
            startKoin {
                modules(
                    testingModule(
                        settings =
                        MapSettings(
                            KEY_HOSTNAME to "throw",
                            KEY_USERNAME to "admin",
                            KEY_PASSWORD to "password",
                            KEY_SHAREDFOLDER to "Public",
                            KEY_AUTOCONNECT to false,
                        ),
                    ),
                )
            }

            val result = useCase()
            assertFalse(result, "Should not have Connected to Server")
        }

    companion object {
        private const val KEY_HOSTNAME = "hostname"
        private const val KEY_SHAREDFOLDER = "sharedFolder"
        private const val KEY_USERNAME = "username"
        private const val KEY_PASSWORD = "password"
        private const val KEY_AUTOCONNECT = "autoConnect"
    }
}
