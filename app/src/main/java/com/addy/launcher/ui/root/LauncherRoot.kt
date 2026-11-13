package com.addy.launcher.ui.root

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.flow.collect
import androidx.compose.material3.Surface
import androidx.compose.material3.MaterialTheme
import com.addy.launcher.apps.AppInfo
import com.addy.launcher.apps.AppRepository
import com.addy.launcher.apps.PackageChangeReceiver
import com.addy.launcher.settings.LauncherSettings
import com.addy.launcher.settings.SettingsViewModel
import com.addy.launcher.ui.drawer.AppDrawerScreen
import com.addy.launcher.ui.edit.EditModeHost
import com.addy.launcher.ui.home.AddyHomeScreen
import com.addy.launcher.ui.search.SearchScreen
import com.addy.launcher.ui.settings.SettingsScreen

@Composable
fun LauncherRoot() {
    val context: Context = LocalContext.current
    val repository = remember { AppRepository(context) }
    val viewModel = remember { SettingsViewModel(context) }

    var drawerOpen by remember { mutableStateOf(false) }
    var searchOpen by remember { mutableStateOf(false) }
    var editMode by remember { mutableStateOf(false) }
    var settingsOpen by remember { mutableStateOf(false) }

    var apps by remember { mutableStateOf(emptyList<AppInfo>()) }
    var settings by remember { mutableStateOf(LauncherSettings()) }

    LaunchedEffect(repository) {
        repository.apps.collect { apps = it }
    }
    LaunchedEffect(Unit) {
        repository.refresh()
        PackageChangeReceiver.onPackagesChanged = { repository.refresh() }
    }

    LaunchedEffect(viewModel) {
        viewModel.settingsFlow().collect { settings = it }
    }

    val dockCount = if (apps.isEmpty()) 0 else settings.dockGridCount.coerceIn(1, apps.size)
    val dockApps = apps.take(dockCount)
    val gridApps = apps.subList(dockCount, apps.size)

    val launchApp: (AppInfo) -> Unit = { app ->
        context.startActivity(app.intent)
        drawerOpen = false
        searchOpen = false
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        if (settingsOpen) {
            SettingsScreen(
                settings = settings,
                apps = apps,
                viewModel = viewModel,
                onClose = { settingsOpen = false }
            )
        } else {
            AddyHomeScreen(
                settings = settings,
                apps = gridApps,
                dockApps = dockApps,
                onAppClick = launchApp,
                onAppLongClick = { /* handled by edit mode long-press */ },
                onRemove = { /* remove from home handled in edit mode */ },
                onDrawerRequested = { drawerOpen = true },
                onSearchRequested = { searchOpen = true },
                onEditModeRequested = { editMode = true },
                onSettingsRequested = { settingsOpen = true },
                onSwipeUp = { drawerOpen = true },
                onSwipeDown = { searchOpen = true }
            )
            if (drawerOpen) {
                AppDrawerScreen(apps = apps, onAppClick = launchApp, onClose = { drawerOpen = false })
            }
            if (searchOpen) {
                SearchScreen(apps = apps, onAppClick = launchApp, onClose = { searchOpen = false })
            }
            if (editMode) {
                EditModeHost(apps = apps, onClose = { editMode = false })
            }
        }
    }
}
