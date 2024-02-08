package com.kevinschildhorn.fotopresenter.ui.screens.directory

import com.kevinschildhorn.atomik.atomic.atoms.Atom
import com.kevinschildhorn.atomik.atomic.atoms.ImageAtom
import com.kevinschildhorn.atomik.atomic.atoms.TextViewAtom
import com.kevinschildhorn.atomik.atomic.atoms.interfaces.SimpleTextAtom
import com.kevinschildhorn.atomik.atomic.molecules.BaseMolecule
import com.kevinschildhorn.atomik.atomic.molecules.TextButtonMolecule
import com.kevinschildhorn.atomik.color.base.AtomikColor
import com.kevinschildhorn.atomik.typography.base.AtomikFontFamily
import com.kevinschildhorn.atomik.typography.base.AtomikTypography
import com.kevinschildhorn.fotopresenter.ui.atoms.FotoColors
import com.kevinschildhorn.fotopresenter.ui.atoms.FotoTypography
import com.kevinschildhorn.fotopresenter.ui.atoms.Padding
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.fill.Folder

object DirectoryAtoms {
    val emptyDirectory = EmptyPhotoMolecule()

    val ImageTicker = SimpleTextAtom(
        textColor = FotoColors.backgroundText,
        typography = FotoTypography.caption,
        fontFamily = null,
    )
    val navigationItem =
        TextButtonMolecule(
            color = FotoColors.secondary,
            disabledColor = FotoColors.disabled,
            radius = 15,
            textAtom =
            SimpleTextAtom(
                textColor = FotoColors.secondaryText,
                typography = FotoTypography.button,
                fontFamily = null,
            ),
        )

    class EmptyPhotoMolecule : BaseMolecule() {
        val imageAtom =
            ImageAtom(
                color = FotoColors.primary,
                image = EvaIcons.Fill.Folder,
            )
        val textAtom =
            SimpleTextAtom(
                textColor = FotoColors.backgroundText,
                typography = FotoTypography.button,
                fontFamily = null,
            )
        override val atoms: List<Atom>
            get() = listOf(imageAtom, textAtom)
    }
}
