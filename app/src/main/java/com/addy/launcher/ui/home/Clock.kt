package com.addy.launcher.ui.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import androidx.compose.material3.MaterialTheme

@Composable
fun Clock(
    modifier: Modifier = Modifier,
    visible: Boolean = true,
    format24: Boolean = true,
    showDate: Boolean = true
) {
    val time by remember { mutableStateOf(formatNow(format24)) }
    val date by remember { mutableStateOf(formatDate()) }

    LaunchedEffect(Unit) {
        val initial = System.currentTimeMillis()
        var delayMs = 1000 - (initial % 1000)
        if (delayMs <= 0) delayMs = 1000
        delay(delayMs.coerceAtLeast(10))
        while (true) {
            delay(1000)
            time.value = formatNow(format24)
            date.value = formatDate()
        }
    }

    if (!visible) return@Composable

    Column(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = time,
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        if (showDate) {
            Text(
                text = date,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

private fun formatNow(format24: Boolean): String =
    SimpleDateFormat(if (format24) "HH:mm" else "hh:mm a", Locale.getDefault()).format(Date())

private fun formatDate(): String =
    SimpleDateFormat("EEE, MMM d", Locale.getDefault()).format(Date())
