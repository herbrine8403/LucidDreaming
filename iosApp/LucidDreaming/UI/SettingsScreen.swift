import SwiftUI

struct SettingsScreen: View {
    @ObservedObject var viewModel: SettingsViewModel
    let onDisconnected: () -> Void
    
    var body: some View {
        ScrollView {
            VStack(spacing: 20) {
                Text("Settings")
                    .font(.title)
                    .fontWeight(.bold)
                
                VStack(spacing: 15) {
                    Text("Refresh Settings")
                        .font(.headline)
                    
                    Toggle(isOn: Binding(
                        get: { viewModel.settings.autoRefresh },
                        set: { viewModel.updateAutoRefresh(enabled: $0) }
                    )) {
                        Text("Auto Refresh")
                    }
                    
                    Text("Refresh Interval: \(viewModel.settings.refreshInterval) seconds")
                        .font(.body)
                    
                    Slider(
                        value: Binding(
                            get: { Double(viewModel.settings.refreshInterval) },
                            set: { viewModel.updateRefreshInterval(interval: Int($0)) }
                        ),
                        in: 5...30,
                        step: 5
                    )
                }
                .padding()
                .background(Color.gray.opacity(0.1))
                .cornerRadius(10)
                
                VStack(spacing: 15) {
                    Text("Appearance")
                        .font(.headline)
                    
                    Toggle(isOn: Binding(
                        get: { viewModel.settings.darkMode },
                        set: { viewModel.updateDarkMode(enabled: $0) }
                    )) {
                        Text("Dark Mode")
                    }
                }
                .padding()
                .background(Color.gray.opacity(0.1))
                .cornerRadius(10)
                
                VStack(spacing: 15) {
                    Text("Connection")
                        .font(.headline)
                    
                    Button(action: {
                        viewModel.disconnect()
                        onDisconnected()
                    }) {
                        Text("Disconnect")
                            .padding()
                            .background(Color.red)
                            .foregroundColor(.white)
                            .cornerRadius(8)
                            .frame(maxWidth: .infinity)
                    }
                }
                .padding()
                .background(Color.gray.opacity(0.1))
                .cornerRadius(10)
                
                Button(action: {
                    viewModel.resetSettings()
                }) {
                    Text("Reset Settings")
                        .padding()
                        .background(Color.orange)
                        .foregroundColor(.white)
                        .cornerRadius(8)
                        .frame(maxWidth: .infinity)
                }
            }
            .padding()
        }
    }
}
