//
//  EmailVerificationViewModel.swift
//  FotoPresenter
//
//  Created by Kevin Schildhorn on 9/16/22.
//

import Foundation
import SharedFotoSDK
import SwiftUI

class EmailVerificiationViewModel : ObservableObject {
    var sharedViewModel: EmailValidationCallbackViewModel
    private var cancellers: [Canceller] = []


    @Published var email: String = "" {
        didSet {
            sharedViewModel.updateUsername(input: email)
        }
    }
    //@Published private(set) var emailTextFieldState: TextFieldState = TextFieldState.companion.create(hint: "Email", defaultColor: ColorOption.normal)

    init() {
        self.sharedViewModel = EmailValidationCallbackViewModel()
        observe(sharedViewModel.email.subscribe(onEach: { [weak self] in self?.email = $0 as String }))
        //observe(sharedViewModel.emailTextFieldState.subscribe(onEach: { [weak self] in self?.emailTextFieldState = $0 }))

    }

    private func observe(_ cancellable: Canceller) {
        cancellers.append(cancellable)
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
