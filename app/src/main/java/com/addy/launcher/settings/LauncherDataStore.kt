package com.addy.launcher.settings

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

object LauncherDataStore {
    private const val FILE_NAME = "addy.preferences"

    val ClockVisible = booleanPreferencesKey("clock_visible")
    val DateVisible = booleanPreferencesKey("date_visible")
    val Clock24Hour = booleanPreferencesKey("clock_24_hour")
    val ShowLabels = booleanPreferencesKey("show_labels")
    val IconSizeDp = intPreferencesKey("icon_size_dp")
    val DockGridCount = intPreferencesKey("dock_grid_count")
    val WallpaperUri = stringPreferencesKey("wallpaper_uri")
    val DrawerGridCount = intPreferencesKey("drawer_grid_count")
    val SortByName = booleanPreferencesKey("sort_by_name")

    fun Context.dataStore() = preferencesDataStore { FILE_NAME }
}
