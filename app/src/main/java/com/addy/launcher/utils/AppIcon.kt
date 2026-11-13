package com.addy.launcher.utils

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toImageBitmap
import androidx.compose.ui.unit.Dp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.foundation.Image

@Composable
fun AppIcon(
    packageName: String,
    modifier: Modifier = Modifier,
    size: Dp = 48.dp
) {
    val context = LocalContext.current
    val imageBitmap = remember(context, packageName) {
        loadIcon(context, packageName)?.toImageBitmap()
    }
    if (imageBitmap != null) {
        Image(
            imageBitmap = imageBitmap,
            modifier = modifier.size(size),
            contentDescription = null
        )
    }
}

private fun loadIcon(context: Context, packageName: String): Drawable? {
    return try {
        val drawable = context.packageManager.getApplicationIcon(packageName)
        if (drawable is BitmapDrawable) drawable else toBitmapDrawable(context, drawable)
    } catch (_: PackageManager.NameNotFoundException) {
        context.getDrawable(android.R.drawable.ic_menu_info_details)
    }
}

private fun toBitmapDrawable(context: Context, drawable: Drawable): BitmapDrawable {
    val bitmap = Bitmap.createBitmap(
        drawable.intrinsicWidth,
        drawable.intrinsicHeight,
        Bitmap.Config.ARGB_8888
    )
    val canvas = android.graphics.Canvas(bitmap)
    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.draw(canvas)
    return BitmapDrawable(context.resources, bitmap)
}
