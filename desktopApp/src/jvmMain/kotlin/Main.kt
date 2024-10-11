import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import co.touchlab.kermit.Logger
import com.kevinschildhorn.fotopresenter.UseCaseFactory
import com.kevinschildhorn.fotopresenter.ui.screens.directory.DirectoryViewModel
import com.kevinschildhorn.fotopresenter.ui.screens.login.LoginViewModel
import com.kevinschildhorn.fotopresenter.ui.screens.playlist.PlaylistViewModel
import com.kevinschildhorn.fotopresenter.ui.screens.slideshow.SlideshowViewModel


object KoinPurse {
    val loginViewModel =
        LoginViewModel(Logger.withTag("LoginViewModel"), UseCaseFactory.credentialsRepository)
    val directoryViewModel =
        DirectoryViewModel(UseCaseFactory.playlistRepository, Logger.withTag("DirectoryViewModel"))
    val slideshowViewModel = SlideshowViewModel(Logger.withTag("SlideshowViewModel"))
    val playlistViewModel =
        PlaylistViewModel(UseCaseFactory.playlistRepository, Logger.withTag("PlaylistViewModel"))
}

fun main() = application {
    Window(
        title = "FotoPresenter",
        onCloseRequest = ::exitApplication
    ) {
        MainView(
            KoinPurse.loginViewModel,
            KoinPurse.directoryViewModel,
            KoinPurse.slideshowViewModel,
            KoinPurse.playlistViewModel,
        )
    }
}
