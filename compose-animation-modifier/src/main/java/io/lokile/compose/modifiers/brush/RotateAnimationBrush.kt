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
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.SweepGradientShader
import io.lokile.compose.modifiers.AnimatedBrush
import kotlin.time.Duration

internal class RotateAnimationBrush(val colors: List<Color>, val duration: Duration) :
    AnimatedBrush() {
    override fun newShader(size: Size): Shader {
        val length = colors.size.toFloat()
        val animatedColors = buildList {
            for (i in colors.indices) add(i / length to colors[i])
            add(1f to colors.first())
        }

        return SweepGradientShader(
            colorStops = animatedColors.map { it.first },
            colors = animatedColors.map { it.second },
            center = Offset(size.width / 2, size.height / 2),
        )
    }


    @Composable
    override fun animatedValue(): Float {
        val infinite = rememberInfiniteTransition()
        val current by infinite.animateFloat(
            initialValue = 0f,
            targetValue = 360f,
            animationSpec = infiniteRepeatable(
                animation = tween(duration.inWholeMilliseconds.toInt(), easing = LinearEasing),
                repeatMode = RepeatMode.Restart
            ),
        )
        return current
    }

    override fun updateAnimation(
        size: Size,
        current: Float
    ) {
        val px = size.width / 2
        val py = size.height / 2
        this@RotateAnimationBrush.transform {
            reset()
            preRotate(current, px, py)
        }
    }
}