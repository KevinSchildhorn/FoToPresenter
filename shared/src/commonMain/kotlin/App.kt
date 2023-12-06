import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.kevinschildhorn.fotopresenter.ui.compose.DirectoryScreen
import com.kevinschildhorn.fotopresenter.ui.compose.ImagePreviewOverlay
import com.kevinschildhorn.fotopresenter.ui.compose.LoginScreen
import com.kevinschildhorn.fotopresenter.ui.compose.Screen
import com.kevinschildhorn.fotopresenter.ui.viewmodel.DirectoryViewModel
import com.kevinschildhorn.fotopresenter.ui.viewmodel.LoginViewModel

@Composable
fun App(
    loginViewModel: LoginViewModel,
    directoryViewModel: DirectoryViewModel,
) {
    val currentScreen = remember { mutableStateOf(Screen.LOGIN) }

    MaterialTheme {
        when (currentScreen.value) {
            Screen.LOGIN ->
                LoginScreen(loginViewModel) {
                    currentScreen.value = Screen.DIRECTORY
                }
            Screen.DIRECTORY -> DirectoryScreen(directoryViewModel) {
            }
        }
    }
}

expect fun getPlatformName(): String
