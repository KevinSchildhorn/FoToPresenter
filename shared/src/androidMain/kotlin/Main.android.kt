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
        val color = Color.Black
        val darkIcons = true
        systemUiController.setNavigationBarColor(color = color, darkIcons = darkIcons)
        systemUiController.setStatusBarColor(color = color, darkIcons = darkIcons)
        systemUiController.setSystemBarsColor(color = color, darkIcons = darkIcons)

        systemUiController.isStatusBarVisible = false
        systemUiController.isNavigationBarVisible = false
        systemUiController.isSystemBarsVisible = false
    }

    slideshowViewModel.onGoFullScreen = {
        systemUiController.isStatusBarVisible = false
        systemUiController.isNavigationBarVisible = false
        systemUiController.isSystemBarsVisible = false
    }

    App(loginViewModel, directoryViewModel, slideshowViewModel, playlistViewModel)
}
