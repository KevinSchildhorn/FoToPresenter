package com.kevinschildhorn.fotopresenter.domain

import com.kevinschildhorn.fotopresenter.data.network.MockNetworkHandler
import com.kevinschildhorn.fotopresenter.testingModule
import kotlinx.coroutines.runBlocking
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.AfterTest
import kotlin.test.BeforeTest

/**
Testing [RetrieveImageDirectoriesUseCase]
 **/
class RetrieveImageDirectoriesUseCaseTest : KoinTest {
    private val useCase: RetrieveImageDirectoriesUseCase by inject()

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
/*
    @Test
    fun `receive_directory_content_success`() =
        runBlocking {
            val details =
                DefaultNetworkDirectoryDetails(
                    Path(""),
                    1,
                )
            val result = useCase(details)
            assertEquals(6, result.count())
        }

    @Test
    fun `receive_directory_content_only_directories`() =
        runBlocking {
            val details = DefaultNetworkDirectoryDetails(Path("Directories"), 1)
            val result = useCase(details)
            assertEquals(0, result.count())
        }

    @Test
    fun `receive_directory_content_failure`() =
        runBlocking {
            val details = DefaultNetworkDirectoryDetails(Path("nonExistant"), 1)
            val result = useCase(details)
            assertEquals(0, result.count())
        }

    @Test
    fun `receive_directory_content_disconnected`() =
        runBlocking {
            MockNetworkHandler.disconnect()
            try {
                val details = DefaultNetworkDirectoryDetails(Path("Photos"), 1)
                val result = useCase(details)
                fail("Should've thrown")
            } catch (e: NetworkHandlerException) {
                assertEquals(NetworkHandlerError.NOT_CONNECTED.message, e.message)
            }
        }*/
}
