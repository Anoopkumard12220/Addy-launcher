package com.addy.launcher.search

import com.addy.launcher.apps.AppInfo

class SearchViewModel {

    fun filterApps(apps: List<AppInfo>, query: String): List<AppInfo> {
        if (query.isBlank()) return apps
        val q = query.lowercase()
        return apps.filter { it.label.lowercase().contains(q) }
    }
}
