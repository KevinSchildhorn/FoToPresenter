import androidx.compose.runtime.Composable
import com.kevinschildhorn.fotopresenter.ui.viewmodel.DirectoryViewModel
import com.kevinschildhorn.fotopresenter.ui.viewmodel.LoginViewModel

actual fun getPlatformName(): String = "Android"

@Composable
fun MainView(
    loginViewModel: LoginViewModel,
    directoryViewModel: DirectoryViewModel,
) = App(loginViewModel, directoryViewModel)
