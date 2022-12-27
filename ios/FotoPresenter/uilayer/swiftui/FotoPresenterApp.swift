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

        SharedFotoSDK.UIDesignKt.designSystem.fontFamily =
            AtomikAtomikFontFamily(uiFonts: [
                .bold: UIFont(name: "Quicksand-Bold", size: 12)!,
                .light: UIFont(name: "Quicksand-Light", size: 12)!,
                .medium: UIFont(name: "Quicksand-Medium", size: 12)!,
                .normal: UIFont(name: "Quicksand-Regular", size: 12)!,
                .semibold: UIFont(name: "Quicksand-SemiBold", size: 12)!
            ])

        print("Hello")
    }

    var body: some Scene {
        WindowGroup {
            LoginScreen()
        }
    }
}
