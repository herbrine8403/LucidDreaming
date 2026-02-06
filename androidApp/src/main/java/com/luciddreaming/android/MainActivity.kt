package com.luciddreaming.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
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
                // Main app with navigation
                val navController = rememberNavController()
                var currentScreen by remember { mutableStateOf(Screen.MONITOR) }
                val drawerState = rememberDrawerState(DrawerValue.Closed)
                val scope = rememberCoroutineScope()
                var showAboutScreen by remember { mutableStateOf(false) }
                
                // Check if device is a tablet
                val isTablet = isTabletDevice()

                if (isTablet) {
                    // Tablet layout with side navigation
                    Row(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        // Side navigation
                        NavigationRail(
                            modifier = Modifier.width(80.dp)
                        ) {
                            Screen.values().forEach {
                                NavigationRailItem(
                                    icon = {
                                        when (it) {
                                            Screen.MONITOR -> Icon(Icons.Filled.InsertChart, contentDescription = it.title)
                                            Screen.MODULES -> Icon(Icons.Filled.List, contentDescription = it.title)
                                            Screen.AUTOMATION -> Icon(Icons.Filled.Settings, contentDescription = it.title)
                                            Screen.SETTINGS -> Icon(Icons.Filled.Gear, contentDescription = it.title)
                                        }
                                    },
                                    label = { Text(it.title) },
                                    selected = currentScreen == it,
                                    onClick = { currentScreen = it }
                                )
                            }
                            Spacer(modifier = Modifier.weight(1f))
                            NavigationRailItem(
                                icon = { Icon(Icons.Filled.Info, contentDescription = "About") },
                                label = { Text("About") },
                                selected = false,
                                onClick = { showAboutScreen = true }
                            )
                        }
                        
                        // Main content
                        Box(
                            modifier = Modifier.weight(1f)
                        ) {
                            Scaffold(
                                topBar = {
                                    TopAppBar(
                                        title = { Text(currentScreen.title) }
                                    )
                                }
                            ) {
                                AnimatedContent(
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
                                ) {
                                    when (currentScreen) {
                                        Screen.MONITOR -> {
                                            MonitorScreen(
                                                viewModel = monitorViewModel,
                                                paddingValues = it
                                            )
                                        }
                                        Screen.MODULES -> {
                                            ModulesScreen(
                                                viewModel = modulesViewModel,
                                                paddingValues = it
                                            )
                                        }
                                        Screen.AUTOMATION -> {
                                            AutomationScreen(
                                                paddingValues = it
                                            )
                                        }
                                        Screen.SETTINGS -> {
                                            SettingsScreen(
                                                viewModel = settingsViewModel,
                                                onUnbindSuccess = {
                                                    // Return to connection screen
                                                },
                                                paddingValues = it
                                            )
                                        }
                                    }
                                }
                            }

                            // About screen
                            if (showAboutScreen) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .align(Alignment.Center)
                                ) {
                                    AboutScreen(
                                        onClose = {
                                            showAboutScreen = false
                                        }
                                    )
                                }
                            }
                        }
                    }
                } else {
                    // Phone layout with drawer navigation
                    ModalNavigationDrawer(
                        drawerState = drawerState,
                        drawerContent = { 
                            DrawerContent(
                                currentScreen = currentScreen,
                                onNavigate = { screen ->
                                    currentScreen = screen
                                    scope.launch {
                                        drawerState.close()
                                    }
                                },
                                onAboutClick = {
                                    showAboutScreen = true
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
                                        IconButton(onClick = {
                                            scope.launch {
                                                drawerState.open()
                                            }
                                        }) {
                                            Icon(
                                                imageVector = Icons.Filled.Menu,
                                                contentDescription = "Menu"
                                            )
                                        }
                                    }
                                )
                            }
                        ) {
                            Box {
                                AnimatedContent(
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
                                ) {
                                    when (currentScreen) {
                                        Screen.MONITOR -> {
                                            MonitorScreen(
                                                viewModel = monitorViewModel,
                                                paddingValues = it
                                            )
                                        }
                                        Screen.MODULES -> {
                                            ModulesScreen(
                                                viewModel = modulesViewModel,
                                                paddingValues = it
                                            )
                                        }
                                        Screen.AUTOMATION -> {
                                            AutomationScreen(
                                                paddingValues = it
                                            )
                                        }
                                        Screen.SETTINGS -> {
                                            SettingsScreen(
                                                viewModel = settingsViewModel,
                                                onUnbindSuccess = {
                                                    // Return to connection screen
                                                },
                                                paddingValues = it
                                            )
                                        }
                                    }
                                }

                                // About screen
                                if (showAboutScreen) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .align(Alignment.Center)
                                    ) {
                                        AboutScreen(
                                            onClose = {
                                                showAboutScreen = false
                                            }
                                        )
                                    }
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

@Composable
fun isTabletDevice(): Boolean {
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp
    val screenHeightDp = configuration.screenHeightDp
    val smallestWidthDp = minOf(screenWidthDp, screenHeightDp)
    return smallestWidthDp >= 600
}

@Composable
fun DrawerContent(
    currentScreen: Screen,
    onNavigate: (Screen) -> Unit,
    onAboutClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "LucidDreaming",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        Screen.values().forEach {
            NavigationDrawerItem(
                label = { Text(it.title) },
                selected = currentScreen == it,
                onClick = { onNavigate(it) },
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        NavigationDrawerItem(
            label = { Text("About") },
            selected = false,
            onClick = onAboutClick,
            modifier = Modifier.padding(vertical = 4.dp)
        )
    }
}

enum class Screen(val title: String) {
    MONITOR("Monitor"),
    MODULES("Modules"),
    AUTOMATION("Automation"),
    SETTINGS("Settings")
}
