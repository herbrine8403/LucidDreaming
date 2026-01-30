package com.luciddreaming.android.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.luciddreaming.android.viewmodel.ConnectionViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConnectScreen(
    viewModel: ConnectionViewModel,
    onConnected: () -> Unit
) {
    val connectionSettings by viewModel.connectionSettings.collectAsState()
    val isConnecting by viewModel.isConnecting.collectAsState()
    val connectionError by viewModel.connectionError.collectAsState()

    var ipAddress by remember { mutableStateOf(connectionSettings.ipAddress) }
    var port by remember { mutableStateOf(connectionSettings.port) }

    LaunchedEffect(connectionSettings.isConnected) {
        if (connectionSettings.isConnected) {
            onConnected()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("LucidDreaming") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Logo/Icon
            Surface(
                modifier = Modifier.size(120.dp),
                shape = RoundedCornerShape(24.dp),
                color = MaterialTheme.colorScheme.primaryContainer
            ) {
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Wifi,
                        contentDescription = null,
                        modifier = Modifier.size(60.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "连接到服务器",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "请输入LucidDreaming Mod的IP地址和端口",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(32.dp))

            // IP Address Input
            OutlinedTextField(
                value = ipAddress,
                onValueChange = { ipAddress = it },
                label = { Text("IP地址") },
                placeholder = { Text("例如: 192.168.1.100") },
                singleLine = true,
                enabled = !isConnecting,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Uri)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Port Input
            OutlinedTextField(
                value = port,
                onValueChange = { port = it },
                label = { Text("端口") },
                placeholder = { Text("默认: 1122") },
                singleLine = true,
                enabled = !isConnecting,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Error message
            if (connectionError != null) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.errorContainer
                    )
                ) {
                    Text(
                        text = connectionError!!,
                        color = MaterialTheme.colorScheme.onErrorContainer,
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Connect Button
            Button(
                onClick = {
                    viewModel.clearError()
                    viewModel.connect(ipAddress, port)
                },
                enabled = !isConnecting && ipAddress.isNotBlank() && port.isNotBlank(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                if (isConnecting) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = MaterialTheme.colorScheme.onPrimary,
                        strokeWidth = 2.dp
                    )
                } else {
                    Text(
                        text = "连接",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "默认端口: 1122",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}