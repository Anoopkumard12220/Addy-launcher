package com.addy.launcher.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Box
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.addy.launcher.apps.AppInfo
import com.addy.launcher.ui.theme.AddyTheme
import com.addy.launcher.utils.AppIcon
import androidx.compose.material3.Icons

@Composable
fun Dock(
    apps: List<AppInfo>,
    iconSize: Dp = 44.dp,
    onAppClick: (AppInfo) -> Unit = {},
    onSettings: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        padding = PaddingValues(horizontal = 16.dp)
    ) {
        items(apps) { app ->
            Box(modifier = Modifier.size(64.dp), contentAlignment = Alignment.Center) {
                AppIcon(packageName = app.packageName, size = iconSize)
                Text(
                    text = app.label,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.align(Alignment.BottomCenter).padding(top = 4.dp),
                    maxLines = 1
                )
            }.clickable { onAppClick(app) }
        }
        IconButton(onClick = onSettings) {
            Icon(imageVector = Icons.Default.Settings, contentDescription = "Settings")
        }
    }
}
