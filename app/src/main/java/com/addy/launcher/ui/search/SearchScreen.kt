package com.addy.launcher.ui.search

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.addy.launcher.apps.AppInfo
import com.addy.launcher.search.SearchViewModel
import com.addy.launcher.utils.AppIcon
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.layout.Arrangement

@Composable
fun SearchScreen(
    apps: List<AppInfo> = emptyList(),
    onAppClick: (AppInfo) -> Unit = {},
    onClose: () -> Unit
) {
    val viewModel = remember { SearchViewModel() }
    var query by remember { mutableStateOf("") }
    val results = viewModel.filterApps(apps, query)

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        TopAppBar(
            title = {
                TextField(
                    value = query,
                    onValueChange = { query = it },
                    placeholder = { Text(text = "Search apps") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    colors = androidx.compose.material3.TextFieldDefaults.textFieldColors(
                        backgroundColor = MaterialTheme.colorScheme.surfaceContainerHighest
                    )
                )
            },
            navigationIcon = {
                IconButton(onClick = onClose) {
                    Icon(imageVector = androidx.compose.material3.Icons.Default.ArrowBack, contentDescription = "Close")
                }
            },
            colors = androidx.compose.material3.TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
            )
        )
        if (results.isEmpty() && query.isNotEmpty()) {
            androidx.compose.foundation.layout.Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = "No apps found",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = androidx.compose.foundation.layout.align(androidx.compose.ui.Alignment.Center)
                )
            }
        } else {
            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize().padding(PaddingValues(16.dp)),
                columns = GridCells.Fixed(5),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(results) { app ->
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
}
