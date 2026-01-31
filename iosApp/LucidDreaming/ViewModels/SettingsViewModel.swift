import Foundation
import Combine
import Shared

class SettingsViewModel: ObservableObject {
    @Published var settings: AppSettings = AppSettings()
    
    private let settingsPreferences = iOSSsettingsPreferences()
    private let connectionPreferences = iOSConnectionPreferences()
    private var cancellables = Set<AnyCancellable>()
    
    init() {
        loadSettings()
    }
    
    private func loadSettings() {
        settingsPreferences.settings
            .sink {
                self.settings = $0
            }
            .store(in: &cancellables)
    }
    
    func updateRefreshInterval(interval: Int) {
        Task {
            await settingsPreferences.updateSettings(
                refreshInterval: interval,
                autoRefresh: settings.autoRefresh,
                darkMode: settings.darkMode
            )
        }
    }
    
    func updateAutoRefresh(enabled: Bool) {
        Task {
            await settingsPreferences.updateSettings(
                refreshInterval: settings.refreshInterval,
                autoRefresh: enabled,
                darkMode: settings.darkMode
            )
        }
    }
    
    func updateDarkMode(enabled: Bool) {
        Task {
            await settingsPreferences.updateSettings(
                refreshInterval: settings.refreshInterval,
                autoRefresh: settings.autoRefresh,
                darkMode: enabled
            )
        }
    }
    
    func resetSettings() {
        Task {
            await settingsPreferences.resetSettings()
        }
    }
    
    func disconnect() {
        Task {
            await connectionPreferences.clearConnectionSettings()
        }
    }
}
