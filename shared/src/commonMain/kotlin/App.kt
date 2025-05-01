import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kevinschildhorn.fotopresenter.ui.atoms.FotoTypography
import com.kevinschildhorn.fotopresenter.ui.atoms.fotoColors
import com.kevinschildhorn.fotopresenter.ui.atoms.fotoShapes
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
    navController: NavHostController = rememberNavController(),
) {
    MaterialTheme(
        colors = fotoColors,
        typography = FotoTypography(),
        shapes = fotoShapes,
    ) {
        NavHost(
            navController = navController,
            startDestination = Screen.LOGIN.name,
            modifier = Modifier.fillMaxSize().safeDrawingPadding(),
        ) {
            composable(route = Screen.LOGIN.name) {
                LoginScreen(loginViewModel) {
                    navController.navigate(Screen.DIRECTORY.name)
                }
            }
            composable(route = Screen.DIRECTORY.name) {
                DirectoryScreen(
                    directoryViewModel,
                    onLogout = {
                        loginViewModel.setLoggedOut()
                        navController.navigate(Screen.LOGIN.name)
                    },
                    onStartSlideshow = {
                        slideshowViewModel.startSlideshow(it)
                        directoryViewModel.clearSlideshow()
                        navController.navigate(Screen.SLIDESHOW.name)
                    },
                    onShowPlaylists = {
                        navController.navigate(Screen.PLAYLIST.name)
                    },
                )
            }
            composable(route = Screen.SLIDESHOW.name) {
                SlideshowScreen(slideshowViewModel) {
                    navController.navigate(Screen.DIRECTORY.name)
                }
            }
            composable(route = Screen.PLAYLIST.name) {
                PlaylistScreen(playlistViewModel, overlaid = false) {
                    slideshowViewModel.setSlideshowFromPlaylist(it)
                    navController.navigate(Screen.SLIDESHOW.name)
                }
            }
        }
    }
}

expect fun getPlatformName(): String
