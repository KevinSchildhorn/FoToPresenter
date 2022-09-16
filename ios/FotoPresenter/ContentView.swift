//
//  ContentView.swift
//  FotoPresenter
//
//  Created by Kevin Schildhorn on 9/7/22.
//

import SwiftUI
import SharedFotoSDK

struct ContentView: View {
    @StateObject private var viewModel = EmailVerificiationViewModel()

    var body: some View {

        HStack {
            TextField(
                "User name (email address)",
                text: $viewModel.email
            )
            .padding(CGFloat(50.0))
            //.focused($viewModel.isFocused)
            .onSubmit {
                //validate(name: username)
            }
            .textInputAutocapitalization(.never)
            .disableAutocorrection(true)
            .border(.secondary)

            viewModel.emailTextFieldState.trailingIconState.image

        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
