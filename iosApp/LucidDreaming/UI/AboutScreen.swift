import SwiftUI

struct AboutScreen: View {
    @Environment(\.dismiss) private var dismiss
    
    var body: some View {
        NavigationView {
            VStack(spacing: 20) {
                Text("About LucidDreaming")
                    .font(.title)
                    .fontWeight(.bold)
                
                Text("LucidDreaming is a powerful tool for managing and monitoring Minecraft servers.")
                    .font(.body)
                    .multilineTextAlignment(.center)
                
                Text("Version: 1.0.0")
                    .font(.body)
                
                Text("Developed with Kotlin and SwiftUI")
                    .font(.body)
                
                Text("© 2026 LucidDreaming Team")
                    .font(.caption)
                    .padding(.top, 40)
            }
            .padding()
            .navigationBarItems(trailing: Button("Close") { dismiss() })
        }
    }
}
