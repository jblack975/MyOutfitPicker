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
    let viewmodel = MyOutfitViewModel()
    @State private var isPushEnable = false
    var body: some View {
        VStack {
        Text("Outfit view")
        let list = ClothingTypes.values()
        let num = list.size
        Toggle(isOn: $isPushEnable) {
            Text(list.get(index: 0)!.clothingLabel)
        }
        Toggle(isOn: $isPushEnable) {
            Text(list.get(index: 1)!.clothingLabel)
        }
        Toggle(isOn: $isPushEnable) {
            Text(list.get(index: 2)!.clothingLabel)
        }
        }
    }
}

#if DEBUG
struct AnonymousView_Previews: PreviewProvider {
    static var previews: some View {
        AnonymousView()
    }
}
#endif

#if DEBUG
struct OutfitView_Previews: PreviewProvider {
    static var previews: some View {
        OutfitView()
    }
}
#endif
