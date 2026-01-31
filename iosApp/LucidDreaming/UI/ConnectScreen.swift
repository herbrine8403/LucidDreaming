import SwiftUI

struct ConnectScreen: View {
    @ObservedObject var viewModel: ConnectionViewModel
    let onConnected: () -> Void
    
    @State private var ipAddress: String = "127.0.0.1"
    @State private var port: String = "8080"
    
    var body: some View {
        VStack(spacing: 20) {
            Text("Connect to LucidDreaming")
                .font(.largeTitle)
                .fontWeight(.bold)
                .padding(.bottom, 40)
            
            VStack(spacing: 20) {
                TextField("IP Address", text: $ipAddress)
                    .textFieldStyle(RoundedBorderTextFieldStyle())
                    .padding(.horizontal, 40)
                    .disabled(viewModel.isConnecting)
                
                TextField("Port", text: $port)
                    .textFieldStyle(RoundedBorderTextFieldStyle())
                    .padding(.horizontal, 40)
                    .disabled(viewModel.isConnecting)
                
                if let error = viewModel.connectionError {
                    Text(error)
                        .foregroundColor(.red)
                        .padding(.horizontal, 40)
                }
                
                Button(action: {
                    viewModel.connect(ipAddress: ipAddress, port: port)
                }) {
                    if viewModel.isConnecting {
                        ProgressView()
                            .padding()
                            .frame(maxWidth: .infinity)
                            .background(Color.blue)
                            .foregroundColor(.white)
                            .cornerRadius(10)
                            .padding(.horizontal, 40)
                    } else {
                        Text("Connect")
                            .padding()
                            .frame(maxWidth: .infinity)
                            .background(Color.blue)
                            .foregroundColor(.white)
                            .cornerRadius(10)
                            .padding(.horizontal, 40)
                    }
                }
                .disabled(viewModel.isConnecting)
            }
        }
        .padding()
        .onReceive(viewModel.$connectionSettings) { settings in
            if settings.isConnected {
                onConnected()
            }
        }
    }
}
