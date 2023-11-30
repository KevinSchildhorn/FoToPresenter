import androidx.compose.runtime.Composable
import com.kevinschildhorn.fotopresenter.ui.viewmodel.LoginViewModel

actual fun getPlatformName(): String = "Android"

@Composable fun MainView(viewModel: LoginViewModel) = App(viewModel)
