package com.luciddreaming.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.ui.unit.IntOffset
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.luciddreaming.android.ui.components.BottomNavBar
import com.luciddreaming.android.ui.components.Screen
import com.luciddreaming.android.ui.screens.*
import com.luciddreaming.android.ui.theme.LucidDreamingTheme
import com.luciddreaming.android.viewmodel.ConnectionViewModel
import com.luciddreaming.android.viewmodel.MonitorViewModel
import com.luciddreaming.android.viewmodel.ModulesViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LucidDreamingApp()
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun LucidDreamingApp() {
    LucidDreamingTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val connectionViewModel: ConnectionViewModel = viewModel()
            val monitorViewModel: MonitorViewModel = viewModel()
            val modulesViewModel: ModulesViewModel = viewModel()

            val connectionSettings by connectionViewModel.connectionSettings.collectAsState()

            if (connectionSettings.isConnected) {
                // Main app with bottom navigation
                val navController = rememberNavController()
                var currentScreen by remember { mutableStateOf(Screen.MONITOR) }

                Scaffold(
                    bottomBar = {
                        BottomNavBar(
                            currentScreen = currentScreen,
                            onNavigate = { screen ->
                                currentScreen = screen
                            }
                        )
                    }
                ) { paddingValues: PaddingValues ->
                    androidx.compose.animation.AnimatedContent(
                        targetState = currentScreen,
                        transitionSpec = {
                            val animationSpec = tween<IntOffset>(
                                durationMillis = 300,
                                easing = FastOutSlowInEasing
                            )
                            
                            ContentTransform(
                                slideInHorizontally(
                                    animationSpec = animationSpec,
                                    initialOffsetX = { -it }
                                ),
                                slideOutHorizontally(
                                    animationSpec = animationSpec,
                                    targetOffsetX = { -it }
                                )
                            )
                        },
                        label = "screenTransition"
                    ) { targetScreen ->
                        when (targetScreen) {
                            Screen.MONITOR -> {
                                MonitorScreen(
                                    viewModel = monitorViewModel
                                )
                            }
                            Screen.MODULES -> {
                                ModulesScreen(
                                    viewModel = modulesViewModel
                                )
                            }
                            Screen.AUTOMATION -> {
                                AutomationScreen()
                            }
                        }
                    }
                }
            } else {
                // Connection screen
                ConnectScreen(
                    viewModel = connectionViewModel,
                    onConnected = {
                        // Connection successful, will automatically switch to main app
                    }
                )
            }
        }
    }
}
