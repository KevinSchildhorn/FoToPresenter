// ktlint-disable filename
package com.kevinschildhorn.atomik.atomic.atoms

import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import com.kevinschildhorn.atomik.atomic.atoms.interfaces.AtomikConstraintX
import com.kevinschildhorn.atomik.atomic.atoms.interfaces.AtomikConstraintY
import com.kevinschildhorn.atomik.atomic.atoms.interfaces.ConstrainedAtom

/**
 * The Vertical compose Alignment, based on the [ConstrainedAtom]
 */
public val ConstrainedAtom.alignmentVertical: Alignment.Vertical
    get() = when (this.constraintY) {
        AtomikConstraintY.ALIGN_TOP -> Alignment.Top
        AtomikConstraintY.ALIGN_BOTTOM -> Alignment.Bottom
        AtomikConstraintY.CENTER -> Alignment.CenterVertically
        AtomikConstraintY.SCALE -> Alignment.CenterVertically
    }

/**
 * The Horizontal compose Alignment, based on the [ConstrainedAtom]
 */
public val ConstrainedAtom.alignmentHorizontal: Alignment.Horizontal
    get() = when (this.constraintX) {
        AtomikConstraintX.ALIGN_LEFT -> Alignment.Start
        AtomikConstraintX.ALIGN_RIGHT -> Alignment.End
        AtomikConstraintX.CENTER -> Alignment.CenterHorizontally
        AtomikConstraintX.SCALE -> Alignment.CenterHorizontally
    }

/**
 * The compose Alignment, based on the [ConstrainedAtom]. Combines both vertical and horizontal
 */
public val ConstrainedAtom.alignment: Alignment
    get() = BiasAlignment(horizontalBias, verticalBias)

private val ConstrainedAtom.verticalBias: Float
    get() = when (this.constraintY) {
        AtomikConstraintY.ALIGN_TOP -> -1f
        AtomikConstraintY.ALIGN_BOTTOM -> 1f
        AtomikConstraintY.CENTER -> 0f
        AtomikConstraintY.SCALE -> 0f
    }

private val ConstrainedAtom.horizontalBias: Float
    get() = when (this.constraintX) {
        AtomikConstraintX.ALIGN_LEFT -> -1f
        AtomikConstraintX.ALIGN_RIGHT -> 1f
        AtomikConstraintX.CENTER -> 0f
        AtomikConstraintX.SCALE -> 0f
    }