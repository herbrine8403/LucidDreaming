package com.luciddreaming.android.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.PaddingValues

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun AutomationScreen(paddingValues: PaddingValues) {
    var contentVisible by remember { mutableStateOf(false) }
    
    LaunchedEffect(Unit) {
        contentVisible = true
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("自动化") }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                AnimatedVisibility(
                    visible = contentVisible,
                    enter = scaleIn(
                        animationSpec = spring(
                            dampingRatio = 0.6f,
                            stiffness = 200f
                        )
                    ) + fadeIn(
                        animationSpec = tween(durationMillis = 400, easing = FastOutSlowInEasing)
                    ),
                    exit = scaleOut(
                        animationSpec = tween(durationMillis = 200)
                    ) + fadeOut(
                        animationSpec = tween(durationMillis = 200)
                    )
                ) {
                    val infiniteTransition = rememberInfiniteTransition()
                    val scale by infiniteTransition.animateFloat(
                        initialValue = 1f,
                        targetValue = 1.1f,
                        animationSpec = infiniteRepeatable(
                            animation = tween(durationMillis = 1500, easing = FastOutSlowInEasing),
                            repeatMode = androidx.compose.animation.core.RepeatMode.Reverse
                        ),
                        label = "pulse"
                    )
                    
                    Icon(
                        imageVector = Icons.Default.Build,
                        contentDescription = null,
                        modifier = Modifier
                            .size(64.dp)
                            .scale(scale),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                
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
                    Text(
                        text = "即将推出",
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                
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
                    Text(
                        text = "自动化功能正在开发中",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}