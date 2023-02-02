package io.github.lexadiky.pdx.lib.navigation.fsdialog

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Brush
import io.github.lexadiky.pdx.ui.uikit.theme.grid

sealed interface FullScreenDialogStyle {

    class CircleExpansion(
        val backgroundBrush: Brush,
        val animationSpec: AnimationSpec<Float>,
    ) : FullScreenDialogStyle

    companion object {

        @Composable
        fun expandingCircle(): CircleExpansion {
            val colorScheme = MaterialTheme.colorScheme

            return remember {
                CircleExpansion(
                    backgroundBrush = Brush.linearGradient(
                        colors = listOf(
                            colorScheme.primaryContainer,
                            colorScheme.background
                        )
                    ),
                    animationSpec = tween(durationMillis = 1500)
                )
            }
        }
    }
}