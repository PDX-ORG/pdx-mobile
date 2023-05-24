package io.github.lexadiky.pdx.lib.navigation

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.SolidColor
import io.github.lexadiky.akore.lechuck.robo.fsdialog.FullScreenDialogStyle
import io.github.lexadiky.pdx.ui.uikit.theme.animation

object FullScreenDialogStyles {

    @Composable
    fun circularExpansion(): FullScreenDialogStyle.CircleExpansion {
        val background = MaterialTheme.colorScheme.primaryContainer

        return remember {
            FullScreenDialogStyle.CircleExpansion(
                backgroundBrush = SolidColor(background),
                animationSpec = MaterialTheme.animation.linearSlow()
            )
        }
    }
}
