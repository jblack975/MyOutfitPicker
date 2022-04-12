import SwiftUI
import shared

struct ContentView: View {
    let viewmodel = MyOutfitViewModel()
   var body: some View {
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
