//
//  ScreenTitleTextField.swift
//  FotoPresenter
//
//  Created by Kevin Schildhorn on 12/16/22.
//

import SwiftUI
import SharedFotoSDK

struct PrimaryButton: View {
    // @StateObject private var viewModel = LoginViewModel()

    // @FocusState private var emailFocusState: Bool
    // @FocusState private var passwordFocusState: Bool

    let molecule = DesignAtoms.Buttons().primaryButtonMolecule

    let text: String
    let action: () -> Void

    var enabled: Bool = true

    var buttonColor: UIColor {
        return enabled ?
            molecule.buttonAtom.enabledColor.color.platformColor :
            molecule.buttonAtom.enabledColor.disabledColor.platformColor
    }

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
}

struct PrimaryButton_Previews: PreviewProvider {
    static var previews: some View {
        PrimaryButton(text: "Hello World", action: {
            print("Hello World")
        })
    }
}
