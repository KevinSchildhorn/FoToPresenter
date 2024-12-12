package com.kevinschildhorn.fotopresenter.domain

import com.kevinschildhorn.fotopresenter.data.Path
import com.kevinschildhorn.fotopresenter.data.network.MockNetworkHandler
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandlerError
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandlerException
import com.kevinschildhorn.fotopresenter.domain.directory.ChangeDirectoryUseCase
import com.kevinschildhorn.fotopresenter.testingModule
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.assertEquals
import kotlin.test.fail

/**
Testing [ChangeDirectoryUseCase]
 **/
class ChangeDirectoryUseCaseTest : KoinTest {
    private val useCase: ChangeDirectoryUseCase by inject()

    @BeforeTest
    fun startTest() =
        runBlocking {
            startKoin {
                modules(testingModule())
            }
            MockNetworkHandler.connectSuccessfully()
        }

    @AfterTest
    fun tearDown() =
        runBlocking {
            stopKoin()
            MockNetworkHandler.disconnect()
        }

    @Test
    fun `change directory success`() =
        runBlocking {
            val result = useCase(Path("Photos"))
            assertEquals(Path("Photos"), result)
        }

    @Test
    fun `change directory failure`() =
        runBlocking {
            try {
                val result = useCase(Path("nonExistant"))
                fail("Should've thrown")
            } catch (e: NetworkHandlerException) {
                assertEquals(NetworkHandlerError.DIRECTORY_NOT_FOUND.message, e.message)
            }
        }

    @Test
    fun `change directory disconnected`() =
        runBlocking {
            MockNetworkHandler.disconnect()
            try {
                val result = useCase(Path("Photos"))
                fail("Should've thrown")
            } catch (e: NetworkHandlerException) {
                assertEquals(NetworkHandlerError.NOT_CONNECTED.message, e.message)
            }
        }
}
