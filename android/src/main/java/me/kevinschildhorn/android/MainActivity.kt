package me.kevinschildhorn.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.runtime.*
import me.kevinschildhorn.common.getPlatformName
import me.kevinschildhorn.common.theme.SharedButtonColors
import me.kevinschildhorn.common.theme.ThemeButtonColors
import me.kevinschildhorn.common.theme.asComposable
import me.kevinschildhorn.common.ui.SharedEnabledColor

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

        val test1 = ThemeButtonColors.default
        val test2 = test1.color.androidColor
        val test = ButtonDefaults.buttonColors(
            backgroundColor = ThemeButtonColors.default.color.androidColor,
            disabledBackgroundColor = ThemeButtonColors.default.disabledColor.androidColor
        )
        val test4 = ThemeButtonColors.default.asComposable2()
        /*
        Box(
            modifier = Modifier.background(
                color = test2
            ).height(90.dp).width(90.dp)
        )*/
        // Button

        Button(onClick = {
            text = "Hello, ${getPlatformName()}"
        },
        colors = test4
            ) {
            Text(text)
        }
    }
}

@Composable
fun SharedEnabledColor.asComposable2(): ButtonColors =
    ButtonDefaults.buttonColors(
        backgroundColor = this.color.androidColor,
        disabledBackgroundColor = this.disabledColor.androidColor
    )
