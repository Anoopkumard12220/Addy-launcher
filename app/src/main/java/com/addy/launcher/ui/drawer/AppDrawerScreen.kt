package com.addy.launcher.ui.drawer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.addy.launcher.apps.AppInfo
import com.addy.launcher.utils.AppIcon

@Composable
fun AppDrawerScreen(
    apps: List<AppInfo> = emptyList(),
    onAppClick: (AppInfo) -> Unit = {},
    onClose: () -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        TopAppBar(
            title = { Text(text = "Apps", style = MaterialTheme.typography.titleLarge) },
            navigationIcon = {
                IconButton(onClick = onClose) {
                    Icon(imageVector = androidx.compose.material3.Icons.Default.ArrowBack, contentDescription = "Close")
                }
            },
            colors = androidx.compose.material3.TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
            )
        )
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            columns = GridCells.Fixed(5),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(apps) { app ->
                androidx.compose.material3.Box(modifier = Modifier.size(80.dp)) {
                    AppIcon(packageName = app.packageName, size = 48.dp)
                    Text(
                        text = app.label,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.align(androidx.compose.ui.Alignment.BottomCenter).padding(top = 4.dp),
                        maxLines = 1
                    )
                }.clickable { onAppClick(app) }
            }
        }
    }
}
