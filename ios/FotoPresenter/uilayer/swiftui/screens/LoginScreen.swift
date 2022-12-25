//
//  SignUpScreen.swift
//  FotoPresenter
//
//  Created by Kevin Schildhorn on 9/7/22.
//

import SwiftUI
import SharedFotoSDK

struct LoginScreen: View {
    @StateObject private var viewModel = LoginViewModel()

    @FocusState private var emailFocusState: Bool
    @FocusState private var passwordFocusState: Bool

    // let molecule = DesignMolecules.TextField().defaultTextField

    var body: some View {
        Text("Hello")
    }
}

struct SignUpScreen_Previews: PreviewProvider {
    static var previews: some View {
        LoginScreen()
    }
}
