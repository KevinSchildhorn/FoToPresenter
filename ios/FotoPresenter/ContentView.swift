//
//  ContentView.swift
//  FotoPresenter
//
//  Created by Kevin Schildhorn on 9/7/22.
//

import SwiftUI
import SharedFotoSDK

struct ContentView: View {
    var body: some View {
        let color = SharedColor(hex: 0x123456)
        Button("Hello from \(color.test)", action: {

        })
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
