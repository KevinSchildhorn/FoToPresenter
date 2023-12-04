
@file:Suppress("ktlint:standard:filename")

package com.kevinschildhorn.atomik.atomic.atoms.compose

import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import com.kevinschildhorn.atomik.atomic.atoms.interfaces.ColorAtom
import com.kevinschildhorn.atomik.color.base.composeColor

/**
 * Modifier extension to apply a Color Atom to a composable
 * @param colorAtom the ColorAtom that you are applying to the composable
 * @return returns a modifier
 */
@Suppress("SpellCheckingInspection")
public fun Modifier.atomikColor(colorAtom: ColorAtom): Modifier =
    this.background(colorAtom.color.composeColor)
