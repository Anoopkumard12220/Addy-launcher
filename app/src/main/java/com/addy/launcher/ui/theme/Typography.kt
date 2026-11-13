package com.addy.launcher.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color

val Typography = Typography(
    displayLarge = TextStyle(fontSize = 64.sp, fontWeight = FontWeight.Normal),
    displayMedium = TextStyle(fontSize = 40.sp, fontWeight = FontWeight.Normal),
    titleLarge = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium),
    bodyLarge = TextStyle(fontSize = 18.sp),
    bodyMedium = TextStyle(fontSize = 16.sp),
    labelLarge = TextStyle(fontSize = 14.sp, color = Color(0xFF92939A)),
    labelMedium = TextStyle(fontSize = 12.sp, color = Color(0xFF92939A)),
    labelSmall = TextStyle(fontSize = 11.sp, color = Color(0xFF686970))
)
