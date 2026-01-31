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
import androidx.compose.material3.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.luciddreaming.android.ui.components.*
import com.luciddreaming.android.ui.screens.*
import com.luciddreaming.android.ui.theme.LucidDreamingTheme
import com.luciddreaming.android.viewmodel.ConnectionViewModel
import com.luciddreaming.android.viewmodel.MonitorViewModel
import com.luciddreaming.android.viewmodel.ModulesViewModel
import com.luciddreaming.android.viewmodel.SettingsViewModel

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
            val settingsViewModel: SettingsViewModel = viewModel()

            val connectionSettings by connectionViewModel.connectionSettings.collectAsState()

            if (connectionSettings.isConnected) {
                // Main app with side navigation drawer
                val navController = rememberNavController()
                var currentScreen by remember { mutableStateOf(Screen.MONITOR) }
                val drawerState = rememberDrawerState(DrawerValue.Closed)
                val scope = rememberCoroutineScope()

                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        SideNavDrawer(
                            currentScreen = currentScreen,
                            onNavigate = { screen ->
                                currentScreen = screen
                            },
                            onCloseDrawer = {
                                scope.launch {
                                    drawerState.close()
                                }
                            }
                        )
                    }
                ) {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = { Text(currentScreen.title) },
                                navigationIcon = {
                                    DrawerButton {
                                        scope.launch {
                                            drawerState.open()
                                        }
                                    }
                                }
                            )
                        }
                    ) {paddingValues: PaddingValues ->
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
                                        viewModel = monitorViewModel,
                                        paddingValues = paddingValues
                                    )
                                }
                                Screen.MODULES -> {
                                    ModulesScreen(
                                        viewModel = modulesViewModel,
                                        paddingValues = paddingValues
                                    )
                                }
                                Screen.AUTOMATION -> {
                                    AutomationScreen(
                                        paddingValues = paddingValues
                                    )
                                }
                                Screen.SETTINGS -> {
                                    SettingsScreen(
                                        viewModel = settingsViewModel,
                                        onUnbindSuccess = {
                                            // 返回连接界面
                                        },
                                        paddingValues = paddingValues
                                    )
                                }
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
