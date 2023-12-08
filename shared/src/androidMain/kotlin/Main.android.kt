import androidx.compose.runtime.Composable
import com.kevinschildhorn.fotopresenter.ui.viewmodel.DirectoryViewModel
import com.kevinschildhorn.fotopresenter.ui.viewmodel.LoginViewModel
import com.kevinschildhorn.fotopresenter.ui.viewmodel.SlideshowViewModel

actual fun getPlatformName(): String = "Android"

@Composable
fun MainView(
    loginViewModel: LoginViewModel,
    directoryViewModel: DirectoryViewModel,
    slideshowViewModel: SlideshowViewModel,
) = App(loginViewModel, directoryViewModel, slideshowViewModel)
