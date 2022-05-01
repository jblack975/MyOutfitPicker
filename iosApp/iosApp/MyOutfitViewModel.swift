//
//  MyOutfitViewModel.swift
//  iosApp
//
//  Created by admin on 4/12/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared
import KMPNativeCoroutinesAsync

@MainActor
class MyOutfitViewModel : ObservableObject {
    @Published var currentForecast = [CurrentForecast]()
    @Published var monthlyForecast = [MonthlyForecast]()
    private let viewModel = MyOutfitPickerViewModel()
    private let store = OutfitPickerStore()
    
    var appIntro : String {
        get {
            viewModel.appIntro
        }
    }
}

class LoginViewModel: ObservableObject {
    private var _wasAuthenticated = false
    var wasAuthenticated:Bool {
        set(value) {
            _wasAuthenticated = value
        }
        get {
            return _wasAuthenticated
        }
    }
}
