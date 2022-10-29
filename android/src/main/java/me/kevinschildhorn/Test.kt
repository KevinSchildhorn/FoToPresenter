package me.kevinschildhorn

data class ButtonPrimary(
    val name: String,
    val design: Design
)

data class Design(
    val atoms: List<Atom>,
    val modes: Mode,
)

data class Atom(
    val type: String,
    val id: String,
    val root: String,
)

data class Mode(
    val modes: List<Rule>
)

data class Rule(
    val id: String,
    val padding: String,
    val borderRadius: String,
    val tapHandler: String,
    val sizeConstraints: SizeContraint,
    val children: List<String>,
    val itemSpacing: String,
    val isStructured: String,
    val backgroundColor: BackgroundColor,
    val clipContent: String,
)

data class SizeContraint(
    val widthConstraints: String,
    val heightConstraints: String,
)

data class BackgroundColor(
    val alpha: String,
    val hue: String,
    val saturation: String,
    val value: String
)