package com.kevinschildhorn.fotopresenter.ui.atoms

import androidx.compose.ui.graphics.vector.ImageVector
import com.kevinschildhorn.atomik.atomic.atoms.Atom
import com.kevinschildhorn.atomik.atomic.atoms.ImageAtom
import com.kevinschildhorn.atomik.atomic.atoms.interfaces.SimpleTextAtom
import com.kevinschildhorn.atomik.atomic.molecules.BaseMolecule
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.fill.Folder

object DirectoryAtoms {


    val emptyDirectory = EmptyPhotoMolecule()

    class EmptyPhotoMolecule : BaseMolecule() {
        val imageAtom = ImageAtom(
            color = FotoColors.primary,
            image = EvaIcons.Fill.Folder,
        )
        val textAtom = SimpleTextAtom(
            textColor = FotoColors.backgroundText,
            typography = FotoTypography.button,
            fontFamily = null,
        )
        override val atoms: List<Atom>
            get() = listOf(imageAtom, textAtom)
    }
}