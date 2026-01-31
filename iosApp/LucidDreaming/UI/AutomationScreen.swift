import SwiftUI

struct AutomationScreen: View {
    var body: some View {
        VStack(spacing: 20) {
            Text("Automation")
                .font(.title)
                .fontWeight(.bold)
            
            VStack(spacing: 15) {
                Text("Automation Features")
                    .font(.headline)
                
                Text("Automation features are currently under development.")
                    .font(.body)
                
                Text("Coming soon!")
                    .font(.headline)
                    .foregroundColor(.blue)
            }
            .padding()
            .background(Color.gray.opacity(0.1))
            .cornerRadius(10)
        }
        .padding()
        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .center)
    }
}
