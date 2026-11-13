package com.addy.launcher.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.weight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.addy.launcher.apps.AppInfo
import com.addy.launcher.gestures.GestureHost
import com.addy.launcher.settings.LauncherSettings
import com.addy.launcher.ui.theme.AddyTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.Icons

@Composable
fun AddyHomeScreen(
    settings: LauncherSettings = LauncherSettings(),
    apps: List<AppInfo> = emptyList(),
    dockApps: List<AppInfo> = emptyList(),
    onAppClick: (AppInfo) -> Unit = {},
    onAppLongClick: (AppInfo) -> Unit = {},
    onRemove: (AppInfo) -> Unit = {},
    onDrawerRequested: () -> Unit = {},
    onSearchRequested: () -> Unit = {},
    onEditModeRequested: () -> Unit = {},
    onSettingsRequested: () -> Unit = {},
    onSwipeUp: () -> Unit = {},
    onSwipeDown: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .swipeDetector(onSwipeUp = onSwipeUp, onSwipeDown = onSwipeDown)
    ) {
        if (settings.clockVisible) {
            Clock(visible = true, format24 = settings.clock24Hour, showDate = settings.dateVisible)
            Spacer(modifier = Modifier.height(20.dp))
        }
        AppGrid(
            apps = apps,
            iconSize = settings.iconSizeDp.dp,
            showLabels = settings.showLabels,
            onAppClick = onAppClick,
            onAppLongClick = onAppLongClick,
            onRemove = onRemove
        )
        Spacer(modifier = Modifier.weight(1f))
        Dock(
            apps = dockApps,
            onAppClick = onAppClick,
            onSettings = onSettingsRequested
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "ADDY",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onSearchRequested) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                }
                IconButton(onClick = onDrawerRequested) {
                    Icon(imageVector = Icons.Default.GridOn, contentDescription = "App drawer")
                }
                IconButton(onClick = onEditModeRequested) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit mode")
                }
            }
        }
    }
}
