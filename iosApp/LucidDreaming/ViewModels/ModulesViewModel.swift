import Foundation
import Combine
import Shared

class ModulesViewModel: ObservableObject {
    @Published var modulesResponse: ModuleResponse? = nil
    @Published var isLoading: Bool = false
    @Published var error: String? = nil
    
    private let repository = GameInfoRepository()
    private let settingsPreferences = iOSSsettingsPreferences()
    private var cancellables = Set<AnyCancellable>()
    private var autoRefreshEnabled = false
    private var refreshTask: Task<Void, Never>? = nil
    
    func loadModules() {
        isLoading = true
        error = nil
        
        Task {
            do {
                let result = try await repository.getModules()
                switch result {
                case .success(let response):
                    modulesResponse = response
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
    
    func toggleModule(moduleName: String) {
        Task {
            do {
                let result = try await repository.toggleModule(moduleName: moduleName, action: "toggle")
                switch result {
                case .success(let module):
                    // Reload modules to update the UI
                    loadModules()
                case .failure(let error):
                    self.error = error.localizedDescription
                }
            } catch {
                self.error = error.localizedDescription
            }
        }
    }
    
    func enableModule(moduleName: String) {
        Task {
            do {
                let result = try await repository.toggleModule(moduleName: moduleName, action: "enable")
                switch result {
                case .success(let module):
                    loadModules()
                case .failure(let error):
                    self.error = error.localizedDescription
                }
            } catch {
                self.error = error.localizedDescription
            }
        }
    }
    
    func disableModule(moduleName: String) {
        Task {
            do {
                let result = try await repository.toggleModule(moduleName: moduleName, action: "disable")
                switch result {
                case .success(let module):
                    loadModules()
                case .failure(let error):
                    self.error = error.localizedDescription
                }
            } catch {
                self.error = error.localizedDescription
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
                    loadModules()
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
