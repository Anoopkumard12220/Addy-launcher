package com.addy.launcher.apps

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AppRepository(private val context: Context) {

    private val packageManager: PackageManager = context.packageManager
    private val _apps = MutableStateFlow<List<AppInfo>>(emptyList())
    val apps: StateFlow<List<AppInfo>> = _apps.asStateFlow()

    fun refresh() {
        _apps.value = loadApps()
    }

    fun getApps(): List<AppInfo> = _apps.value

    private fun loadApps(): List<AppInfo> {
        val intent = Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_LAUNCHER)
        val resolveInfos = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)
        return resolveInfos.mapNotNull { resolveInfo ->
            val info = resolveInfo.activityInfo ?: return@mapNotNull null
            val component = ComponentName(info.packageName, info.name)
            val label = info.loadLabel(packageManager)?.toString() ?: info.packageName
            val launchIntent = Intent(Intent.ACTION_MAIN).apply {
                setComponent(component)
                addCategory(Intent.CATEGORY_LAUNCHER)
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED
            }
            AppInfo(info.packageName, label, launchIntent, component)
        }.sortedBy { it.label.lowercase() }
    }
}
