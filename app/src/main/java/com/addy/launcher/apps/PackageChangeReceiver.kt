package com.addy.launcher.apps

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class PackageChangeReceiver : BroadcastReceiver() {

    companion object {
        var onPackagesChanged: (() -> Unit)? = null
    }

    override fun onReceive(context: Context, intent: Intent?) {
        onPackagesChanged?.invoke()
    }
}
