import androidx.compose.runtime.Composable
import com.kevinschildhorn.fotopresenter.ui.screens.directory.DirectoryViewModel
import com.kevinschildhorn.fotopresenter.ui.screens.directory.DirectoryViewModelTwo
import com.kevinschildhorn.fotopresenter.ui.screens.login.LoginViewModel
import com.kevinschildhorn.fotopresenter.ui.screens.playlist.PlaylistViewModel
import com.kevinschildhorn.fotopresenter.ui.screens.slideshow.SlideshowViewModel

actual fun getPlatformName(): String = "Android"

@Composable
fun MainView(
    loginViewModel: LoginViewModel,
    directoryViewModel: DirectoryViewModelTwo,
    slideshowViewModel: SlideshowViewModel,
    playlistViewModel: PlaylistViewModel,
    ) = App(loginViewModel, directoryViewModel, slideshowViewModel, playlistViewModel)
