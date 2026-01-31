import Foundation
import Combine
import Shared

class ConnectionViewModel: ObservableObject {
    @Published var isConnecting: Bool = false
    @Published var connectionError: String? = nil
    @Published var connectionSettings: ConnectionSettings = ConnectionSettings()
    
    private let repository = GameInfoRepository()
    private let preferences = iOSConnectionPreferences()
    private var cancellables = Set<AnyCancellable>()
    
    init() {
        loadConnectionSettings()
    }
    
    private func loadConnectionSettings() {
        preferences.connectionSettings
            .sink {
                self.connectionSettings = $0
                if $0.isConnected {
                    self.repository.updateBaseUrl(baseUrl: $0.getBaseUrl())
                }
            }
            .store(in: &cancellables)
    }
    
    func connect(ipAddress: String, port: String) {
        isConnecting = true
        connectionError = nil
        
        let baseUrl = "http://\(ipAddress):\(port)"
        repository.updateBaseUrl(baseUrl: baseUrl)
        
        Task {
            do {
                let result = try await repository.getGameInfo()
                switch result {
                case .success(let gameInfo):
                    await preferences.updateConnectionSettings(
                        isConnected: true,
                        ipAddress: ipAddress,
                        port: port
                    )
                    connectionError = nil
                case .failure(let error):
                    connectionError = error.localizedDescription
                }
                
                isConnecting = false
            } catch {
                connectionError = error.localizedDescription
                isConnecting = false
            }
        }
    }
    
    func disconnect() {
        Task {
            await preferences.clearConnectionSettings()
            connectionError = nil
        }
    }
    
    func clearError() {
        connectionError = nil
    }
}
