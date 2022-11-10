//
//  EmailVerificationViewModel.swift
//  FotoPresenter
//
//  Created by Kevin Schildhorn on 9/16/22.
//∫

import Foundation
import SharedFotoSDK
import SwiftUI
import Combine

class LoginViewModel : ObservableObject {
    var sharedViewModel: LoginCallbackViewModel
    private var cancellers: [Cancellable] = []


    /*
    @Published var email: String = "" {
        didSet {
            sharedViewModel.updateEmail(email: email)
        }
    }
    @Published var password: String = ""{
        didSet {
            sharedViewModel.updatePassword(password: password)
        }
    }
    @Published private(set) var emailTextFieldState: TextFieldState = TextFieldState.companion.create(hint: "Email")
    @Published private(set) var passwordTextFieldState: TextFieldState = TextFieldState.companion.create(hint: "Email")
    @Published private(set) var createProfileButtonState: ButtonState = ButtonState.companion.create(text: "Create Profile")


    init() {
        self.sharedViewModel = EmailValidationCallbackViewModel()

        cancellers += [
            doPublish(sharedViewModel.emailTextState, onEach: { [weak self] in
                self?.emailTextFieldState = $0
            }),
            doPublish(sharedViewModel.passwordTextState, onEach: { [weak self] in
                self?.passwordTextFieldState = $0
            }),
            doPublish(sharedViewModel.createProfileButtonState, onEach: { [weak self] in
                self?.createProfileButtonState = $0
            })
        ]
    }

    deinit {
        cancellers.forEach { $0.cancel() }
        sharedViewModel.clear()
    }

    func updateEmail(input: String) {
        sharedViewModel.updateEmail(email: input)
    }

    func updatePassword(input: String) {
        sharedViewModel.updatePassword(password: input)
    }

    func emailFocusChanged(isFocused: Bool) {
        sharedViewModel.setEmailFocus(focus: isFocused)
    }
    func passwordFocusChanged(isFocused: Bool) {
        sharedViewModel.setPasswordFocus(focus: isFocused)
    }*/
}
