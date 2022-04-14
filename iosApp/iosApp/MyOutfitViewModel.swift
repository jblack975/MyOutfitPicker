//
//  MyOutfitViewModel.swift
//  iosApp
//
//  Created by admin on 4/12/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared

class MyOutfitViewModel : ObservableObject {
    private var _wasAuthenticated = false
    private let viewModel = MyOutfitPickerViewModel()
    private let store = OutfitPickerStore()
    var anonymousId: String {
        get {
            viewModel.anonynmousId
        }
    }
    var wasAuthenticated:Bool {
        set(value) {
            _wasAuthenticated = value
        }
        get {
            return _wasAuthenticated
        }
    }
}
