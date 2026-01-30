package com.luciddreaming.android.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.luciddreaming.android.data.model.GameInfo
import com.luciddreaming.android.ui.theme.AccentGreen
import com.luciddreaming.android.viewmodel.MonitorViewModel

@Composable
fun MonitorScreen(viewModel: MonitorViewModel) {
    val gameInfo by viewModel.gameInfo.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()
    val screenshot by viewModel.screenshot.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadGameInfo()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("监测") },
                actions = {
                    IconButton(onClick = {
                        viewModel.loadGameInfo()
                    }) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "刷新"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        when {
            isLoading && gameInfo == null -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            error != null && gameInfo == null -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.Error,
                            contentDescription = null,
                            modifier = Modifier.size(64.dp),
                            tint = MaterialTheme.colorScheme.error
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = error!!,
                            color = MaterialTheme.colorScheme.error
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = { viewModel.loadGameInfo() }) {
                            Text("重试")
                        }
                    }
                }
            }
            gameInfo != null -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .verticalScroll(rememberScrollState())
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Screenshot
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant
                        )
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "截图",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.SemiBold
                                )
                                IconButton(onClick = { viewModel.loadScreenshot() }) {
                                    Icon(
                                        imageVector = Icons.Default.Refresh,
                                        contentDescription = "刷新截图"
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(12.dp))
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                                    .background(
                                        color = MaterialTheme.colorScheme.surface,
                                        shape = RoundedCornerShape(8.dp)
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                if (screenshot != null) {
                                    AsyncImage(
                                        model = screenshot,
                                        contentDescription = "游戏截图",
                                        modifier = Modifier.fillMaxSize()
                                    )
                                } else {
                                    CircularProgressIndicator()
                                }
                            }
                        }
                    }

                    // Player Info
                    InfoCard(
                        title = "玩家信息",
                        icon = Icons.Default.Person
                    ) {
                        InfoRow("名称", gameInfo!!.player.name)
                        InfoRow("生命值", "${gameInfo!!.player.health}/${gameInfo!!.player.maxHealth}")
                        InfoRow("饥饿度", "${gameInfo!!.player.hunger}/20")
                        InfoRow("经验", "${gameInfo!!.player.experienceLevel} (${(gameInfo!!.player.experienceProgress * 100).toInt()}%)")
                        InfoRow("位置", gameInfo!!.player.position)
                        InfoRow("维度", gameInfo!!.player.dimension)
                        InfoRow("游戏模式", gameInfo!!.player.gameMode)
                    }

                    // Server Info
                    InfoCard(
                        title = "服务器信息",
                        icon = Icons.Default.Dns
                    ) {
                        InfoRow("类型", gameInfo!!.server.type)
                        InfoRow("地址", gameInfo!!.server.address)
                        if (gameInfo!!.server.name.isNotEmpty()) {
                            InfoRow("名称", gameInfo!!.server.name)
                        }
                    }

                    // Game Info
                    InfoCard(
                        title = "游戏信息",
                        icon = Icons.Default.VideogameAsset
                    ) {
                        InfoRow("Minecraft", gameInfo!!.game.minecraftVersion)
                        InfoRow("Forge", gameInfo!!.game.forgeVersion)
                        InfoRow("Mod", gameInfo!!.game.modVersion)
                        InfoRow("运行时间", gameInfo!!.game.uptime)
                        InfoRow("FPS", "${gameInfo!!.game.fps}")
                    }

                    // System Info
                    InfoCard(
                        title = "系统信息",
                        icon = Icons.Default.Computer
                    ) {
                        InfoRow("操作系统", "${gameInfo!!.system.osName} ${gameInfo!!.system.osVersion}")
                        InfoRow("Java", gameInfo!!.system.javaVersion)
                        InfoRow("当前时间", gameInfo!!.game.currentTime)
                    }

                    // Scoreboard
                    if (gameInfo!!.scoreboard.isNotEmpty()) {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xFF808080)
                            )
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(
                                    text = "计分板",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color.White
                                )
                                Spacer(modifier = Modifier.height(12.dp))
                                gameInfo!!.scoreboard.forEach { line ->
                                    Text(
                                        text = line,
                                        fontFamily = FontFamily.Monospace,
                                        color = Color.White,
                                        modifier = Modifier.padding(vertical = 2.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun InfoCard(
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = AccentGreen,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            content()
        }
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}