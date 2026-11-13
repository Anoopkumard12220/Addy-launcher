package com.addy.launcher.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.longClickable
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Box
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.unit.dp
import com.addy.launcher.apps.AppInfo
import com.addy.launcher.ui.theme.AddyTheme
import com.addy.launcher.utils.AppIcon
import androidx.compose.material3.Icons.Default.Close

@Composable
fun AppGrid(
    apps: List<AppInfo>,
    iconSize: Dp = 56.dp,
    showLabels: Boolean = true,
    editMode: Boolean = false,
    onAppClick: (AppInfo) -> Unit = {},
    onAppLongClick: (AppInfo) -> Unit = {},
    onRemove: (AppInfo) -> Unit = {},
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(5),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        padding = PaddingValues(16.dp)
    ) {
        items(apps) { app ->
            AppShortcut(
                app = app,
                iconSize = iconSize,
                showLabels = showLabels,
                editMode = editMode,
                onClick = { onAppClick(app) },
                onLongClick = { onAppLongClick(app) },
                onRemove = onRemove
            )
        }
    }
}

@Composable
private fun AppShortcut(
    app: AppInfo,
    iconSize: Dp,
    showLabels: Boolean,
    editMode: Boolean,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
    onRemove: (AppInfo) -> Unit
) {
    val containerSize = with(AddyTheme) { iconSize + 12.dp }
    Box(
        modifier = Modifier
            .size(containerSize)
            .clipToBounds()
            .clickable(onClickLabel = app.label) { onClick() }
            .longClickable { onLongClick() },
        contentAlignment = Alignment.Center
    ) {
        AppIcon(packageName = app.packageName, size = iconSize)
        if (showLabels) {
            androidx.compose.foundation.layout.Row(
                modifier = Modifier
                    .align(androidx.compose.ui.Alignment.BottomCenter)
                    .padding(top = 4.dp),
                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.Center
            ) {
                Text(
                    text = app.label,
                    style = androidx.compose.material3.MaterialTheme.typography.labelSmall,
                    color = androidx.compose.material3.MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    modifier = androidx.compose.foundation.layout.width(48.dp)
                )
            }
        }
        if (editMode) {
            IconButton(onClick = { onRemove(app) }) {
                androidx.compose.material3.Icon(
                    imageVector = Close,
                    contentDescription = "Remove ${app.label}"
                )
            }
        }
    }
}
