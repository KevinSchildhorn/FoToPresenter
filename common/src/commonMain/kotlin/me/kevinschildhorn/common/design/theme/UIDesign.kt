package me.kevinschildhorn.common.design.theme

import me.kevinschildhorn.atomik.CustomDesignSystem
import me.kevinschildhorn.atomik.DefaultDesignSystem
import me.kevinschildhorn.atomik.atomic.molecules.TextButtonMolecule
import me.kevinschildhorn.atomik.typography.base.TypographyType

val designSystem = DefaultDesignSystem(
    colorSet = ColorSets.light,
    typographySet = TypographySets.sharedTypography,
    atoms = emptyMap(),
    molecules = mutableMapOf(),
    fontFamily = null
)

val microsoftDesignSystem = CustomDesignSystem(
    colorSet = ColorSets.microsoft,
    typographySet = TypographySets.microsoftTypography,
    atoms = emptyMap(),
    molecules = mutableMapOf(),
    fontFamily = null
).apply {
    molecules["PrimaryButton"] = TextButtonMolecule(
        color = colorSet.getColor(ColorSets.MicrosoftColor.PRIMARY.value), // TODO: BROKEN
        disabledColor = colorSet.getColor(ColorSets.MicrosoftColor.GREY20.value),
        radius = 2,
        height = null,
        textColor = colorSet.getColor(ColorSets.MicrosoftColor.WHITE.value),
        typography = typographySet.getTypography(TypographyType.Button),
        fontFamily = this.fontFamily,
    )
}
