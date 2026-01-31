package com.luciddreaming.android.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.draw.scale
import androidx.compose.ui.Alignment

enum class Screen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    MONITOR("monitor", "监测", Icons.Default.Dashboard),
    MODULES("modules", "模块", Icons.Default.Extension),
    AUTOMATION("automation", "自动化", Icons.Default.Build),
    SETTINGS("settings", "设置", Icons.Default.Settings)
}

@Composable
fun SideNavDrawer(
    currentScreen: Screen,
    onNavigate: (Screen) -> Unit,
    onCloseDrawer: () -> Unit
) {
    ModalDrawerSheet(
        drawerContainerColor = MaterialTheme.colorScheme.surface,
        drawerTonalElevation = 8.dp
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        Screen.values().forEach { screen ->
            val isSelected = currentScreen == screen
            
            // 缩放动画
            val scale by animateFloatAsState(
                targetValue = if (isSelected) 1.05f else 1f,
                animationSpec = spring(
                    dampingRatio = 0.7f,
                    stiffness = 300f
                ),
                label = "iconScale"
            )
            
            NavigationDrawerItem(
                selected = isSelected,
                onClick = {
                    onNavigate(screen)
                    onCloseDrawer()
                },
                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = screen.title,
                        modifier = Modifier.scale(scale)
                    )
                },
                label = {
                    Text(screen.title)
                },
                colors = NavigationDrawerItemDefaults.colors(
                    selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                ),
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
            )
        }
    }
}

@Composable
fun DrawerButton(
    onOpenDrawer: () -> Unit
) {
    IconButton(onClick = onOpenDrawer) {
        Icon(
            imageVector = Icons.Default.Menu,
            contentDescription = "打开菜单"
        )
    }
}