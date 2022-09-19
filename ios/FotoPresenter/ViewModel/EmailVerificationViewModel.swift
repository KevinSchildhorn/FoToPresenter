//
//  EmailVerificationViewModel.swift
//  FotoPresenter
//
//  Created by Kevin Schildhorn on 9/16/22.
//

import Foundation
import SharedFotoSDK
import SwiftUI
import Combine

class EmailVerificiationViewModel : ObservableObject {
    var sharedViewModel: EmailValidationCallbackViewModel
    private var cancellers: [Cancellable] = []


    @Published var email: String = "" {
        didSet {
            sharedViewModel.updateUsername(input: email)
        }
    }
    @Published private(set) var emailTextFieldState: TextFieldState = TextFieldState.companion.create(hint: "Email", defaultColor: ColorOption.normal)

    init() {
        self.sharedViewModel = EmailValidationCallbackViewModel()

        cancellers += [
            doPublish(sharedViewModel.email, onEach: { [weak self] in self?.email = $0 as String }),
            doPublish(sharedViewModel.emailTextFieldState, onEach: { [weak self] in self?.emailTextFieldState = $0 })
        ]
    }

    deinit {
        cancellers.forEach { $0.cancel() }
        sharedViewModel.clear()
    }

    func updateUsername(input: String) {
        sharedViewModel.updateUsername(input: input)
    }

    func focusChanged(isFocused: Bool) {
        sharedViewModel.focusChanged(isFocused: isFocused)
    }
}
