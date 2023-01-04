# Using an atom

## Android

```kotlin
@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
) {
    val molecule: TextButtonMolecule = DesignAtoms.Buttons.primaryButtonMolecule
    val buttonAtom = molecule.buttonAtom
    TextButton(
        onClick = onClick,
        colors = buttonAtom.enabledColor.buttonColors(),
        shape = buttonAtom.shape,
        modifier = buttonAtom.heightModifier
    ) {
        val textAtom = molecule.textAtom
        Text(
            text,
            color = textAtom.textColor.platformColor,
            style = textAtom.textStyle(fontFamily),
        )
    }
}
```

Here we're creating a composable Button, which is a `TextButton` with a `Text` inside.

In this example we have a `Molecule`, which contains two `Atoms`, one for the button and one for the text inside
since compose is available in KMP we are able to use shapes, textStyles, and other compose classes easily.

## iOS

```swift
    let text: String
    let action: () -> Void
    
    var font: Font? {
        var font: Font?
        if let uiFont: UIFont = molecule.textAtom.uiFont {
            font = Font(uiFont)
        }
        return font
    }

    var body: some View {

        let buttonAtom = molecule.buttonAtom
        let textAtom: AtomikTextViewAtom = molecule.textAtom

        let height = buttonAtom.height.map { CGFloat(truncating: $0) }

        Button(action: action, label: {
            Text(text)
                .padding()
                .frame(height: height)
                .background(Color(buttonColor))
                .foregroundColor(Color(textAtom.textColor.platformColor))
                .font(font)
                .cornerRadius(CGFloat(buttonAtom.radius))
        })
        .disabled(!enabled)
    }
```

In iOS we have something similar, we have the molecule and the atoms, and are passing them into the `SwiftUI Views`. Unfortunately KMP only supports `UIKit` and not `SwiftUI` so some conversions are needed.