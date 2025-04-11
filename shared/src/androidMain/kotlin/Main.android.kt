import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.kevinschildhorn.fotopresenter.ui.screens.directory.DirectoryViewModel
import com.kevinschildhorn.fotopresenter.ui.screens.login.LoginViewModel
import com.kevinschildhorn.fotopresenter.ui.screens.playlist.PlaylistViewModel
import com.kevinschildhorn.fotopresenter.ui.screens.slideshow.SlideshowViewModel

actual fun getPlatformName(): String = "Android"

@Composable
fun MainView(
    loginViewModel: LoginViewModel,
    directoryViewModel: DirectoryViewModel,
    slideshowViewModel: SlideshowViewModel,
    playlistViewModel: PlaylistViewModel,
) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        // Update all of the system bar colors to be transparent, and use
        // dark icons if we're use a light theme
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = true
        )
    }
    App(loginViewModel, directoryViewModel, slideshowViewModel, playlistViewModel)
}
