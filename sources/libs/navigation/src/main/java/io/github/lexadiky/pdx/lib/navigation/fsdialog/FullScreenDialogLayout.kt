package io.github.lexadiky.pdx.lib.navigation.fsdialog

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