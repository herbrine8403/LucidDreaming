package com.luciddreaming.android.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.luciddreaming.android.viewmodel.SettingsViewModel
import com.luciddreaming.android.ui.theme.AccentGreen

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = viewModel(),
    onUnbindSuccess: () -> Unit
) {
    val appSettings by viewModel.appSettings.collectAsState()
    val showUnbindDialog by viewModel.showUnbindDialog.collectAsState()

    var contentVisible by remember { mutableStateOf(false) }
    var refreshInterval by remember { mutableStateOf(appSettings.refreshInterval) }
    var showSuccessMessage by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        contentVisible = true
        refreshInterval = appSettings.refreshInterval
    }

    LaunchedEffect(appSettings.refreshInterval) {
        refreshInterval = appSettings.refreshInterval
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("设置") }
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 16.dp,
                        bottom = 16.dp + paddingValues.calculateBottomPadding()
                    )
            ) {
                // 刷新时间设置
                AnimatedVisibility(
                    visible = contentVisible,
                    enter = fadeIn(animationSpec = tween(300)) + scaleIn(
                        animationSpec = spring(
                            dampingRatio = 0.6f,
                            stiffness = 200f
                        )
                    ),
                    exit = fadeOut(animationSpec = tween(200))
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        shape = RoundedCornerShape(16.dp),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = "数据刷新",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "刷新时间间隔",
                                    modifier = Modifier.weight(1f)
                                )
                                
                                Spacer(modifier = Modifier.width(8.dp))
                                
                                Text(
                                    text = "${refreshInterval}秒",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.padding(end = 8.dp)
                                )
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            Slider(
                                value = refreshInterval.toFloat(),
                                onValueChange = { refreshInterval = it.toInt() },
                                onValueChangeFinished = {
                                    viewModel.updateRefreshInterval(refreshInterval)
                                    showSuccessMessage = true
                                },
                                valueRange = 1f..30f,
                                steps = 29,
                                modifier = Modifier.fillMaxWidth()
                            )

                            Text(
                                text = "每 ${refreshInterval} 秒刷新一次数据",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                    }
                }

                // 连接设置
                AnimatedVisibility(
                    visible = contentVisible,
                    enter = fadeIn(animationSpec = tween(300, delayMillis = 100)) + scaleIn(
                        animationSpec = spring(
                            dampingRatio = 0.6f,
                            stiffness = 200f
                        )
                    ),
                    exit = fadeOut(animationSpec = tween(200))
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        shape = RoundedCornerShape(16.dp),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = "连接管理",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )

                            Text(
                                text = "取消与服务器的绑定，返回连接界面",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.padding(bottom = 16.dp)
                            )

                            Button(
                                onClick = { viewModel.showUnbindDialog() },
                                modifier = Modifier.fillMaxWidth(),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.error
                                )
                            ) {
                                Icon(
                                    imageVector = Icons.Default.LinkOff,
                                    contentDescription = null,
                                    modifier = Modifier.padding(end = 8.dp)
                                )
                                Text(text = "取消绑定")
                            }
                        }
                    }
                }

                // 关于软件
                AnimatedVisibility(
                    visible = contentVisible,
                    enter = fadeIn(animationSpec = tween(300, delayMillis = 200)) + scaleIn(
                        animationSpec = spring(
                            dampingRatio = 0.6f,
                            stiffness = 200f
                        )
                    ),
                    exit = fadeOut(animationSpec = tween(200))
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        shape = RoundedCornerShape(16.dp),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = "关于软件",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )

                            // 软件信息
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Info,
                                    contentDescription = null,
                                    modifier = Modifier.padding(end = 16.dp),
                                    tint = MaterialTheme.colorScheme.primary
                                )
                                Column {
                                    Text(
                                        text = "Lucid Dreaming",
                                        style = MaterialTheme.typography.titleMedium
                                    )
                                    Text(
                                        text = "版本 1.0.0",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }

                            Divider(modifier = Modifier.padding(vertical = 8.dp))

                            // 开发者信息
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Person,
                                    contentDescription = null,
                                    modifier = Modifier.padding(end = 16.dp),
                                    tint = MaterialTheme.colorScheme.primary
                                )
                                Column {
                                    Text(
                                        text = "开发者",
                                        style = MaterialTheme.typography.titleMedium
                                    )
                                    Text(
                                        text = "Drwei",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }

                            Divider(modifier = Modifier.padding(vertical = 8.dp))

                            // 功能特性
                            Text(
                                text = "功能特性",
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
                            )
                            
                            Text(
                                text = "• 实时监测 Minecraft 游戏状态\n" +
                                      "• 模块控制与管理\n" +
                                      "• 自动化任务配置\n" +
                                      "• HTTP 服务器通信",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }

                // 高级设置（可以扩展更多设置）
                AnimatedVisibility(
                    visible = contentVisible,
                    enter = fadeIn(animationSpec = tween(300, delayMillis = 300)) + scaleIn(
                        animationSpec = spring(
                            dampingRatio = 0.6f,
                            stiffness = 200f
                        )
                    ),
                    exit = fadeOut(animationSpec = tween(200))
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        shape = RoundedCornerShape(16.dp),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = "高级设置",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )

                            // 这里可以添加更多高级设置项
                            Text(
                                text = "更多设置选项即将推出...",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }

            LaunchedEffect(showSuccessMessage) {
                if (showSuccessMessage) {
                    kotlinx.coroutines.delay(2000)
                    showSuccessMessage = false
                }
            }
        }

        // 成功提示
        AnimatedVisibility(
            visible = showSuccessMessage,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically(),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        ) {
            Snackbar(
                action = {
                    TextButton(onClick = { showSuccessMessage = false }) {
                        Text("确定")
                    }
                }
            ) {
                Text("设置已保存")
            }
        }

        // 取消绑定确认对话框
        if (showUnbindDialog) {
        AlertDialog(
            onDismissRequest = { viewModel.hideUnbindDialog() },
            icon = {
                Icon(
                    imageVector = Icons.Default.LinkOff,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.error
                )
            },
            title = {
                Text(text = "取消绑定")
            },
            text = {
                Text("确定要取消与服务器的绑定吗？这将返回到连接界面。")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.unbindConnection()
                        onUnbindSuccess()
                    }
                ) {
                    Text("确定", color = MaterialTheme.colorScheme.error)
                }
            },
            dismissButton = {
                TextButton(onClick = { viewModel.hideUnbindDialog() }) {
                    Text("取消")
                }
            }
        )
    }
}
