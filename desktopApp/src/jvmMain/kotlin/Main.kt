import KoinPurse.imageRepository
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import co.touchlab.kermit.Logger
import coil3.ImageLoader
import com.kevinschildhorn.fotopresenter.UseCaseFactory
import com.kevinschildhorn.fotopresenter.baseLogger
import com.kevinschildhorn.fotopresenter.ui.SMBJFetcher
import com.kevinschildhorn.fotopresenter.ui.screens.directory.DirectoryViewModel
import com.kevinschildhorn.fotopresenter.ui.screens.login.LoginViewModel
import com.kevinschildhorn.fotopresenter.ui.screens.playlist.PlaylistViewModel
import com.kevinschildhorn.fotopresenter.ui.screens.slideshow.SlideshowViewModel
import coil3.compose.setSingletonImageLoaderFactory
import com.kevinschildhorn.fotopresenter.data.datasources.image.CachedImageDataSource
import com.kevinschildhorn.fotopresenter.data.datasources.image.NetworkImageDataSource
import com.kevinschildhorn.fotopresenter.data.network.SMBJHandler
import com.kevinschildhorn.fotopresenter.data.repositories.ImageRepository
import com.kevinschildhorn.fotopresenter.ui.ByteArrayFetcher
import com.kevinschildhorn.fotopresenter.ui.shared.SharedFileCache


object KoinPurse {
    private val remoteImageDataSource: NetworkImageDataSource = NetworkImageDataSource(SMBJHandler)
    private val localImageDataSource: CachedImageDataSource =
        CachedImageDataSource(SharedFileCache("cache"), Logger.withTag("CachedImageDataSource"))

    val loginViewModel =
        LoginViewModel(Logger.withTag("LoginViewModel"), UseCaseFactory.credentialsRepository)
    val directoryViewModel =
        DirectoryViewModel(UseCaseFactory.playlistRepository, Logger.withTag("DirectoryViewModel"))
    val slideshowViewModel = SlideshowViewModel(Logger.withTag("SlideshowViewModel"))
    val playlistViewModel =
        PlaylistViewModel(UseCaseFactory.playlistRepository, Logger.withTag("PlaylistViewModel"))
    val imageRepository =
        ImageRepository(
            remoteImageDataSource,
            localImageDataSource,
            Logger.withTag("ImageRepository")
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
                    add(SMBJFetcher.Factory(imageRepository, Logger.withTag("SMBJFetcher")))
                    add(ByteArrayFetcher.Factory(Logger.withTag("ByteArrayFetcher")))
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
