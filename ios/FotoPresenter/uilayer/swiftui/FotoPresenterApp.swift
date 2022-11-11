//
//  FotoPresenterApp.swift
//  FotoPresenter
//
//  Created by Kevin Schildhorn on 9/7/22.
//

import SwiftUI
import SharedFotoSDK

@main
struct FotoPresenterApp: App {

    init() {
        KoinIOSKt.startKoin()
    }

    var body: some Scene {
        WindowGroup {
            SignUpScreen()
        }
    }
}
