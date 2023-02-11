@file:OptIn(ExperimentalMaterial3Api::class)

package io.github.lexadiky.pdx.lib.navigation.fsdialog

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.ExperimentalMaterial3Api
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
