@file:OptIn(ExperimentalMaterial3Api::class)

package io.github.lexadiky.pdx.library.uikit.widget.scaffold

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PdxScaffold(
    drawerState: DrawerState,
    drawerContent: @Composable ColumnScope.() -> Unit,
    topBar: @Composable () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet() {
                Column(
                    modifier = Modifier.padding(PdxScaffoldTokens.DrawerContentPadding.dp)
                ) {
                    drawerContent()
                }
            }
        },
        content = {
            Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                Scaffold(
                    topBar = { topBar() },
                    content = { padding -> content(padding) },
                )
            }
        }
    )
}

private object PdxScaffoldTokens {

    const val DrawerWidth = 0.75f // percents of screen
    const val DrawerContentPadding = 12
}
