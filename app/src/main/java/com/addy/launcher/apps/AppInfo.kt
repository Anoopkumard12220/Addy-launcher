package com.addy.launcher.apps

import android.content.ComponentName
import android.content.Intent

data class AppInfo(
    val packageName: String,
    val label: String,
    val intent: Intent,
    val component: ComponentName
)
