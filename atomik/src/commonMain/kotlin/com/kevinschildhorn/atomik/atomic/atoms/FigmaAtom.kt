package com.kevinschildhorn.atomik.atomic.atoms

import com.kevinschildhorn.atomik.atomic.atoms.interfaces.AtomType
import com.kevinschildhorn.atomik.atomic.atoms.interfaces.AtomikConstraintX
import com.kevinschildhorn.atomik.atomic.atoms.interfaces.AtomikConstraintY
import com.kevinschildhorn.atomik.atomic.atoms.interfaces.ColorAtom
import com.kevinschildhorn.atomik.atomic.atoms.interfaces.ConstrainedAtom
import com.kevinschildhorn.atomik.atomic.atoms.interfaces.FixedSizeAtom
import com.kevinschildhorn.atomik.atomic.atoms.interfaces.PaddingAtom
import com.kevinschildhorn.atomik.atomic.atoms.interfaces.TextAtom
import com.kevinschildhorn.atomik.color.base.AtomikColor
import com.kevinschildhorn.atomik.typography.base.AtomikFontFamily
import com.kevinschildhorn.atomik.typography.base.AtomikTypography
import com.kevinschildhorn.atomik.typography.base.AtomikTypographyType

/**
 * Represents an Atom with all of the properties found in a Figma atom
 *
 * This atom contains the components that are configurable in a Figma file. These are the base components,
 * as in Figma you can create shape and text components. There are child classes for those components.
 *
 * @property type the Type of atom being displayed, such as button or text
 * @property width the width of the UI (in dp / pt)
 * @property height the height of the UI (in dp / pt)
 * @property constraintX the constraint in the X Axis (horizontal)
 * @property constraintY the constraint in the Y Axis (vertical)
 */
public open class FigmaBaseAtom(
    override val type: AtomType,
    override val width: Int?,
    override val height: Int?,
    override val constraintX: AtomikConstraintX,
    override val constraintY: AtomikConstraintY
) : Atom(), FixedSizeAtom, ConstrainedAtom

/**
 * Represents an Atom with all of the properties found in a Figma Shape atom
 *
 * This atom contains the components that are configurable in a Figma file, for a component that has a shape.
 *
 * @property type the Type of atom being displayed, such as button or text
 * @property subComponents the components related to this atom, as mentioned in description
 * @property width the width of the UI (in dp / pt)
 * @property height the height of the UI (in dp / pt)
 * @property constraintX the constraint in the X Axis (horizontal)
 * @property constraintY the constraint in the Y Axis (vertical)
 * @property color the color of the UI.
 * @property padding the horizontal padding of the atom (in dp/pt)
 * @property paddingHorizontal the horizontal padding of the atom (in dp/pt)
 * @property paddingVertical the vertical padding of the atom (in dp/pt)
 * @property paddingLeft the padding on the left of the atom (in dp/pt)
 * @property paddingRight the padding on the right of the atom (in dp/pt)
 * @property paddingTop the padding on the top of the atom (in dp/pt)
 * @property paddingBottom the padding on the bottom of the atom (in dp/pt)
 */
public class FigmaShapeAtom(
    override val type: AtomType = AtomType.VIEW,
    override val subComponents: List<Atom> = emptyList(),
    override val width: Int? = null,
    override val height: Int? = null,
    override val constraintX: AtomikConstraintX,
    override val constraintY: AtomikConstraintY,
    // [ColorAtom]
    override val color: AtomikColor,
    // [PaddingAtom]
    override val padding: Int? = null,
    override val paddingHorizontal: Int? = padding,
    override val paddingVertical: Int? = padding,
    override val paddingLeft: Int? = paddingHorizontal,
    override val paddingRight: Int? = paddingHorizontal,
    override val paddingTop: Int? = paddingVertical,
    override val paddingBottom: Int? = paddingVertical
) : FigmaBaseAtom(type, width, height, constraintX, constraintY), ColorAtom, PaddingAtom

/**
 * Represents an Atom with all of the properties found in a Figma Text atom
 *
 * This atom contains the components that are configurable in a Figma file, for a component that has text.
 *
 * @property type the Type of atom being displayed, such as button or text
 * @property subComponents the components related to this atom, as mentioned in description
 * @property width the width of the UI (in dp / pt)
 * @property height the height of the UI (in dp / pt)
 * @property constraintX the constraint in the X Axis (horizontal)
 * @property constraintY the constraint in the Y Axis (vertical)
 * @property textColor the color of the text
 * @property typography the typography associated with the text
 * @property fontFamily the fontFamily associated with the text
 * @property padding the horizontal padding of the atom (in dp/pt)
 * @property paddingHorizontal the horizontal padding of the atom (in dp/pt)
 * @property paddingVertical the vertical padding of the atom (in dp/pt)
 * @property paddingLeft the padding on the left of the atom (in dp/pt)
 * @property paddingRight the padding on the right of the atom (in dp/pt)
 * @property paddingTop the padding on the top of the atom (in dp/pt)
 * @property paddingBottom the padding on the bottom of the atom (in dp/pt)
 */
public class FigmaTextAtom(
    override val type: AtomType = AtomType.TEXT,
    override val subComponents: List<Atom> = emptyList(),
    override val width: Int? = null,
    override val height: Int? = null,
    override val constraintX: AtomikConstraintX,
    override val constraintY: AtomikConstraintY,
    // TextAtom
    override val textColor: AtomikColor,
    override val typography: AtomikTypography,
    override val fontFamily: AtomikFontFamily? = null,
    // PaddingAtom
    override val padding: Int? = null,
    override val paddingHorizontal: Int? = padding,
    override val paddingVertical: Int? = padding,
    override val paddingLeft: Int? = paddingHorizontal,
    override val paddingRight: Int? = paddingHorizontal,
    override val paddingTop: Int? = paddingVertical,
    override val paddingBottom: Int? = paddingVertical
) : FigmaBaseAtom(type, width, height, constraintX, constraintY), TextAtom, PaddingAtom
