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
    private let viewModel = MyOutfitPickerViewModel()
    private let store = OutfitPickerStore()
    var anonymousId: String {
        get {
            viewModel.anonynmousId
        }
    }
    var clothingTypeList : [String] {
        get {
            viewModel.clothingTypeList
        }
    }
    var appIntro : String {
        get {
            viewModel.appIntro
        }
    }
}
