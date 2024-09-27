package com.flexath.currencyapp.presentation.utils

import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.flexath.currencyapp.ui.theme.currencyColorScheme

fun Modifier.shimmerEffect() = composed {
    val transition = rememberInfiniteTransition(label = "shimmer")
    val alpha = transition.animateFloat(
        initialValue = 0.2f,
        targetValue = 0.9f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "shimmer"
    ).value

    background(
        color = MaterialTheme.currencyColorScheme.colorHint.copy(alpha = alpha)
    )
}

fun Modifier.skeletonEffect(
    animationSpec: InfiniteRepeatableSpec<Float> = infiniteRepeatable(
        animation = tween(
            durationMillis = 500,
            easing = LinearEasing,
            delayMillis = 100
        ),
        repeatMode = RepeatMode.Restart
    ),
    shimmerColors: List<Color> = listOf(
        Color(0xFFC3C3C3).copy(alpha = 0.2f),
        Color(0xFFC3C3C3).copy(alpha = 0.4f),
        Color(0xFFC3C3C3).copy(alpha = 0.2f)
    )
): Modifier = composed {
    var startOffsetX by remember { mutableFloatStateOf(0f) }
    val shimmerWidth = with(LocalDensity.current) { 100.dp.toPx() }

    val transition = rememberInfiniteTransition(label = "transition shimmer")
    startOffsetX = transition.animateFloat(
        initialValue = -shimmerWidth,
        targetValue = shimmerWidth,
        animationSpec = animationSpec, label = "transition shimmer"
    ).value

    background(
        brush = Brush.linearGradient(
            colors = shimmerColors,
            start = Offset(startOffsetX, 0f),
            end = Offset(startOffsetX + shimmerWidth, 0f)
        )
    )
}