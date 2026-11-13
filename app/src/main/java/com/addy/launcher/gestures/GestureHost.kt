package com.addy.launcher.gestures

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp

object GestureHost {

    fun Modifier.swipeDetector(
        onSwipeUp: () -> Unit,
        onSwipeDown: () -> Unit
    ): Modifier = this.pointerInput(Unit) {
        detectDragGestures(
            onDragStart = { _, _ -> },
            onDragEnd = { _, dragAmount ->
                val horizontal = abs(dragAmount.x)
                val vertical = abs(dragAmount.y)
                val threshold = 50.dp.toFloat()
                if (vertical > threshold && vertical > horizontal) {
                    if (dragAmount.y < 0) onSwipeUp() else onSwipeDown()
                }
            },
            onDragCancel = { }
        )
    }
}
