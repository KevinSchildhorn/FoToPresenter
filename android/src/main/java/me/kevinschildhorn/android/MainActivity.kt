package me.kevinschildhorn.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import me.kevinschildhorn.android.ui.screens.LoginScreen
import me.kevinschildhorn.common.architecture.ui.viewmodel.LoginViewModel
import me.kevinschildhorn.common.startKoin
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent

class MainActivity : AppCompatActivity(), KoinComponent {

    private val viewModel by viewModel<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startKoin(this)
        setContent {
            MaterialTheme(
                // colors = UIDesign.composeColors(isSystemInDarkTheme()),
                // typography = UIDesign.composeTypography(fontFamily)
            ) {
                App()
            }
        }
    }

    @Composable
    fun App() {
        Column {
            LoginScreen(viewModel = viewModel)
        }
    }
}

val fontFamily = FontFamily(
    Font(R.font.quicksand_regular),
    Font(R.font.quicksand_bold, FontWeight.Bold),
    Font(R.font.quicksand_light, FontWeight.Light),
    Font(R.font.quicksand_medium, FontWeight.Medium),
    Font(R.font.quicksand_semibold, FontWeight.SemiBold),
)
