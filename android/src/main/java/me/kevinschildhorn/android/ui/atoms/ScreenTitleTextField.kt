package me.kevinschildhorn.android.ui.atoms

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import me.kevinschildhorn.android.fontFamily
import me.kevinschildhorn.common.design.theme.font.FontTypes
import me.kevinschildhorn.common.design.theme.font.customFontSet
import me.kevinschildhorn.common.extensions.asComposeTextStyle

@Composable
fun ScreenTitleTextField(
    text: String
) {

    //val font = customFontSet.font(FontTypes.Button)
    //val style: TextStyle = font!!.asComposeTextStyle(fontFamily)
    Text(
        text = text,
        //style = style,
    )
}
