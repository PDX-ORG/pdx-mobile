package io.github.lexadiky.akore.lechuck.robo.fsdialog

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.ui.graphics.Brush

sealed interface FullScreenDialogStyle {

    class CircleExpansion(
        val backgroundBrush: Brush,
        val animationSpec: AnimationSpec<Float>,
    ) : FullScreenDialogStyle
}
