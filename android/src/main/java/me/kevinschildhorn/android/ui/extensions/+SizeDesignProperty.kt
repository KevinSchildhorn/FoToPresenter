package me.kevinschildhorn.android.ui.extensions

import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.kevinschildhorn.android.util.safeLet
import me.kevinschildhorn.common.ui.atomic.designproperty.SizeDesignProperty

val SizeDesignProperty.asModifier: Modifier
    get() {
        if (constrainProportions) {
            safeLet(width, height) { width, height ->
                return Modifier.aspectRatio(width.toFloat() / height.toFloat())
            }
        } else {
            width?.let { width ->
                return Modifier.width(width.dp)
            }
            height?.let { height ->
                return Modifier.height(height.dp)
            }
        }
        return Modifier.fillMaxSize(1.0f)
    }