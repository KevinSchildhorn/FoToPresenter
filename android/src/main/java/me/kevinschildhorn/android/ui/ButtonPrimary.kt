package me.kevinschildhorn.android.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.kevinschildhorn.android.ui.extensions.AsComposable
import me.kevinschildhorn.common.ui.atomic.molecules.TextButtonMolecule
import me.kevinschildhorn.common.ui.style.DesignColors
import me.kevinschildhorn.common.ui.style.color.SharedColorStyleGuide

@Composable
fun TestButtonPrimary(
    text: String = "",
    onClick: () -> Unit,
    colorGuide: SharedColorStyleGuide = DesignColors.light
) {
    val molecule = TextButtonMolecule(colorGuide)
    //molecule.AsComposable(text = text)

    Button(
        onClick = {},
        modifier = Modifier.width(IntrinsicSize.Max).height(IntrinsicSize.Max).clip(RoundedCornerShape(5.dp))
    ) {
        Box(
            modifier = Modifier
                .background(Color.Red)
                .fillMaxWidth(1.0f)
                .fillMaxHeight(1.0f)
        ) {}
        Text(text = "Hello", modifier = Modifier.padding(10.dp))
    }
}

@Preview
@Composable
fun TestButtonPrimaryPreview() {
    TestButtonPrimary(
        text = "Hello",
        onClick = { }
    )
}