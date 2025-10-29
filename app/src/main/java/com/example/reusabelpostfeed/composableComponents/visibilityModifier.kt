package com.example.reusabelpostfeed.composableComponents

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import kotlin.math.max
import kotlin.math.min

@Composable
fun Modifier.detectVisibility(onVisibilityChanged: (Float) -> Unit): Modifier {
    val view = LocalView.current
    val density = LocalDensity.current
    var layoutCoordinates: LayoutCoordinates? by remember { mutableStateOf(null) }

    LaunchedEffect(layoutCoordinates) {
        snapshotFlow { layoutCoordinates?.boundsInWindow() }
            .collect { bounds ->
                bounds?.let {
                    val visibleRect = Rect(0f, 0f, view.width.toFloat(), view.height.toFloat())
                    val intersectionWidth = max(0f, min(it.right, visibleRect.right) - max(it.left, visibleRect.left))
                    val intersectionHeight = max(0f, min(it.bottom, visibleRect.bottom) - max(it.top, visibleRect.top))
                    val visibleArea = intersectionWidth * intersectionHeight
                    val totalArea = it.width * it.height
                    val visibilityPercent = if (totalArea > 0) visibleArea / totalArea else 0f
                    onVisibilityChanged(visibilityPercent)
                }
            }
    }

    return this.onGloballyPositioned { coords ->
        layoutCoordinates = coords
    }
}