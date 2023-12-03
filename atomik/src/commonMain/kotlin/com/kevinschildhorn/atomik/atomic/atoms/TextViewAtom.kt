package com.kevinschildhorn.atomik.atomic.atoms

import com.kevinschildhorn.atomik.atomic.atoms.interfaces.AtomType
import com.kevinschildhorn.atomik.atomic.atoms.interfaces.PaddingAtom
import com.kevinschildhorn.atomik.atomic.atoms.interfaces.TextAtom
import com.kevinschildhorn.atomik.color.base.AtomikColor
import com.kevinschildhorn.atomik.typography.base.AtomikFontFamily
import com.kevinschildhorn.atomik.typography.base.AtomikTypography

public class TextViewAtom(
    override val textColor: AtomikColor,
    override val typography: AtomikTypography,
    override val fontFamily: AtomikFontFamily? = null,
    public val backgroundColor: AtomikColor? = null,
    override val paddingHorizontal: Int? = null,
    override val paddingVertical: Int? = null,
    override val padding: Int? = null,
    override val paddingLeft: Int? = null,
    override val paddingRight: Int? = null,
    override val paddingTop: Int? = null,
    override val paddingBottom: Int? = null,
) : Atom(), TextAtom, PaddingAtom {
    override val type: AtomType
        get() = AtomType.TEXT
}
