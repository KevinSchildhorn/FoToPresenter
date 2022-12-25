//
//  ScreenTitleTextField.swift
//  FotoPresenter
//
//  Created by Kevin Schildhorn on 12/16/22.
//

import SwiftUI
import SharedFotoSDK

struct ScreenTitleTextField: View {
    @StateObject private var viewModel = LoginViewModel()

    @FocusState private var emailFocusState: Bool
    @FocusState private var passwordFocusState: Bool

    let text: String
    var body: some View {
        let atom = DesignAtoms.TextView().labelAtom

        Text(text)
            .foregroundColor(Color(atom.textColor.platformColor))

    }
}

struct ScreenTitleTextField_Previews: PreviewProvider {
    static var previews: some View {
        ScreenTitleTextField(text: "Hello")
    }
}
