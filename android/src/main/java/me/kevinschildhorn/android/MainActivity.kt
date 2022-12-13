package me.kevinschildhorn.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import me.kevinschildhorn.android.ui.SignUpScreen
import me.kevinschildhorn.common.design.color.SharedEnabledColor
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
            MaterialTheme {
                App()
            }
        }
    }

    @Composable
    fun App() {
        var text by remember { mutableStateOf("Hello, World!") }
        SignUpScreen(viewModel)
/*
        val test1 = ThemeButtonColors.default
        val test2 = test1.color.androidColor
        val test = ButtonDefaults.buttonColors(
            backgroundColor = ThemeButtonColors.default.color.androidColor,
            disabledBackgroundColor = ThemeButtonColors.default.disabledColor.androidColor
        )
        val test4 = ThemeButtonColors.default.asComposable2()
        */
        /*
        Box(
            modifier = Modifier.background(
                color = test2
            ).height(90.dp).width(90.dp)
        )*/
        // Button

        Column {
            SignUpScreen(
                viewModel = viewModel
            )
        }
    }
}

@Composable
fun SharedEnabledColor.asComposable2(): ButtonColors =
    ButtonDefaults.buttonColors(
        backgroundColor = this.color.androidColor,
        disabledBackgroundColor = this.disabledColor.androidColor
    )
