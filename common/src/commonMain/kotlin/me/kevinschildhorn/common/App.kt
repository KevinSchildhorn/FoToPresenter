package me.kevinschildhorn.common

import androidx.compose.material.Text
import androidx.compose.material.Button
import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.material.Typography
import androidx.compose.ui.text.font.FontWeight.Companion.W300
import androidx.compose.ui.unit.sp
import javafx.scene.text.FontWeight

@Composable
fun App() {
    var text by remember { mutableStateOf("Hello, World!") }

    // Button
    Button(onClick = {
        text = "Hello, ${getPlatformName()}"
    }) {
        Text(text)
    }
}
