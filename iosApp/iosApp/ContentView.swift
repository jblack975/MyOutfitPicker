import SwiftUI
import shared
import LocalAuthentication

struct ContentView: View {
    var viewmodel : MyOutfitViewModel
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
                    viewmodel.wasAuthenticated = true
                } else {
                    context.evaluatePolicy(.deviceOwnerAuthentication, localizedReason: reason) { success, authenticationError in
                        // authentication has now completed
                        if success {
                            isUnlocked = true
                            viewmodel.wasAuthenticated = true
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
                            Label("Home", systemImage: "list.dash")
                        }
                    OutfitView(viewmodel: viewmodel)
                        .tabItem {
                            Label("Outfit", systemImage: "square.and.pencil")
                        }
                    AnonymousView(viewmodel: viewmodel)
                        .tabItem {
                            Label("Anonymous", systemImage: "list.dash")
                        }
                    SettingsView(viewmodel: viewmodel)
                        .tabItem {
                            Label("Settings", systemImage: "list.dash")
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
        var viewmodel = MyOutfitViewModel()
        ContentView(viewmodel: viewmodel)
    }
}
#endif

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
    var viewmodel : MyOutfitViewModel
    var body: some View {
        VStack {
            Text("Anonymous view")
            Text(viewmodel.anonymousId)
        }
    }
}

struct OutfitView: View {
    var viewmodel : MyOutfitViewModel
    @State private var isPushEnable : [Bool] = [Bool](repeating: false,count: 20)
    var body: some View {
        VStack {
            Text("Outfit view")
            let list2:[String] = viewmodel.clothingTypeList
            List {
                ForEach(list2, id:\.self) { item in
                    if let index = list2.firstIndex(where: { $0 == item }) {
                        Toggle(isOn: $isPushEnable[index]) {
                            Text(item)
                        }
                    }
                }
            }
        }
    }
}

#if DEBUG
struct AnonymousView_Previews: PreviewProvider {
    static var previews: some View {
        var viewmodel = MyOutfitViewModel()
        AnonymousView(viewmodel: viewmodel)
    }
}
#endif

#if DEBUG
struct OutfitView_Previews: PreviewProvider {
    static var previews: some View {
        var viewmodel = MyOutfitViewModel()
        OutfitView(viewmodel: viewmodel)
    }
}
#endif
