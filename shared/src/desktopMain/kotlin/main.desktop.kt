import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import com.kevinschildhorn.fotopresenter.ui.viewmodel.LoginViewModel
import org.koin.java.KoinJavaComponent.inject

actual fun getPlatformName(): String = "Desktop"
/*
private val viewModel: LoginViewModel by inject()

@Composable fun MainView() = App(viewModel)

@Preview
@Composable
fun AppPreview() {
    App(viewModel)
}*/