import SwiftUI
import shared
import LocalAuthentication
import KMPNativeCoroutinesAsync

struct ContentView: View {
    var viewmodel : MyOutfitViewModel
    var loginViewModel: LoginViewModel
    var outfitViewModel: MyOutfitPickerViewModel
    @State private var isUnlocked = false
    func authenticate() {
        let context = LAContext()
        var error: NSError?
        
        // check whether biometric authentication is possible
        if context.canEvaluatePolicy(.deviceOwnerAuthenticationWithBiometrics, error: &error) {
            // it's possible, so go ahead and use it
            let reason = "We need to unlock your data."
            
            context.evaluatePolicy(.deviceOwnerAuthenticationWithBiometrics, localizedReason: reason) { success, authenticationError in
                // authentication has now completed
                if success {
                    isUnlocked = true
                    loginViewModel.wasAuthenticated = true
                } else {
                    context.evaluatePolicy(.deviceOwnerAuthentication, localizedReason: reason) { success, authenticationError in
                        // authentication has now completed
                        if success {
                            isUnlocked = true
                            loginViewModel.wasAuthenticated = true
                        } else {
                        }
                    }
                }
            }
        } else {
        }
    }
    var body: some View {
        VStack {
            if isUnlocked {
                TabView {
                    HomeView(viewmodel: viewmodel)
                        .tabItem {
                            Label("Home", systemImage: "homepod.2")
                        }
                    OutfitView(viewmodel: viewmodel)
                        .tabItem {
                            Label("Outfit", systemImage: "mouth")
                        }
                    WeatherView(viewmodel: viewmodel, outfitViewModel: outfitViewModel)
                        .tabItem {
                            Label("Weather", systemImage: "cloud.bolt")
                        }
                    AnonymousView(viewmodel: viewmodel)
                        .tabItem {
                            Label("Anonymous", systemImage: "airplane.circle")
                        }
                    SettingsView(viewmodel: viewmodel)
                        .tabItem {
                            Label("Settings", systemImage: "gear")
                        }
                }
            } else {
                Text("Locked")
            }
        }
        .onAppear(perform: authenticate)
    }
}

#if DEBUG
struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        let viewmodel = MyOutfitViewModel()
        let loginViewModel = LoginViewModel()
        let outfitViewModel = MyOutfitPickerViewModel()
        ContentView(viewmodel: viewmodel, loginViewModel: loginViewModel, outfitViewModel: outfitViewModel)
    }
}
#endif

struct WeatherView: View {
    var viewmodel : MyOutfitViewModel
    var outfitViewModel:MyOutfitPickerViewModel
    @State var city:String = ""
    var body: some View {
//        Form {
//            Section {
                VStack(alignment: .center) {
                    TextField("City", text: $city) {
                        Text("City, State")
                    }
                    
                    Button(action: {
                        let a = outfitViewModel.currentWeatherFlow
                    }) {
                        Text("Current Weather")
                    }
                     
                    
                    Button(action: {
                        let b = outfitViewModel.monthlyWeatherFlow
                    }) {
                        Text("Monthly Weather")
                    }
                     
//                }
//            }
            /*
            Section {
                Text(outfitViewModel.currentWeatherFlow.collect(collector: <#T##Kotlinx_coroutines_coreFlowCollector#>, completionHandler: {_,_ in }))
            }
             */
        }
    }
}
struct HomeView: View {
    let viewmodel : MyOutfitViewModel
    var body: some View {
        Text(viewmodel.appIntro)
    }
}

struct SettingsView: View {
    var viewmodel : MyOutfitViewModel
    var body: some View {
        Text("Settings view")
    }
}

struct AnonymousView: View {
    @ObservedObject var viewmodel : MyOutfitViewModel
    @State var selectedSituation = "gym"
    var outfitViewModel = MyOutfitPickerViewModel()
    @State private var isPushEnable : [Bool] = [Bool](repeating: false,count: 20)
    @State private var isOn = false
    var body: some View {
        VStack {
            NavigationView {
                Form {
                    Section {
                        HStack {
                            Text("Current anonymous ID: \(outfitViewModel.anonynmousId)")
                            Button("Change ID", action: {
                                outfitViewModel.changeAnonymousId()
                            })
                        }
                        Text("Selected situation is : \(selectedSituation)")
                        Button("Send Data", action: {
                            outfitViewModel.situationChoiceFromName(name: selectedSituation)
                            outfitViewModel.submitAnonymousData()
                            
                        })
                    }
                    Section {
                        Picker("Situations", selection: $selectedSituation) {
                            ForEach(outfitViewModel.situationTypeList, id: \.self) { option in
                                Text(option)
                            }
                        }
                    }
                    Section {
                        let list2:[String] = outfitViewModel.clothingTypeList
                        List {
                            ForEach(list2, id:\.self) { item in
                                if let index = list2.firstIndex(where: { $0 == item }) {
                                    Toggle(item, isOn: $isPushEnable[index])
                                        .onChange(of: isPushEnable[index]) { _isOn in
                                            outfitViewModel.addClothingNameToClothingWeather(clothing: list2[index])
                                        }
                                }
                            }
                        }
                    }
                }.navigationBarTitle(Text("Anonymous Submission"))
            }
        }
    }
}

struct OutfitView: View {
    var viewmodel : MyOutfitViewModel
    var body: some View {
        Text("Outfit view")
    }
}

#if DEBUG
struct AnonymousView_Previews: PreviewProvider {
    static var previews: some View {
        let viewmodel = MyOutfitViewModel()
        AnonymousView(viewmodel: viewmodel)
    }
}
#endif

#if DEBUG
struct OutfitView_Previews: PreviewProvider {
    static var previews: some View {
        let viewmodel = MyOutfitViewModel()
        OutfitView(viewmodel: viewmodel)
    }
}
#endif

#if DEBUG
struct WeatherView_Previews: PreviewProvider {
    static var previews: some View {
        let viewmodel = MyOutfitViewModel()
        let outfitViewModel = MyOutfitPickerViewModel()
        WeatherView(viewmodel: viewmodel, outfitViewModel: outfitViewModel)
    }
}
#endif
