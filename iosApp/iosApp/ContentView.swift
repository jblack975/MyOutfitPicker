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
    let viewmodel = MyOutfitViewModel()
    var body: some View {
        Text(viewmodel.appIntro)
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
