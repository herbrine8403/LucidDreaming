package com.luciddreaming.android.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.luciddreaming.android.viewmodel.MonitorViewModel
import java.io.ByteArrayInputStream
import android.graphics.BitmapFactory

@Composable
fun MonitorScreen(
    viewModel: MonitorViewModel,
    paddingValues: PaddingValues
) {
    val gameInfo by viewModel.gameInfo.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()
    val screenshot by viewModel.screenshot.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadGameInfo()
        viewModel.startAutoRefresh()
    }

    DisposableEffect(Unit) {
        onDispose {
            viewModel.stopAutoRefresh()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Game Monitor",
                style = MaterialTheme.typography.titleLarge
            )
            Button(onClick = { viewModel.loadGameInfo() }) {
                Text("Refresh")
            }
        }

        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else if (error != null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = error!!,
                    color = MaterialTheme.colorScheme.error
                )
            }
        } else {
            gameInfo?.let {info ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = info.serverName,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Version: ${info.version}")
                            Text(text = "Players: ${info.playerCount}")
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Map: ${info.mapName}")
                            Text(text = "Mode: ${info.gameMode}")
                        }
                        Text(text = "Uptime: ${formatUptime(info.uptime)}")
                    }
                }
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Game Screenshot",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .aspectRatio(16f / 9f)
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        screenshot?.let {bitmapData ->
                            val bitmap = BitmapFactory.decodeStream(ByteArrayInputStream(bitmapData))
                            AsyncImage(
                                model = bitmap,
                                contentDescription = "Game screenshot",
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        } ?: run {
                            Text(
                                text = "No screenshot available",
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                    Button(
                        onClick = { viewModel.loadScreenshot() },
                        modifier = Modifier.padding(top = 16.dp)
                    ) {
                        Text("Capture Screenshot")
                    }
                }
            }
        }
    }
}

private fun formatUptime(seconds: Long): String {
    val hours = seconds / 3600
    val minutes = (seconds % 3600) / 60
    val secs = seconds % 60
    return "${hours}h ${minutes}m ${secs}s"
}
