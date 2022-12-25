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

    let fontFamily:[UIFont] = [
        UIFont(name: "quicksand_bold", size: 12),
        UIFont(name: "quicksand_light", size: 12),
        UIFont(name: "quicksand_medium", size: 12),
        UIFont(name: "quicksand_regular", size: 12),
        UIFont(name: "quicksand_semibold", size: 12),
    ]

    init() {
        KoinIOSKt.startKoin()
        SharedFotoSDK.UIDesignKt.designSystem.setFontFamily(
            fontFamily: AtomikAtomikFontFamily(fonts: fontFamily)
        )

        print("Hello")
    }

    var body: some Scene {
        WindowGroup {
            LoginScreen()
        }
    }
}
