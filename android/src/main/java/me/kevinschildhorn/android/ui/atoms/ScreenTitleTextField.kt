package me.kevinschildhorn.android.ui.atoms

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

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
