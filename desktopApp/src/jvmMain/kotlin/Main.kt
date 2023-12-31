import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.kevinschildhorn.fotopresenter.UseCaseFactory
import com.kevinschildhorn.fotopresenter.ui.screens.directory.DirectoryViewModel
import com.kevinschildhorn.fotopresenter.ui.screens.login.LoginViewModel
import com.kevinschildhorn.fotopresenter.ui.screens.playlist.PlaylistViewModel
import com.kevinschildhorn.fotopresenter.ui.screens.slideshow.SlideshowViewModel


object KoinPurse {
    val loginViewModel =
        LoginViewModel(UseCaseFactory.baseLogger, UseCaseFactory.credentialsRepository)
    val directoryViewModel =
        DirectoryViewModel(UseCaseFactory.playlistRepository, UseCaseFactory.baseLogger)
    val slideshowViewModel = SlideshowViewModel(UseCaseFactory.baseLogger)
    val playlistViewModel =
        PlaylistViewModel(UseCaseFactory.playlistRepository, UseCaseFactory.baseLogger)
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        MainView(
            KoinPurse.loginViewModel,
            KoinPurse.directoryViewModel,
            KoinPurse.slideshowViewModel,
            KoinPurse.playlistViewModel,
        )
    }
}
