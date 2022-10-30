import SwiftUI
import shared

struct ContentView: View {
   @ObservedObject private(set) var viewModel: ViewModel

	var body: some View {
        Text(viewModel.text)
	}
}

// ViewModel is declared as an extension to ContentView, as they are closely connected.

// The Combine frameworks connects the view model (ContentView.ViewModel) with the view (ContentView)

//ContentView.ViewModel is declared as an ObservableObject

// The @Published wrapper is used for the text property

// The @ObservedObject property wrapper is used to subscribe to the viewModel
extension ContentView {
    class ViewModel: ObservableObject {
        @Published var text = "Loading..."
        init() {
            Greeting().greeting()
        }
    }
}
