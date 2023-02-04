@file:OptIn(ExperimentalMaterial3Api::class)

package io.github.lexadiky.pdx.lib.navigation.fsdialog

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

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