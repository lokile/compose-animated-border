package io.lokile.compose.modifiers.brush

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RadialGradientShader
import androidx.compose.ui.graphics.Shader
import io.lokile.compose.modifiers.AnimatedBrush
import kotlin.math.max
import kotlin.time.Duration

internal class RadialAnimationBrush(
    private val colors: List<Color>,
    private val duration: Duration
) : AnimatedBrush() {
    override fun newShader(size: Size): Shader {
        return RadialGradientShader(
            center = Offset(size.width / 2f, size.height / 2f),
            radius = max(size.width, size.height),
            colors = colors
        )
    }

    override fun updateAnimation(
        size: Size,
        current: Float
    ) {
        if (size.width == 0f) return

        val px = size.width / 2
        val py = size.height / 2
        val scale = current * 2
        this.transform {
            reset()
            preScale(scale / py * px, scale, px, py)
        }
    }

    @Composable
    override fun animatedValue(): Float {
        val infinite = rememberInfiniteTransition()
        val current by infinite.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(duration.inWholeMilliseconds.toInt(), easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            ),
        )
        return current
    }
}