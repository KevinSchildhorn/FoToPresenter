//
//  TextFieldState+Ext.swift
//  FotoPresenter
//
//  Created by Kevin Schildhorn on 9/16/22.
//

import Foundation
import SharedFotoSDK
import SwiftUI

extension TrailingIconState {

    var color: Color {
        switch self {
            case .none: return Color.clear
            case .error: return Color.red
            case .clearText: return Color.black
            case .checkmark: return Color.green
            default: return Color.clear
        }
    }
    var iconName: String {
        switch self {
            case .none: return ""
            case .error: return "xmark.circle.fill"
            case .clearText: return "xmark"
            case .checkmark: return "checkmark.circle.fill"
            default: return ""
        }
    }
    var image: some View {
        Image(systemName: iconName).foregroundColor(color)
    }
}
