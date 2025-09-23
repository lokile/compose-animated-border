package io.lokile.compose.modifiers

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.lokile.compose.modifiers.brush.RadialAnimationBrush
import io.lokile.compose.modifiers.brush.RotateAnimationBrush
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

@Composable
fun Modifier.animatedBorder(
    enabled: Boolean = true,
    shape: Shape = CircleShape,
    borderWidth: Dp = 1.dp,
    duration: Duration = 1.seconds,
    style: AnimatedStyle = AnimatedStyle.Gradient,
    colors: List<Color> = listOf(
        Color.Blue,
        Color.Red,
        Color.Green
    ),
): Modifier {
    if (!enabled) return this

    val brush = remember(colors) {
        when (style) {
            AnimatedStyle.Rotate -> RotateAnimationBrush(colors, duration)
            AnimatedStyle.Gradient -> RadialAnimationBrush(colors, duration)
        }
    }

    val animatedValue = brush.animatedValue()

    return this
        .padding(borderWidth / 2)
        .drawWithCache {
            val outline = shape.createOutline(size, layoutDirection, this)
            val style = Stroke(width = borderWidth.toPx())
            onDrawWithContent {
                brush.updateAnimation(
                    size = size,
                    current = animatedValue
                )

                drawOutline(
                    outline = outline,
                    brush = brush,
                    style = style
                )
                drawContent()
            }
        }
}

enum class AnimatedStyle {
    Rotate,
    Gradient
}