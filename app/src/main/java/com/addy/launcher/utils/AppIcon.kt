package com.addy.launcher.utils

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toImageBitmap
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun AppIcon(
    packageName: String,
    modifier: Modifier = Modifier,
    size: Dp = 48.dp
) {
    val context = LocalContext.current
    val imageBitmap = remember(context, packageName) {
        loadIcon(context, packageName)?.toBitmap()?.toImageBitmap()
    }
    if (imageBitmap != null) {
        Image(
            bitmap = imageBitmap,
            modifier = modifier.size(size),
            contentDescription = null
        )
    }
}

private fun Drawable?.toBitmap(): Bitmap? {
    if (this == null) return null
    if (this is android.graphics.drawable.BitmapDrawable) return this.bitmap
    val bitmap = Bitmap.createBitmap(
        intrinsicWidth.coerceAtLeast(1),
        intrinsicHeight.coerceAtLeast(1),
        Bitmap.Config.ARGB_8888
    )
    val canvas = android.graphics.Canvas(bitmap)
    setBounds(0, 0, canvas.width, canvas.height)
    draw(canvas)
    return bitmap
}

private fun loadIcon(context: Context, packageName: String): Drawable? {
    return try {
        context.packageManager.getApplicationIcon(packageName)
    } catch (_: PackageManager.NameNotFoundException) {
        context.getDrawable(android.R.drawable.ic_menu_info_details)
    }
}
