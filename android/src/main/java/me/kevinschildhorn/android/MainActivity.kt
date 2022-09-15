package me.kevinschildhorn.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.*
import me.kevinschildhorn.android.ui.SignUpScreen
import me.kevinschildhorn.common.getPlatformName
import me.kevinschildhorn.common.ui.SharedEnabledColor
import me.kevinschildhorn.common.viewmodel.EmailValidationViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                App()
            }
        }
    }

    @Composable
    fun App() {
        var text by remember { mutableStateOf("Hello, World!") }
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
                viewModel = EmailValidationViewModel()
            )
            Button(onClick = {
                text = "Hello, ${getPlatformName()}"
            }, content = {})
        }
    }
}

@Composable
fun SharedEnabledColor.asComposable2(): ButtonColors =
    ButtonDefaults.buttonColors(
        backgroundColor = this.color.androidColor,
        disabledBackgroundColor = this.disabledColor.androidColor
    )
