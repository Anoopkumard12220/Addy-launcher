package com.addy.launcher.search

import com.addy.launcher.apps.AppInfo

data class SearchState(
    val query: String = "",
    val results: List<AppInfo> = emptyList(),
    val isEmpty: Boolean = true
)
