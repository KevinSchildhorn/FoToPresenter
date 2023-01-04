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

    var body: some View {
        HStack {
            VStack(alignment: .leading, content: {
                PrimaryButton(text: "Login", action: {

                })
                Spacer()
            })
            Spacer()
        }
    }
}

struct SignUpScreen_Previews: PreviewProvider {
    static var previews: some View {
        LoginScreen()
    }
}
