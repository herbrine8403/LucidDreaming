package com.luciddreaming.android.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.luciddreaming.android.data.model.GameInfo
import com.luciddreaming.android.ui.theme.AccentGreen
import com.luciddreaming.android.viewmodel.MonitorViewModel
import kotlinx.coroutines.launch

@Composable
fun AnimatedInfoCard(
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    content: @Composable ColumnScope.() -> Unit
) {
    var iconScale by remember { mutableStateOf(0f) }
    
    LaunchedEffect(Unit) {
        iconScale = 1f
    }
    
    val animatedIconScale by animateFloatAsState(
        targetValue = iconScale,
        animationSpec = spring(
            dampingRatio = 0.6f,
            stiffness = 300f
        ),
        label = "iconScale"
    )
    
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
                    modifier = Modifier
                        .size(20.dp)
                        .scale(animatedIconScale)
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

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun MonitorScreen(viewModel: MonitorViewModel, paddingValues: PaddingValues) {
    val gameInfo by viewModel.gameInfo.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()
    val screenshot by viewModel.screenshot.collectAsState()

    var contentVisible by remember { mutableStateOf(false) }
    
    LaunchedEffect(Unit) {
        contentVisible = true
        viewModel.loadGameInfo()
        viewModel.startAutoRefresh()
    }
    
    // 当屏幕销毁时停止自动刷新
    DisposableEffect(Unit) {
        onDispose {
            viewModel.stopAutoRefresh()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = paddingValues.calculateTopPadding() + 8.dp,
                bottom = 16.dp,
                start = 16.dp,
                end = 16.dp
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // 刷新按钮
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                var refreshing by remember { mutableStateOf(false) }
                val rotation by animateFloatAsState(
                    targetValue = if (refreshing) 360f else 0f,
                    animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing),
                    finishedListener = { if (refreshing) refreshing = false },
                    label = "refreshRotation"
                )
                
                IconButton(onClick = {
                    refreshing = true
                    viewModel.loadGameInfo()
                }) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "刷新",
                        modifier = Modifier.scale(if (refreshing) 1.2f else 1f)
                    )
                }
            }
            
            when {
                isLoading && gameInfo == null -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                error != null && gameInfo == null -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f),
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
                            .weight(1f)
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        // Screenshot Card with animation
                        AnimatedVisibility(
                            visible = contentVisible,
                            enter = slideInVertically(
                                initialOffsetY = { it / 2 },
                                animationSpec = tween(durationMillis = 400, easing = FastOutSlowInEasing)
                            ) + fadeIn(
                                animationSpec = tween(durationMillis = 400)
                            ),
                            exit = fadeOut(animationSpec = tween(durationMillis = 200))
                        ) {
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
                                        var screenshotRefreshing by remember { mutableStateOf(false) }
                                        val screenshotRotation by animateFloatAsState(
                                            targetValue = if (screenshotRefreshing) 360f else 0f,
                                            animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing),
                                            finishedListener = { if (screenshotRefreshing) screenshotRefreshing = false },
                                            label = "screenshotRotation"
                                        )
                                        IconButton(onClick = { 
                                            screenshotRefreshing = true
                                            viewModel.loadScreenshot() 
                                        }) {
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
                        }

                        // Player Info Card
                        AnimatedVisibility(
                            visible = contentVisible,
                            enter = slideInVertically(
                                initialOffsetY = { it / 2 },
                                animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
                            ) + fadeIn(
                                animationSpec = tween(durationMillis = 500)
                            ),
                            exit = fadeOut(animationSpec = tween(durationMillis = 200))
                        ) {
                            AnimatedInfoCard(
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
                        }

                        // Server Info Card
                        AnimatedVisibility(
                            visible = contentVisible,
                            enter = slideInVertically(
                                initialOffsetY = { it / 2 },
                                animationSpec = tween(durationMillis = 600, easing = FastOutSlowInEasing)
                            ) + fadeIn(
                                animationSpec = tween(durationMillis = 600)
                            ),
                            exit = fadeOut(animationSpec = tween(durationMillis = 200))
                        ) {
                            AnimatedInfoCard(
                                title = "服务器信息",
                                icon = Icons.Default.Dns
                            ) {
                                InfoRow("类型", gameInfo!!.server.type)
                                InfoRow("地址", gameInfo!!.server.address)
                                if (gameInfo!!.server.name.isNotEmpty()) {
                                    InfoRow("名称", gameInfo!!.server.name)
                                }
                            }
                        }

                        // Game Info Card
                        AnimatedVisibility(
                            visible = contentVisible,
                            enter = slideInVertically(
                                initialOffsetY = { it / 2 },
                                animationSpec = tween(durationMillis = 700, easing = FastOutSlowInEasing)
                            ) + fadeIn(
                                animationSpec = tween(durationMillis = 700)
                            ),
                            exit = fadeOut(animationSpec = tween(durationMillis = 200))
                        ) {
                            AnimatedInfoCard(
                                title = "游戏信息",
                                icon = Icons.Default.VideogameAsset
                            ) {
                                InfoRow("Minecraft", gameInfo!!.game.minecraftVersion)
                                InfoRow("Forge", gameInfo!!.game.forgeVersion)
                                InfoRow("Mod", gameInfo!!.game.modVersion)
                                InfoRow("运行时间", gameInfo!!.game.uptime)
                                InfoRow("FPS", "${gameInfo!!.game.fps}")
                            }
                        }

                        // System Info Card
                        AnimatedVisibility(
                            visible = contentVisible,
                            enter = slideInVertically(
                                initialOffsetY = { it / 2 },
                                animationSpec = tween(durationMillis = 800, easing = FastOutSlowInEasing)
                            ) + fadeIn(
                                animationSpec = tween(durationMillis = 800)
                            ),
                            exit = fadeOut(animationSpec = tween(durationMillis = 200))
                        ) {
                            AnimatedInfoCard(
                                title = "系统信息",
                                icon = Icons.Default.Computer
                            ) {
                                InfoRow("操作系统", "${gameInfo!!.system.osName} ${gameInfo!!.system.osVersion}")
                                InfoRow("Java", gameInfo!!.system.javaVersion)
                                InfoRow("当前时间", gameInfo!!.game.currentTime)
                            }
                        }

                        // Scoreboard Card
                        if (gameInfo!!.scoreboard.isNotEmpty()) {
                            AnimatedVisibility(
                                visible = contentVisible,
                                enter = slideInVertically(
                                    initialOffsetY = { it / 2 },
                                    animationSpec = tween(durationMillis = 900, easing = FastOutSlowInEasing)
                                ) + fadeIn(
                                    animationSpec = tween(durationMillis = 900)
                                ),
                                exit = fadeOut(animationSpec = tween(durationMillis = 200))
                            ) {
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
                                        gameInfo!!.scoreboard.forEachIndexed { index, line ->
                                            var lineVisible by remember { mutableStateOf(false) }
                                            LaunchedEffect(Unit) {
                                                kotlinx.coroutines.delay(900L + index * 50L)
                                                lineVisible = true
                                            }
                                            AnimatedVisibility(
                                                visible = lineVisible,
                                                enter = fadeIn(
                                                    animationSpec = tween(durationMillis = 300)
                                                )
                                            ) {
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
        }
    }
}