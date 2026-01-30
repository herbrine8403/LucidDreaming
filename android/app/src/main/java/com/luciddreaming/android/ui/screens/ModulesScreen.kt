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
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.luciddreaming.android.data.model.Module
import com.luciddreaming.android.ui.theme.AccentGreen
import com.luciddreaming.android.viewmodel.ModulesViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun ModulesScreen(viewModel: ModulesViewModel) {
    val modulesResponse by viewModel.modulesResponse.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadModules()
        viewModel.startAutoRefresh()
    }
    
    // 当屏幕销毁时停止自动刷新
    DisposableEffect(Unit) {
        onDispose {
            viewModel.stopAutoRefresh()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("模块") },
                actions = {
                    var refreshing by remember { mutableStateOf(false) }
                    val rotation by animateFloatAsState(
                        targetValue = if (refreshing) 360f else 0f,
                        animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing),
                        finishedListener = { if (refreshing) refreshing = false },
                        label = "refreshRotation"
                    )
                    
                    IconButton(onClick = {
                        refreshing = true
                        viewModel.loadModules()
                    }) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "刷新",
                            modifier = Modifier.scale(if (refreshing) 1.2f else 1f)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        when {
            isLoading && modulesResponse == null -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            error != null && modulesResponse == null -> {
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
                        Button(onClick = { viewModel.loadModules() }) {
                            Text("重试")
                        }
                    }
                }
            }
            modulesResponse != null -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    itemsIndexed(modulesResponse!!.modules) { index, module ->
                        AnimatedModuleCard(
                            module = module,
                            index = index,
                            onToggle = { viewModel.toggleModule(module.name) }
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun AnimatedModuleCard(
    module: Module,
    index: Int,
    onToggle: () -> Unit
) {
    var cardVisible by remember { mutableStateOf(false) }
    
    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(100L * index.toLong())
        cardVisible = true
    }
    
    var cardPressed by remember { mutableStateOf(false) }
    val cardScale by animateFloatAsState(
        targetValue = if (cardPressed) 0.98f else 1f,
        animationSpec = spring(
            dampingRatio = 0.6f,
            stiffness = 400f
        ),
        label = "cardScale"
    )
    
    AnimatedVisibility(
        visible = cardVisible,
        enter = slideInVertically(
            initialOffsetY = { it / 2 },
            animationSpec = tween(
                durationMillis = 400,
                easing = FastOutSlowInEasing,
                delayMillis = index * 50
            )
        ) + fadeIn(
            animationSpec = tween(
                durationMillis = 400,
                delayMillis = index * 50
            )
        ),
        exit = fadeOut(animationSpec = tween(durationMillis = 200))
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .scale(cardScale),
            colors = CardDefaults.cardColors(
                containerColor = if (module.enabled) {
                    MaterialTheme.colorScheme.primaryContainer
                } else {
                    MaterialTheme.colorScheme.surfaceVariant
                }
            ),
            onClick = {
                cardPressed = true
                onToggle()
            }
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = module.localizedName,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold,
                            color = if (module.enabled) AccentGreen else MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        AnimatedVisibility(
                            visible = cardVisible,
                            enter = fadeIn(
                                animationSpec = tween(
                                    durationMillis = 300,
                                    delayMillis = 100
                                )
                            )
                        ) {
                            Text(
                                text = module.localizedDescription,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    var switchChecked by remember { mutableStateOf(module.enabled) }
                    LaunchedEffect(module.enabled) {
                        switchChecked = module.enabled
                    }
                    Switch(
                        checked = switchChecked,
                        onCheckedChange = { 
                            switchChecked = it
                            onToggle()
                        },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = AccentGreen,
                            checkedTrackColor = AccentGreen.copy(alpha = 0.5f),
                            uncheckedThumbColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            uncheckedTrackColor = MaterialTheme.colorScheme.outlineVariant
                        )
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                AnimatedVisibility(
                    visible = cardVisible,
                    enter = expandVertically(
                        animationSpec = spring(
                            dampingRatio = 0.7f,
                            stiffness = 200f
                        )
                    ) + fadeIn(
                        animationSpec = tween(
                            durationMillis = 300,
                            delayMillis = 150
                        )
                    )
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        SuggestionChip(
                            onClick = {},
                            label = { Text(module.localizedCategory) },
                            colors = SuggestionChipDefaults.suggestionChipColors(
                                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                                labelColor = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                        )
                        if (module.keybind != "NONE") {
                            SuggestionChip(
                                onClick = {},
                                label = { Text("快捷键: ${module.keybind}") },
                                colors = SuggestionChipDefaults.suggestionChipColors(
                                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                                    labelColor = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}