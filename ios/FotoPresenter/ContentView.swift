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

    @State private var username: String = ""
    @FocusState private var emailFieldIsFocused: Bool

    var body: some View {
        TextField(
            "User name (email address)",
            text: $username
        )
        .padding(CGFloat(50.0))
        .focused($emailFieldIsFocused)
        .onSubmit {
            //validate(name: username)
        }
        .textInputAutocapitalization(.never)
        .disableAutocorrection(true)
        .border(.secondary)

        Text(username)
        .foregroundColor(emailFieldIsFocused ? .red : .blue)
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
