package io.lokile.compose.animation.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.lokile.compose.modifiers.AnimatedStyle
import io.lokile.compose.modifiers.animatedBorder
import kotlin.time.Duration.Companion.seconds

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Column(
                Modifier
                    .safeDrawingPadding()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Hello(
                    modifier = Modifier.animatedBorder(
                        colors = listOf(
                            Color.Magenta,
                            Color.Cyan,
                            Color.Transparent
                        ),
                        duration = 2.seconds,
                        style = AnimatedStyle.Rotate
                    ),
                    message = "Colorful border"
                )
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Hello(
                        modifier = Modifier
                            .animatedBorder(
                                duration = 2.seconds,
                                borderWidth = 2.dp,
                                colors = listOf(
                                    Color.Blue.copy(alpha = 0.1f),
                                    Color.Blue.copy(alpha = 0.3f),
                                    Color.Blue.copy(alpha = 0.5f),
                                    Color.Blue.copy(alpha = 0.8f),
                                    Color.Transparent,
                                    Color.Transparent,
                                )
                            )
                            .size(120.dp),
                        message = "Loading..."
                    )
                    Hello(
                        modifier = Modifier
                            .animatedBorder(
                                duration = 2.seconds,
                                borderWidth = 2.dp,
                                colors = listOf(
                                    Color.Red.copy(alpha = 0.1f),
                                    Color.Green.copy(alpha = 0.3f),
                                    Color.Blue.copy(alpha = 0.5f),
                                    Color.Cyan.copy(alpha = 0.8f),
                                    Color.Transparent,
                                    Color.Transparent,
                                )
                            )
                            .size(120.dp),
                        message = "Colorful \nLoading..."
                    )
                }
            }
        }
    }

    @Composable
    private fun Hello(
        message: String,
        modifier: Modifier
    ) {
        Box(
            modifier = modifier.padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = message)
        }
    }
}