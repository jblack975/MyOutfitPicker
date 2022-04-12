import SwiftUI
import shared

struct ContentView: View {
    let viewmodel = MyOutfitViewModel()
   var body: some View {
        Text("Hello")
       Text(viewmodel.anonymousId)
   }
}

#if DEBUG
struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
#endif
