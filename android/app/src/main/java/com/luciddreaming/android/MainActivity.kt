package com.luciddreaming.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
                    when (currentScreen) {
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