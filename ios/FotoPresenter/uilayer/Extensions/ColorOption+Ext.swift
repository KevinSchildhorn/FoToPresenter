//
//  ColorOption+Ext.swift
//  FotoPresenter
//
//  Created by Kevin Schildhorn on 9/21/22.
//

import Foundation
import SharedFotoSDK
import SwiftUI

extension ColorOption {

    var color: SwiftUI.Color {
        switch self {
            case .normal: return Color.blue
            case .error: return Color.red
            case .hint: return Color.gray
            case .success: return Color.green
            default:
                return Color.black
        }
    }

}
