package com.kevinschildhorn.fotopresenter.domain

import com.kevinschildhorn.fotopresenter.data.Path
import com.kevinschildhorn.fotopresenter.data.network.MockNetworkHandler
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandlerError
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandlerException
import com.kevinschildhorn.fotopresenter.testingModule
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.String
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.fail

/**
Testing [RetrieveDirectoryContentsUseCase]
 **/
class RetrieveDirectoryContentsUseCaseTest : KoinTest {
    private val useCase: RetrieveDirectoryContentsUseCase by inject()

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
    fun receiveDirectoryContentSuccess() =
        runBlocking {
            val result = useCase(Path(""), recursively = false)
            assertTrue(result.images.first().details.isAnImage)
            assertTrue(result.folders.first().details.isDirectory)
            assertEquals(2, result.folders.count())
            assertEquals(2, result.images.count())
            assertEquals(4, result.allDirectories.count())
        }

    @Test
    fun receiveDirectoryContentOnlyDirectories() =
        runBlocking {
            val result = useCase(Path("Directories"), recursively = false)
            assertEquals(2, result.folders.count())
            assertEquals(0, result.images.count())
            assertEquals(2, result.allDirectories.count())
        }

    @Test
    fun receiveDirectoryContentFailure() =
        runBlocking {
            val result = useCase(Path("nonExistant"), recursively = false)
            assertEquals(0, result.folders.count())
            assertEquals(0, result.images.count())
            assertEquals(0, result.allDirectories.count())
        }

    @Test
    fun receiveDirectoryContentDisconnected() =
        runBlocking {
            MockNetworkHandler.disconnect()
            try {
                val result = useCase(Path("Photos"), recursively = false)
                fail("Should've thrown")
            } catch (e: NetworkHandlerException) {
                assertEquals(NetworkHandlerError.NOT_CONNECTED.message, e.message)
            }
        }

    @Test
    fun receiveDirectoryContentWithDate() =
        runBlocking {
            /*
            fullPath = Path("Peeng.png"), id = 75,
            dateMillis = getMillis(2024, Month.MAY, dayOfMonth = 20),

            fullPath = Path("Jaypeg.jpg"), id = 3,
            dateMillis = getMillis(2024, Month.MAY, dayOfMonth = 10),
             */
            var result = useCase(
                path = Path(""),
                recursively = false,
                startDate = null,
                endDate = null,
            )
            assertEquals(2, result.images.count())

            result = useCase(
                path = Path(""),
                recursively = false,
                startDate = LocalDate(2020, 5, 1),
                endDate = LocalDate(2024, 5, 15),
            )
            assertEquals(1, result.images.count())
        }

    @Test
    fun receiveDirectoryContentWithTag() =
        runBlocking {
            /*
            fullPath = Path("Peeng.png"), id = 75,
            dateMillis = getMillis(2024, Month.MAY, dayOfMonth = 20),

            fullPath = Path("Jaypeg.jpg"), id = 3,
            dateMillis = getMillis(2024, Month.MAY, dayOfMonth = 10),
             */
            var result = useCase(
                path = Path(""),
                recursively = false,
                tags = emptyList(),
                inclusiveTags = false,
            )
            assertEquals(2, result.images.count())

            result = useCase(
                path = Path(""),
                recursively = false,
                tags = listOf("P"),
                inclusiveTags = false,
            )
            assertEquals(1, result.images.count())

            result = useCase(
                path = Path(""),
                recursively = false,
                tags = listOf("P", "J"),
                inclusiveTags = true,
            )
            assertEquals(0, result.images.count())

            result = useCase(
                path = Path(""),
                recursively = false,
                tags = listOf("P", "png"),
                inclusiveTags = true,
            )
            assertEquals(1, result.images.count())
        }

}
