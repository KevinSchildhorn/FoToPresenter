//
//  EmailVerificationViewModel.swift
//  FotoPresenter
//
//  Created by Kevin Schildhorn on 9/16/22.
// ∫

import Foundation
import SharedFotoSDK
import SwiftUI
import Combine

class LoginViewModel: CancellableViewModel<LoginCallbackViewModel>, ObservableObject {

    // MARK: - UI STATE

    var address: String {
        get { uiState.address }
        set { callbackViewModel.updateAddress(address: newValue) }
    }
    var username: String {
        get { uiState.username }
        set { callbackViewModel.updateUsername(username: newValue) }
    }
    var password: String {
        get { uiState.password }
        set { callbackViewModel.updatePassword(password: newValue) }
    }

    @Published private var uiState: LoginUiState = LoginUiState.companion.EMPTY

    // MARK: - LIFECYCLE

    init() {
        super.init(LoginCallbackViewModel())
        cancellers.append(
            doPublish(callbackViewModel.uiState, onEach: { [weak self] in
                self?.uiState = $0
            })
        )
    }

    deinit {
        callbackViewModel.clear()
    }

    // MARK: - FUNCTIONS

    func login() { callbackViewModel.login() }
    func fetchLoginCredentials() { callbackViewModel.fetchLoginCredentials() }
}
