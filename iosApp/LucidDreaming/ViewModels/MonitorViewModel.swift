import Foundation
import Combine
import Shared

class MonitorViewModel: ObservableObject {
    @Published var gameInfo: GameInfo? = nil
    @Published var isLoading: Bool = false
    @Published var error: String? = nil
    @Published var screenshot: Data? = nil
    
    private let repository = GameInfoRepository()
    private let settingsPreferences = iOSSsettingsPreferences()
    private var cancellables = Set<AnyCancellable>()
    private var autoRefreshEnabled = false
    private var refreshTask: Task<Void, Never>? = nil
    
    func loadGameInfo() {
        isLoading = true
        error = nil
        
        Task {
            do {
                let result = try await repository.getGameInfo()
                switch result {
                case .success(let gameInfo):
                    self.gameInfo = gameInfo
                case .failure(let error):
                    self.error = error.localizedDescription
                }
                
                isLoading = false
            } catch {
                self.error = error.localizedDescription
                isLoading = false
            }
        }
    }
    
    func loadScreenshot() {
        isLoading = true
        
        Task {
            do {
                let result = try await repository.getScreenshot()
                switch result {
                case .success(let screenshotData):
                    screenshot = screenshotData
                case .failure(let error):
                    self.error = error.localizedDescription
                }
                
                isLoading = false
            } catch {
                self.error = error.localizedDescription
                isLoading = false
            }
        }
    }
    
    func clearError() {
        error = nil
    }
    
    func startAutoRefresh() {
        guard !autoRefreshEnabled else { return }
        autoRefreshEnabled = true
        
        refreshTask = Task {
            while autoRefreshEnabled {
                let settings = await settingsPreferences.settings.first(where: { _ in true })
                let interval = settings?.refreshInterval ?? 10
                
                try? await Task.sleep(nanoseconds: UInt64(interval) * 1_000_000_000)
                
                if autoRefreshEnabled {
                    loadGameInfo()
                }
            }
        }
    }
    
    func stopAutoRefresh() {
        autoRefreshEnabled = false
        refreshTask?.cancel()
        refreshTask = nil
    }
}
