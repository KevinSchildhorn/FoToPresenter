//
//  ContentView.swift
//  FotoPresenter
//
//  Created by Kevin Schildhorn on 9/7/22.
//

import SwiftUI
import SharedFotoSDK

struct ContentView: View {
    @StateObject private var viewModel = EmailVerificationViewModel()

    @FocusState private var emailFocusState:Bool
    @FocusState private var passwordFocusState:Bool

    let molecule = DesignMolecules.TextField().defaultTextField

    var body: some View {



        VStack {
            // Email
            HStack {
                if viewModel.emailTextFieldState.isError {
                    Text(viewModel.emailTextFieldState.errorText ?? "")
                        .foregroundColor(Color(molecule.errorAtom.textColor.iOSColor))
                        .font(.system(size: molecule.errorAtom.textSize))
                }
                TextField(
                    viewModel.emailTextFieldState.hint,
                    text: $viewModel.email
                )
                .padding(CGFloat(50.0))
                .focused($emailFocusState)
                .textInputAutocapitalization(.never)
                .disableAutocorrection(true)
                .border(viewModel.emailTextFieldState.currentBorderColor.color, width: 5)
                .foregroundColor(Color(molecule.textEntryAtom.textColor.iOSColor))
                .font(.system(size: molecule.textEntryAtom.textSize))

                Button(action: {
                    viewModel.email = ""
                }, label: {
                    viewModel.emailTextFieldState.trailingIconState.image
                })

            }
            .onChange(of: emailFocusState, perform: { newFocus in
                viewModel.emailFocusChanged(isFocused: newFocus)
            })

            // Password
            HStack {
                TextField(
                    viewModel.passwordTextFieldState.hint,
                    text: $viewModel.password
                )
                .padding(CGFloat(50.0))
                .focused($passwordFocusState)
                .textInputAutocapitalization(.never)
                .disableAutocorrection(true)
                .border(viewModel.passwordTextFieldState.currentBorderColor.color, width: 5)

                Button(action: {
                    viewModel.password = ""
                }, label: {
                    viewModel.passwordTextFieldState.trailingIconState.image
                })
            }
            .onChange(of: passwordFocusState, perform: { newFocus in
                viewModel.passwordFocusChanged(isFocused: newFocus)
            })
            Button(action: {
                viewModel.sharedViewModel.createProfile()
            }, label: {
                if viewModel.createProfileButtonState.isLoading {
                    ProgressView()
                } else {
                    Text(viewModel.createProfileButtonState.text)
                        .foregroundColor(Color.white)
                }
            })
            .buttonStyle(.borderedProminent)
            .disabled(!viewModel.createProfileButtonState.isEnabled)
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
