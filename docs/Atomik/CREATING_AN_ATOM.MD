# Creating an Atom

`Atomik` is a KMP implementation of design system, which includes using [Atomic Design](https://bradfrost.com/blog/post/atomic-web-design/). The way `Atomik` is designed, you can create atoms using an abstract `Atom` class, which then implements different `Atom` interfaces.


```kotlin
abstract class Atom {
    abstract val type: AtomType
}
```

To create an atom you should reference your `DesignSystem` for colors, fonts and other variables. If you haven't yet see the document about [Getting Started](GETTING_STARTED.MD).

## Example

```kotlin
class ButtonAtom(
    override val enabledColor: AtomikEnabledColor,
    override val radius: Int,
    override val height: Int?,
) : Atom(), EnablableAtom, RoundedAtom, FixedSizeAtom {
    override val type: AtomType = AtomType.BUTTON

    override val width: Int? = null
}
```

For example, we have the `ButtonAtom`, which inherits `Atom`, and has various interfaces
* `EnablableAtom`, used for ui that can be set to disabled and have the ui update
* `RoundedAtom`, used to set the UI has having rounded corners
* `FixedSizeAtom`, used to set a width and/or height of the ui element

Then you can create a button like so:

```kotlin
val button = ButtonAtom(
    AtomikEnabledColor(designSystem.colorSet.primary, designSystem.colorSet.secondary),
    radius = 5,
    height = 55,
)
```