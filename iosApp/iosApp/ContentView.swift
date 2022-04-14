import SwiftUI
import shared
import LocalAuthentication

struct ContentView: View {
    let viewmodel = MyOutfitViewModel()
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
                    HomeView()
                        .tabItem {
                            Label("Home", systemImage: "list.dash")
                        }
                    OutfitView()
                        .tabItem {
                            Label("Outfit", systemImage: "square.and.pencil")
                        }
                    AnonymousView()
                        .tabItem {
                            Label("Anonymous", systemImage: "list.dash")
                        }
                    SettingsView()
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
        ContentView()
    }
}
#endif

struct HomeView: View {
    var body: some View {
        Text("Home view")
    }
}

struct SettingsView: View {
    var body: some View {
        Text("Settings view")
    }
}

struct AnonymousView: View {
    let viewmodel = MyOutfitViewModel()
    var body: some View {
        VStack {
            Text("Anonymous view")
            Text(viewmodel.anonymousId)
        }
    }
}

struct OutfitView: View {
    var body: some View {
        Text("Outfit view")
    }
}
