# Creating a Design System


First, create a `DesignSystem`, which can be made using a preset or as a new custom System.
Existing Design Systems:
* `PlatformDesignSystem`
* `DefaultDesignSystem`
* `CustomDesignSystem` 

The `DesignSystem` takes in four variables, a `ColorSet`, a `TypographySet`, a map of `atoms`, and a `fontFamily`

```
open class DesignSystem(
    open val colorSet: ColorSet,
    open val typographySet: TypographySet,
    open val atoms: Map<String, Atom>,
    open var fontFamily: AtomikFontFamily? = null
)
```

### ColorSet

`ColorSet` is an interface that can be implemented, however there's also a `DefaultColorSet` for a standard set. There is also a `CustomColorSet` that can be used for more granularity.

`ColorSets` are made up of various `AtomikColor`, which are created with hex codes

```kotlin
    val colorSet = DefaultColorSet(
        primary = AtomikColor(0xFFFFA500),
        secondary = AtomikColor(0xFFFFD383),
        background = AtomikColor(0xFFFFFFFF),
        surface = AtomikColor(0xFFFFFFFF),
        error = AtomikColor(0xFFFF0000),

        primaryText = AtomikColor(0xFF402900),
        secondaryText = AtomikColor(0xFFFFA500),
        backgroundText = AtomikColor(0xFF25231F),
        surfaceText = AtomikColor(0xFFFFA500),
        errorText = AtomikColor(0xFF9E1F1F),
    )
```

### TypographySet

`TypographySet` is an interface that can be implemented, however there's also a `DefaultTypographySet` for a standard set. There is also a `CustomTypographySet` that can be used for granularity.

`TypographySets` are made up of various `AtomikTypography`, which are referenced by a `TypographyType`.

```kotlin
val typographySet = DefaultTypographySet(
    h1 = AtomikTypography(size = 96),
    h2 = AtomikTypography(size = 60),
    h3 = AtomikTypography(size = 48),
    h4 = AtomikTypography(size = 34),
    subtitle = AtomikTypography(size = 16),
    button = AtomikTypography(weight = AtomikTypographyWeight.BOLD, size = 14),
    body = AtomikTypography(size = 16),
    caption = AtomikTypography(weight = AtomikTypographyWeight.BOLD, size = 14),
)
```
### Atoms

`Atoms` are interface representations of Atoms in Atomic Design. They can be grabbed from the `DesignSystem`. For a quick start you can pass in an empty map. 

To see how to create an `Atom` see [Creating an atom](CREATING_AN_ATOM.MD)

### DesignSystem

```kotlin
val designSystem = DefaultDesignSystem(
    colorSet = ColorSets.light,
    typographySet = sharedTypography,
    atoms = emptyMap(),
    fontFamily = null
)
```

### FontFamily

The `AtomikFontFamily` is an expect/actual class for platform specific fonts. When initializing the Design System it's easier to set it to null and pass in the `FontFamily` later, when the app is started

#### Android

```kotlin
val fontFamily = FontFamily(
    Font(R.font.quicksand_regular),
    Font(R.font.quicksand_bold, FontWeight.Bold),
    Font(R.font.quicksand_light, FontWeight.Light),
    Font(R.font.quicksand_medium, FontWeight.Medium),
    Font(R.font.quicksand_semibold, FontWeight.SemiBold),
)
designSystem.fontFamily = AtomikFontFamily(fontFamily)
```

#### iOS

```swift
let fontFamily = [
    .bold: UIFont(name: "Quicksand-Bold", size: 12)!,
    .light: UIFont(name: "Quicksand-Light", size: 12)!,
    .medium: UIFont(name: "Quicksand-Medium", size: 12)!,
    .normal: UIFont(name: "Quicksand-Regular", size: 12)!,
    .semibold: UIFont(name: "Quicksand-SemiBold", size: 12)!
]
UIDesignKt.designSystem.fontFamily = AtomikAtomikFontFamily(uiFonts: fontFamily)
```