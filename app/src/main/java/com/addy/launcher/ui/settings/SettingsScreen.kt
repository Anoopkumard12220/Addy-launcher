package com.addy.launcher.ui.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import com.addy.launcher.apps.AppInfo
import com.addy.launcher.settings.LauncherSettings
import com.addy.launcher.settings.SettingsViewModel
import com.addy.launcher.settings.LauncherDataStore.Clock24Hour
import com.addy.launcher.settings.LauncherDataStore.ClockVisible
import com.addy.launcher.settings.LauncherDataStore.DateVisible
import com.addy.launcher.settings.LauncherDataStore.DrawerGridCount
import com.addy.launcher.settings.LauncherDataStore.IconSizeDp
import com.addy.launcher.settings.LauncherDataStore.ShowLabels

@Composable
fun SettingsScreen(
    settings: LauncherSettings,
    apps: List<AppInfo> = emptyList(),
    viewModel: SettingsViewModel,
    onClose: () -> Unit
) {
    val scope = rememberCoroutineScope()
    var local by remember { mutableStateOf(settings) }

    LaunchedEffect(settings) {
        local = settings
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween
        ) {
            Text(text = "Settings", style = MaterialTheme.typography.titleLarge)
            IconButton(onClick = onClose) {
                Icon(imageVector = androidx.compose.material3.Icons.Default.Close, contentDescription = "Close")
            }
        }

        settingRow("Clock", local.clockVisible) { local.clockVisible = it }
        settingRow("Date", local.dateVisible) { local.dateVisible = it }
        settingRow("24-hour clock", local.clock24Hour) { local.clock24Hour = it }
        settingRow("Show labels", local.showLabels) { local.showLabels = it }

        divider()

        Text("Icon size", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Button(onClick = { local.iconSizeDp = 48 }) { Text("48 dp") }
        Button(onClick = { local.iconSizeDp = 56 }) { Text("56 dp") }
        Button(onClick = { local.iconSizeDp = 64 }) { Text("64 dp") }

        divider()

        Text("Drawer grid", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Button(onClick = { local.drawerGridCount = 4 }) { Text("4 x 4") }
        Button(onClick = { local.drawerGridCount = 5 }) { Text("5 x 5") }
        Button(onClick = { local.drawerGridCount = 6 }) { Text("6 x 6") }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                scope.launch {
                    viewModel.updateSettings { editor ->
                        editor[ClockVisible] = local.clockVisible
                        editor[DateVisible] = local.dateVisible
                        editor[Clock24Hour] = local.clock24Hour
                        editor[ShowLabels] = local.showLabels
                        editor[IconSizeDp] = local.iconSizeDp
                        editor[DrawerGridCount] = local.drawerGridCount
                    }
                }
                onClose()
            }
        ) { Text("Save") }
    }
}

@Composable
private fun settingRow(title: String, value: Boolean, onChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title, style = MaterialTheme.typography.bodyLarge)
        Switch(checked = value, onCheckedChange = onChange)
    }
}

@Composable
private fun divider() = Spacer(
    modifier = Modifier.fillMaxWidth()
        .height(1.dp)
        .padding(vertical = 12.dp)
)
