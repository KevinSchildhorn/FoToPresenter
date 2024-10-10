package com.kevinschildhorn.fotopresenter.ui.atoms

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Colors
import androidx.compose.material.Shapes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

val fotoColors = Colors(
    primary = Color(0xFFFFA500),
    primaryVariant = Color(0xFFFFA500),
    secondary = Color(0xFFFFD383),
    secondaryVariant = Color(0xFFFFD383),
    background = Color(0xFFFFFFFF),
    surface = Color(0xFFFFFFFF),
    error = Color(0xFFFF0000),
    onPrimary = Color(0xFF402900),
    onSecondary = Color(0xFFC1872E),
    onBackground = Color(0xFF25231F),
    onSurface = Color(0xFFC2882E),
    onError = Color(0xFF9E1F1F),
    isLight = true
)

val disabled = Color(0xE0E0E0)
val shadow = Color(red = 0f, green = 0f, blue = 0f, alpha = 0.25f)

val fotoShapes = Shapes(
    small = RoundedCornerShape(5.dp),
    medium = RoundedCornerShape(10.dp),
    large = RoundedCornerShape(20.dp),
)