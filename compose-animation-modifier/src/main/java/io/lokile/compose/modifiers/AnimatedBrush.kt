package io.lokile.compose.modifiers

import android.graphics.Matrix
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.ShaderBrush

internal abstract class AnimatedBrush : ShaderBrush() {
    private var internalShader: Shader? = null
    private val localMatrix: Matrix = Matrix()

    override fun createShader(size: Size): Shader {
        return newShader(size).apply {
            internalShader = this
            setLocalMatrix(localMatrix)
        }
    }

    abstract fun newShader(size: Size): Shader

    abstract fun updateAnimation(
        size: Size,
        current: Float
    )

    @Composable
    abstract fun animatedValue(): Float

    protected fun transform(transformer: Matrix.() -> Unit) {
        transformer.invoke(localMatrix)
        internalShader?.setLocalMatrix(localMatrix)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AnimatedBrush) return false
        if (localMatrix != other.localMatrix) return false
        return true
    }

    override fun hashCode(): Int {
        return 31 + (internalShader?.hashCode() ?: 0) + localMatrix.hashCode()
    }
}