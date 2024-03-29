import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.kevinschildhorn.fotopresenter.ui.screens.common.Screen
import com.kevinschildhorn.fotopresenter.ui.screens.directory.DirectoryScreen
import com.kevinschildhorn.fotopresenter.ui.screens.directory.DirectoryViewModel
import com.kevinschildhorn.fotopresenter.ui.screens.login.LoginScreen
import com.kevinschildhorn.fotopresenter.ui.screens.login.LoginViewModel
import com.kevinschildhorn.fotopresenter.ui.screens.playlist.PlaylistScreen
import com.kevinschildhorn.fotopresenter.ui.screens.playlist.PlaylistViewModel
import com.kevinschildhorn.fotopresenter.ui.screens.slideshow.SlideshowScreen
import com.kevinschildhorn.fotopresenter.ui.screens.slideshow.SlideshowViewModel

@Composable
fun App(
    loginViewModel: LoginViewModel,
    directoryViewModel: DirectoryViewModel,
    slideshowViewModel: SlideshowViewModel,
    playlistViewModel: PlaylistViewModel,
) {
    val currentScreen = remember { mutableStateOf(Screen.LOGIN) }

    MaterialTheme {
        when (currentScreen.value) {
            Screen.LOGIN ->
                LoginScreen(loginViewModel) {
                    directoryViewModel.setLoggedIn()
                    currentScreen.value = Screen.DIRECTORY
                }

            Screen.DIRECTORY ->
                DirectoryScreen(
                    directoryViewModel,
                    onLogout = {
                        loginViewModel.setLoggedOut()
                        currentScreen.value = Screen.LOGIN
                    },
                    onStartSlideshow = {
                        slideshowViewModel.setSlideshow(it)
                        directoryViewModel.clearSlideshow()
                        currentScreen.value = Screen.SLIDESHOW
                    },
                    onShowPlaylists = {
                        currentScreen.value = Screen.PLAYLIST
                    }
                )

            Screen.SLIDESHOW ->
                SlideshowScreen(slideshowViewModel) {
                    currentScreen.value = Screen.DIRECTORY
                }

            Screen.PLAYLIST -> {
                PlaylistScreen(playlistViewModel, overlaid = false) {
                    slideshowViewModel.setSlideshowFromPlaylist(it)
                    currentScreen.value = Screen.SLIDESHOW
                }
            }
        }
    }
}

expect fun getPlatformName(): String
