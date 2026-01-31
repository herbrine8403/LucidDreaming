import SwiftUI

struct ModulesScreen: View {
    @ObservedObject var viewModel: ModulesViewModel
    
    @State private var isAutoRefreshing = true
    
    var body: some View {
        ScrollView {
            VStack(spacing: 20) {
                HStack {
                    Text("Modules")
                        .font(.title)
                        .fontWeight(.bold)
                    Spacer()
                    Button(action: {
                        viewModel.loadModules()
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
                } else if let modulesResponse = viewModel.modulesResponse {
                    Text("Total: \(modulesResponse.total), Enabled: \(modulesResponse.enabled)")
                        .font(.subheadline)
                        .padding(.bottom, 10)
                    
                    ForEach(modulesResponse.modules, id: \.name) { module in
                        VStack(spacing: 10) {
                            HStack {
                                VStack(alignment: .leading) {
                                    Text(module.displayName)
                                        .font(.headline)
                                    Text(module.description)
                                        .font(.body)
                                        .lineLimit(2)
                                    Text("Category: \(module.category)")
                                        .font(.caption)
                                }
                                Spacer()
                                Toggle(isOn: Binding(
                                    get: { module.enabled },
                                    set: { _ in
                                        viewModel.toggleModule(moduleName: module.name)
                                    }
                                )) {
                                    Text("")
                                }
                            }
                            Text("Version: \(module.version)")
                                .font(.caption)
                                .frame(maxWidth: .infinity, alignment: .trailing)
                        }
                        .padding()
                        .background(Color.gray.opacity(0.1))
                        .cornerRadius(10)
                        .padding(.bottom, 10)
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
            viewModel.loadModules()
            if isAutoRefreshing {
                viewModel.startAutoRefresh()
            }
        }
        .onDisappear {
            viewModel.stopAutoRefresh()
        }
    }
}
