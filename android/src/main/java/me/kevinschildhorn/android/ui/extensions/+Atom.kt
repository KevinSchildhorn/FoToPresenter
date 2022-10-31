package me.kevinschildhorn.android.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import me.kevinschildhorn.android.ui.extensions.asModifier
import me.kevinschildhorn.common.ui.atomic.atoms.Atom
import me.kevinschildhorn.common.ui.atomic.atoms.AtomType
import me.kevinschildhorn.common.ui.atomic.designproperty.*
/*
@Composable
fun Atom.AsComposable(text: String) {
    when (type) {
        AtomType.BUTTON -> Button(title = text, modifier = design.modifiers)
        AtomType.TEXT -> Text(text = text, modifier = design.modifiers)
        AtomType.GROUP -> Box(modifier = design.modifiers.fillMaxSize(1.00f)) {}
    }
}*/

val List<DesignProperty>.modifiers: Modifier
    get() {
        var modifier: Modifier = Modifier
        forEach {
            when (it) {
                is ColorDesignProperty -> modifier = modifier.background(it.color.androidColor)
                is CornerDesignProperty ->
                    modifier = modifier.clip(RoundedCornerShape(it.radius.dp))
                is MarginDesignProperty -> modifier = modifier.padding(
                    horizontal = it.marginX.dp,
                    vertical = it.marginY.dp
                )
                is SizeDesignProperty -> modifier = it.asModifier
                is IntrinsicSizeDesignProperty -> modifier = modifier
                    .width(IntrinsicSize.Max)
                    .height(IntrinsicSize.Max)
            }
        }
        return modifier
    }