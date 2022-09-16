//
//  ContentView.swift
//  FotoPresenter
//
//  Created by Kevin Schildhorn on 9/7/22.
//

import SwiftUI
import SharedFotoSDK

struct ContentView: View {
    //let viewModel = EmailVerificiationViewModel()

    @StateObject private var viewModel = EmailVerificiationViewModel()


    @State private var username: String = ""
    @FocusState private var emailFieldIsFocused: Bool


    var body: some View {

        TextField(
            "User name (email address)",
            text: $viewModel.email
        )
        .padding(CGFloat(50.0))
        .focused($emailFieldIsFocused)
        .onSubmit {
            //validate(name: username)
        }
        .textInputAutocapitalization(.never)
        .disableAutocorrection(true)
        .border(.secondary)
        .onChange(of: username, perform: {newValue in
            viewModel.updateUsername(input: newValue)
        })
        .onChange(of: emailFieldIsFocused, perform: {newValue in
            viewModel.focusChanged(isFocused: newValue)
        })

        Text(username)
        .foregroundColor(emailFieldIsFocused ? .red : .blue)
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
