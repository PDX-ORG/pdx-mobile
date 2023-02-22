package io.github.lexadiky.pdx.ui.uikit.theme

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.unit.Dp

@Suppress("MagicNumber")
object PdxAnimation {

    fun <T> overShootFast() = tween<T>(250, easing = {
        OvershootInterpolator().getInterpolation(it)
    })
    fun <T> linearSlow() = tween<T>(1000)
}

@Suppress("UnusedReceiverParameter")
val MaterialTheme.animation get() = PdxAnimation
