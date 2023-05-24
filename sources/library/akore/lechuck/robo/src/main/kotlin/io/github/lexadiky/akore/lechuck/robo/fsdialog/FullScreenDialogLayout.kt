package io.github.lexadiky.akore.lechuck.robo.fsdialog

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable

@Composable
fun FullScreenDialogLayout(
    navigator: FullScreenDialogNavigator,
    content: @Composable () -> Unit,
) {
    Box {
        content()
        navigator.content()
    }
}
