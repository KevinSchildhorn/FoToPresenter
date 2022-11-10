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

    @Published private(set) var uiState: LoginUiState =
        LoginUiState(
            address: "",
            username: "",
            password: "",
            isLoading: false,
            errorMessage: nil
        )

    //MARK: - LIFECYCLE

    init() {
        self.sharedViewModel = LoginCallbackViewModel()
        cancellers.append(
            doPublish(sharedViewModel.uiState, onEach: { [weak self] in
                self?.uiState = $0
            })
        )
    }

    deinit {
        cancellers.forEach { $0.cancel() }
        sharedViewModel.clear()
    }

    //MARK: - SHARED VIEW MODEL FUNCTIONS

    func updateAddress(address: String) { sharedViewModel.updateAddress(address: address) }
    func updateUsername(username: String) { sharedViewModel.updateUsername(username: username) }
    func updatePassword(password: String) { sharedViewModel.updatePassword(password: password) }

    func login() { sharedViewModel.login() }
    func fetchLoginCredentials() { sharedViewModel.fetchLoginCredentials() }

}
