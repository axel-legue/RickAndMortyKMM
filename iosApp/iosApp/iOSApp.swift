import SwiftUI
import shared

@main
struct iOSApp: App {

    init() {
        #if DEBUG
        Helper().doInitKoin(enableNetworkLogs: true)
        #else
        Helper().doInitKoin(enableNetworkLogs: false)
        #endif
    }

	var body: some Scene {
		WindowGroup {
            ContentView(viewModel: ContentView.ViewModel())
		}
	}
}
