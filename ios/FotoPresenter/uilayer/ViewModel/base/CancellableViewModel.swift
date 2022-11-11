//
//  CancellableViewModel.swift
//  FotoPresenter
//
//  Created by Kevin Schildhorn on 11/11/22.
//

import Foundation
import Combine
import SharedFotoSDK

class CancellableViewModel<T:CallbackViewModel> {

    var cancellers: [Cancellable] = []
    var callbackViewModel:T

    init(_ callbackViewModel:T){
        self.callbackViewModel = callbackViewModel
    }

    deinit {
        cancellers.forEach { $0.cancel() }
        callbackViewModel.clear()
    }
}
