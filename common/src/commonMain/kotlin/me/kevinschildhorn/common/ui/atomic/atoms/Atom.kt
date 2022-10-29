package me.kevinschildhorn.common.ui.atomic.atoms

import me.kevinschildhorn.common.ui.atomic.designproperty.DesignProperty

enum class AtomType {
    GROUP,
    TEXT,
    BUTTON
}

open class Atom(
    val type:AtomType,
    open val design: List<DesignProperty>,
)