package com.kevinschildhorn.atomik

import com.kevinschildhorn.atomik.atomic.atoms.Atom
import com.kevinschildhorn.atomik.color.CustomColorSet
import com.kevinschildhorn.atomik.color.base.ColorSet
import com.kevinschildhorn.atomik.typography.CustomTypographySet
import com.kevinschildhorn.atomik.typography.base.AtomikFontFamily
import com.kevinschildhorn.atomik.typography.base.TypographySet

/**
 * A Design System
 *
 * This is the core of the Atomik Library, representing a Design Systems components.
 * This is based on atomic design which is included in the Design System
 *
 * @property colorSet the collection of colors to be used in this Design System
 * @property typographySet  the collection of typohraphies to be used in this Design System
 * @property components  the atomic components to be used in this Design System
 * @property fontFamily  the font family passed into the Design System from the Platform level
 */
@ExperimentalAtomik
public open class DesignSystem(
    public open val colorSet: ColorSet,
    public open val typographySet: TypographySet,
    public open val components: Map<String, Atom>,
    public open var fontFamily: AtomikFontFamily? = null
)
/*
@OptIn(ExperimentalAtomik::class)
public class PlatformDesignSystem(
    override val colorSet: DefaultColorSet,
    override val typographySet: PlatformTypographySet,
    override val components: Map<String, Atom>,
    override var fontFamily: AtomikFontFamily?,
) : DesignSystem(colorSet, typographySet, components, fontFamily)

@OptIn(ExperimentalAtomik::class)
class DefaultDesignSystem(
    override val colorSet: DefaultColorSet,
    override val typographySet: DefaultTypographySet,
    override val components: Map<String, Atom>,
    override var fontFamily: AtomikFontFamily?,
) : DesignSystem(colorSet, typographySet, components, fontFamily)
*/

/**
 * A Custom implementation of a Design System
 *
 * This inherits the [DesignSystem], and uses Custom implementations of the properties
 * This is based on atomic design which is included in the Design System
 *
 * @property colorSet the custom collection of colors to be used in this Design System
 * @property typographySet the custom collection of typohraphies to be used in this Design System
 * @property components the atomic components to be used in this Design System
 * @property fontFamily the font family passed into the Design System from the Platform level
 */
@OptIn(ExperimentalAtomik::class)
internal class CustomDesignSystem(
    override val colorSet: CustomColorSet,
    override val typographySet: CustomTypographySet,
    override val components: Map<String, Atom>,
    override var fontFamily: AtomikFontFamily?
) : DesignSystem(colorSet, typographySet, components, fontFamily)
