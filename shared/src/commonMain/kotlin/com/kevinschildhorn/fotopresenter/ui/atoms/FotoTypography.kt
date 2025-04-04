@file:Suppress("ktlint:standard:class-naming")

package com.kevinschildhorn.fotopresenter.ui.atoms

import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.kevinschildhorn.fotopresenter.Res
import com.kevinschildhorn.fotopresenter.quicksand_bold
import com.kevinschildhorn.fotopresenter.quicksand_light
import com.kevinschildhorn.fotopresenter.quicksand_medium
import com.kevinschildhorn.fotopresenter.quicksand_regular
import com.kevinschildhorn.fotopresenter.quicksand_semibold
import org.jetbrains.compose.resources.Font

@Composable
fun QuicksandFontFamily() =
    FontFamily(
        Font(Res.font.quicksand_light, weight = FontWeight.Light),
        Font(Res.font.quicksand_regular, weight = FontWeight.Normal),
        Font(Res.font.quicksand_medium, weight = FontWeight.Medium),
        Font(Res.font.quicksand_semibold, weight = FontWeight.SemiBold),
        Font(Res.font.quicksand_bold, weight = FontWeight.Bold),
    )

@Composable
fun FotoTypography() =
    Typography().run {
        val fontFamily = QuicksandFontFamily()
        this.copy(
            h1 = TextStyle( fontSize = 96.sp, fontWeight = FontWeight.Normal, fontFamily = fontFamily),
            h2 = TextStyle(fontSize = 48.sp, fontWeight = FontWeight.Normal, fontFamily = fontFamily),
            h3 = TextStyle(fontSize = 36.sp, fontWeight = FontWeight.Normal, fontFamily = fontFamily),
            h4 = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Normal, fontFamily = fontFamily),
            subtitle1 = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal, fontFamily = fontFamily),
            subtitle2 = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal, fontFamily = fontFamily),
            button = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold, fontFamily = fontFamily),
            body1 = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal, fontFamily = fontFamily),
            body2 = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal, fontFamily = fontFamily),
            caption = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold, fontFamily = fontFamily),
        )
    }
