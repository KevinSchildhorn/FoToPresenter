import KoinPurse.imageRepository
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import co.touchlab.kermit.Logger
import coil3.ImageLoader
import coil3.compose.setSingletonImageLoaderFactory
import com.kevinschildhorn.fotopresenter.UseCaseFactory
import com.kevinschildhorn.fotopresenter.UseCaseFactory.credentialsRepository
import com.kevinschildhorn.fotopresenter.UseCaseFactory.directoryRepository
import com.kevinschildhorn.fotopresenter.data.DirectoryNavigator
import com.kevinschildhorn.fotopresenter.data.ImagePreviewNavigator
import com.kevinschildhorn.fotopresenter.data.datasources.ImageMetadataDataSource
import com.kevinschildhorn.fotopresenter.data.datasources.image.CachedImageDataSource
import com.kevinschildhorn.fotopresenter.data.datasources.image.NetworkImageDataSource
import com.kevinschildhorn.fotopresenter.data.network.SMBJHandler
import com.kevinschildhorn.fotopresenter.data.repositories.ImageRepository
import com.kevinschildhorn.fotopresenter.ui.SMBJFetcher
import com.kevinschildhorn.fotopresenter.ui.SharedImageFetcher
import com.kevinschildhorn.fotopresenter.ui.screens.directory.DirectoryViewModel
import com.kevinschildhorn.fotopresenter.ui.screens.login.LoginViewModel
import com.kevinschildhorn.fotopresenter.ui.screens.playlist.PlaylistViewModel
import com.kevinschildhorn.fotopresenter.ui.screens.slideshow.SlideshowViewModel
import com.kevinschildhorn.fotopresenter.ui.shared.SharedFileCache


object KoinPurse {
    val baseLogger = Logger
    private val remoteImageDataSource: NetworkImageDataSource = NetworkImageDataSource(SMBJHandler)
    private val localImageDataSource: CachedImageDataSource =
        CachedImageDataSource(
            SharedFileCache("cache", baseLogger.withTag("SharedFileCache")),
            baseLogger.withTag("CachedImageDataSource")
        )

    val loginViewModel =
        LoginViewModel(
            baseLogger.withTag("LoginViewModel"), UseCaseFactory.credentialsRepository,
            SMBJHandler
        )

    val imageMetadataDataSource = ImageMetadataDataSource(
        logger = baseLogger.withTag("ImageMetadataDataSource"),
        SMBJHandler,
    )
    val directoryNavigator = DirectoryNavigator(directoryRepository, baseLogger.withTag("DirectoryNavigator"))
    val imagePreviewNavigator = ImagePreviewNavigator(baseLogger.withTag("ImagePreviewNavigator"))
    val directoryViewModel = DirectoryViewModel(
        directoryNavigator = directoryNavigator,
        imagePreviewNavigator = imagePreviewNavigator,
        credentialsRepository = credentialsRepository,
        networkHandler = SMBJHandler,
        dataSource = imageMetadataDataSource,
        logger = baseLogger.withTag("DirectoryViewModelNew")
    )

    val slideshowViewModel = SlideshowViewModel(baseLogger.withTag("SlideshowViewModel"))
    val playlistViewModel =
        PlaylistViewModel(
            UseCaseFactory.playlistRepository,
            baseLogger.withTag("PlaylistViewModel")
        )
    val imageRepository =
        ImageRepository(
            remoteImageDataSource,
            baseLogger.withTag("ImageRepository")
        )
}

fun main() = application {
    Window(
        title = "FotoPresenter",
        onCloseRequest = ::exitApplication
    ) {

        setSingletonImageLoaderFactory { context ->
            ImageLoader.Builder(context)
                .components {
                    add(SMBJFetcher.Factory(imageRepository, KoinPurse.baseLogger))
                    add(SharedImageFetcher.Factory(KoinPurse.baseLogger))
                }
                .build()
        }

        MainView(
            KoinPurse.loginViewModel,
            KoinPurse.directoryViewModel,
            KoinPurse.slideshowViewModel,
            KoinPurse.playlistViewModel,
        )
    }
}
