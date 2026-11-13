package com.addy.launcher.settings

import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.preferences.core.edit
import com.addy.launcher.settings.LauncherDataStore.Clock24Hour
import com.addy.launcher.settings.LauncherDataStore.ClockVisible
import com.addy.launcher.settings.LauncherDataStore.DateVisible
import com.addy.launcher.settings.LauncherDataStore.DrawerGridCount
import com.addy.launcher.settings.LauncherDataStore.DockGridCount
import com.addy.launcher.settings.LauncherDataStore.IconSizeDp
import com.addy.launcher.settings.LauncherDataStore.ShowLabels
import com.addy.launcher.settings.LauncherDataStore.SortByName
import com.addy.launcher.settings.LauncherDataStore.WallpaperUri
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

data class LauncherSettings(
    val clockVisible: Boolean = true,
    val dateVisible: Boolean = true,
    val clock24Hour: Boolean = true,
    val showLabels: Boolean = true,
    val iconSizeDp: Int = 56,
    val dockGridCount: Int = 5,
    val drawerGridCount: Int = 5,
    val sortByName: Boolean = true,
    val wallpaperUri: String? = null
)

class SettingsViewModel(private val context: Context) {

    private val dataStore = context.dataStore()

    suspend fun updateSettings(block: SharedPreferences.Editor -> Unit) {
        dataStore.edit(block)
    }

    fun settingsFlow(): Flow<LauncherSettings> = dataStore.data.map { prefs ->
        LauncherSettings(
            clockVisible = prefs[ClockVisible] ?: true,
            dateVisible = prefs[DateVisible] ?: true,
            clock24Hour = prefs[Clock24Hour] ?: true,
            showLabels = prefs[ShowLabels] ?: true,
            iconSizeDp = prefs[IconSizeDp] ?: 56,
            dockGridCount = prefs[DockGridCount] ?: 5,
            drawerGridCount = prefs[DrawerGridCount] ?: 5,
            sortByName = prefs[SortByName] ?: true,
            wallpaperUri = prefs[WallpaperUri]
        )
    }
}
