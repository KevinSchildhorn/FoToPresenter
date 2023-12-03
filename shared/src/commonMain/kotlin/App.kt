import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.kevinschildhorn.fotopresenter.ui.compose.DirectoryScreen
import com.kevinschildhorn.fotopresenter.ui.compose.LoginScreen
import com.kevinschildhorn.fotopresenter.ui.compose.Screen
import com.kevinschildhorn.fotopresenter.ui.viewmodel.LoginViewModel

@Composable
fun App(viewModel: LoginViewModel) {

    val currentScreen = remember { mutableStateOf(Screen.LOGIN) }

    MaterialTheme {
        when (currentScreen.value) {
            Screen.LOGIN -> LoginScreen(viewModel) {
                currentScreen.value = Screen.DIRECTORY
            }

            Screen.DIRECTORY -> DirectoryScreen()
        }

    }
}

expect fun getPlatformName(): String