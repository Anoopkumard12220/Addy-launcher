package com.addy.launcher.utils

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.AndroidView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun AppIcon(
    packageName: String,
    modifier: Modifier = Modifier,
    size: Dp = 48.dp
) {
    val context = LocalContext.current
    val drawable = remember(context, packageName) { loadIcon(context, packageName) }
    AndroidView(
        modifier = modifier.size(size),
        factory = { context ->
            ImageView(context).apply {
                scaleType = ImageView.ScaleType.CENTER_INSIDE
                setImageDrawable(drawable)
            }
        },
        update = { view -> view.setImageDrawable(drawable) }
    )
}

private fun loadIcon(context: Context, packageName: String): Drawable {
    return try {
        context.packageManager.getApplicationIcon(packageName)
    } catch (_: PackageManager.NameNotFoundException) {
        context.getDrawable(android.R.drawable.ic_menu_info_details)
            ?: android.graphics.drawable.ColorDrawable(ContextCompat.getColor(context, android.R.color.darker_gray))
    }
}
