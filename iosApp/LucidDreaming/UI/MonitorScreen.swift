import SwiftUI
import UIKit

struct MonitorScreen: View {
    @ObservedObject var viewModel: MonitorViewModel
    
    @State private var isAutoRefreshing = true
    
    var body: some View {
        ScrollView {
            VStack(spacing: 20) {
                HStack {
                    Text("Game Monitor")
                        .font(.title)
                        .fontWeight(.bold)
                    Spacer()
                    Button(action: {
                        viewModel.loadGameInfo()
                    }) {
                        Text("Refresh")
                            .padding()
                            .background(Color.blue)
                            .foregroundColor(.white)
                            .cornerRadius(8)
                    }
                }
                
                if viewModel.isLoading {
                    ProgressView()
                        .padding()
                } else if let error = viewModel.error {
                    Text(error)
                        .foregroundColor(.red)
                        .padding()
                } else if let gameInfo = viewModel.gameInfo {
                    VStack(spacing: 10) {
                        Text(gameInfo.serverName)
                            .font(.headline)
                        
                        HStack {
                            Text("Version: \(gameInfo.version)")
                            Spacer()
                            Text("Players: \(gameInfo.playerCount)")
                        }
                        
                        HStack {
                            Text("Map: \(gameInfo.mapName)")
                            Spacer()
                            Text("Mode: \(gameInfo.gameMode)")
                        }
                        
                        Text("Uptime: \(formatUptime(gameInfo.uptime))")
                    }
                    .padding()
                    .background(Color.gray.opacity(0.1))
                    .cornerRadius(10)
                }
                
                VStack(spacing: 10) {
                    Text("Game Screenshot")
                        .font(.headline)
                    
                    if let screenshot = viewModel.screenshot {
                        Image(uiImage: UIImage(data: screenshot) ?? UIImage())
                            .resizable()
                            .aspectRatio(contentMode: .fit)
                            .frame(maxHeight: 300)
                            .cornerRadius(10)
                    } else {
                        Text("No screenshot available")
                            .padding()
                            .background(Color.gray.opacity(0.1))
                            .cornerRadius(10)
                            .frame(maxHeight: 300)
                    }
                    
                    Button(action: {
                        viewModel.loadScreenshot()
                    }) {
                        Text("Capture Screenshot")
                            .padding()
                            .background(Color.blue)
                            .foregroundColor(.white)
                            .cornerRadius(8)
                    }
                }
                
                VStack(spacing: 10) {
                    Text("Auto Refresh Settings")
                        .font(.headline)
                    
                    Toggle(isOn: $isAutoRefreshing) {
                        Text("Auto Refresh")
                    }
                    .onChange(of: isAutoRefreshing) {newValue in
                        if newValue {
                            viewModel.startAutoRefresh()
                        } else {
                            viewModel.stopAutoRefresh()
                        }
                    }
                }
                .padding()
                .background(Color.gray.opacity(0.1))
                .cornerRadius(10)
            }
            .padding()
        }
        .onAppear {
            viewModel.loadGameInfo()
            if isAutoRefreshing {
                viewModel.startAutoRefresh()
            }
        }
        .onDisappear {
            viewModel.stopAutoRefresh()
        }
    }
    
    private func formatUptime(_ seconds: Int64) -> String {
        let hours = seconds / 3600
        let minutes = (seconds % 3600) / 60
        let secs = seconds % 60
        return "\(hours)h \(minutes)m \(secs)s"
    }
}
