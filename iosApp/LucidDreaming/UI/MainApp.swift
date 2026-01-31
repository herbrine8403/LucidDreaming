import SwiftUI
import Shared
import UIKit

struct MainApp: View {
    @StateObject private var connectionViewModel = ConnectionViewModel()
    @StateObject private var monitorViewModel = MonitorViewModel()
    @StateObject private var modulesViewModel = ModulesViewModel()
    @StateObject private var settingsViewModel = SettingsViewModel()
    
    @State private var selectedTab: Tab = .monitor
    @State private var showConnectScreen = true
    @State private var showAboutScreen = false
    
    enum Tab {
        case monitor
        case modules
        case automation
        case settings
    }
    
    var body: some View {
        Group {
            if showConnectScreen && !connectionViewModel.connectionSettings.isConnected {
                ConnectScreen(
                    viewModel: connectionViewModel,
                    onConnected: {
                        showConnectScreen = false
                    }
                )
            } else {
                if isTablet() {
                    // Tablet layout with side navigation
                    NavigationView {
                        HStack {
                            // Sidebar navigation
                            VStack(alignment: .leading, spacing: 20) {
                                Text("LucidDreaming")
                                    .font(.largeTitle)
                                    .fontWeight(.bold)
                                    .padding(.horizontal, 20)
                                    .padding(.top, 40)
                                
                                ForEach(Tab.allCases, id: \.self) {
                                    NavigationLink(
                                        destination: getScreenForTab($0),
                                        tag: $0,
                                        selection: $selectedTab
                                    ) {
                                        HStack {
                                            Image(systemName: $0.systemImage)
                                                .frame(width: 24, height: 24)
                                            Text($0.title)
                                                .font(.body)
                                        }
                                        .padding(.horizontal, 20)
                                        .padding(.vertical, 10)
                                        .background(selectedTab == $0 ? Color.blue.opacity(0.1) : Color.clear)
                                        .cornerRadius(10)
                                        .padding(.horizontal, 10)
                                    }
                                }
                                
                                Spacer()
                                
                                Button(action: {
                                    showAboutScreen = true
                                }) {
                                    HStack {
                                        Image(systemName: "info.circle")
                                            .frame(width: 24, height: 24)
                                        Text("About")
                                            .font(.body)
                                    }
                                    .padding(.horizontal, 20)
                                    .padding(.vertical, 10)
                                    .padding(.horizontal, 10)
                                }
                                .padding(.bottom, 40)
                            }
                            .frame(width: 200)
                            .background(Color.gray.opacity(0.05))
                            
                            // Main content
                            getScreenForTab(selectedTab)
                                .navigationTitle(selectedTab.title)
                        }
                    }
                    .sheet(isPresented: $showAboutScreen) {
                        AboutScreen()
                    }
                } else {
                    // Phone layout with tab bar
                    NavigationView {
                        TabView(selection: $selectedTab) {
                            MonitorScreen(viewModel: monitorViewModel)
                                .tabItem {
                                    Label("Monitor", systemImage: "chart.bar")
                                }
                                .tag(Tab.monitor)
                            
                            ModulesScreen(viewModel: modulesViewModel)
                                .tabItem {
                                    Label("Modules", systemImage: "list.bullet")
                                }
                                .tag(Tab.modules)
                            
                            AutomationScreen()
                                .tabItem {
                                    Label("Automation", systemImage: "gear")
                                }
                                .tag(Tab.automation)
                            
                            SettingsScreen(
                                viewModel: settingsViewModel,
                                onDisconnected: {
                                    showConnectScreen = true
                                }
                            )
                                .tabItem {
                                    Label("Settings", systemImage: "gearshape")
                                }
                                .tag(Tab.settings)
                        }
                        .navigationTitle(selectedTab.title)
                        .navigationBarItems(trailing: {
                            Button(action: {
                                showAboutScreen = true
                            }) {
                                Image(systemName: "info.circle")
                            }
                        }())
                    }
                    .sheet(isPresented: $showAboutScreen) {
                        AboutScreen()
                    }
                }
            }
        }
    }
    
    private func getScreenForTab(_ tab: Tab) -> some View {
        switch tab {
        case .monitor:
            return AnyView(MonitorScreen(viewModel: monitorViewModel))
        case .modules:
            return AnyView(ModulesScreen(viewModel: modulesViewModel))
        case .automation:
            return AnyView(AutomationScreen())
        case .settings:
            return AnyView(SettingsScreen(
                viewModel: settingsViewModel,
                onDisconnected: {
                    showConnectScreen = true
                }
            ))
        }
    }
    
    private func isTablet() -> Bool {
        let idiom = UIDevice.current.userInterfaceIdiom
        let size = UIScreen.main.bounds.size
        let width = min(size.width, size.height)
        return idiom == .pad || width >= 768
    }
}

extension MainApp.Tab {
    var title: String {
        switch self {
        case .monitor:
            return "Monitor"
        case .modules:
            return "Modules"
        case .automation:
            return "Automation"
        case .settings:
            return "Settings"
        }
    }
    
    var systemImage: String {
        switch self {
        case .monitor:
            return "chart.bar"
        case .modules:
            return "list.bullet"
        case .automation:
            return "gear"
        case .settings:
            return "gearshape"
        }
    }
}

extension MainApp.Tab: CaseIterable {
    static var allCases: [MainApp.Tab] {
        return [.monitor, .modules, .automation, .settings]
    }
}
