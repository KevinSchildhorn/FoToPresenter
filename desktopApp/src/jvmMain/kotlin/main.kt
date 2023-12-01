import androidx.compose.material.Text
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.kevinschildhorn.fotopresenter.ui.compose.LoginScreen

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        Text("")
    }
}