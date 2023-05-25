@file:OptIn(ExperimentalMaterial3Api::class)

package io.github.lexadiky.pdx.feature.toolbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import io.github.lexadiky.akore.alice.robo.DIFeature
import io.github.lexadiky.akore.alice.robo.di
import io.github.lexadiky.akore.alice.robo.viewModel
import io.github.lexadiky.akore.lechuck.robo.decoration.DecorationHost
import io.github.lexadiky.pdx.library.uikit.resources.render
import io.github.lexadiky.pdx.library.uikit.util.scroll.LocalPrimeScrollState
import io.github.lexadiky.pdx.library.uikit.util.scroll.asTopAppBarState
import kotlinx.coroutines.launch

@Composable
fun Toolbar(connector: ToolbarConnector) {
    DIFeature(ToolbarModule) {
        ToolbarImpl(viewModel = di.viewModel(connector))
    }
}

@Composable
internal fun ToolbarImpl(viewModel: ToolbarViewModel) {
    val scope = rememberCoroutineScope()
    val topAppBarState = LocalPrimeScrollState.current.asTopAppBarState()

    TopAppBar(
        scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(topAppBarState),
        title = {
            DecorationHost(decoration = "pdx://toolbar/title") {
                Text(
                    text = viewModel.state.title.render()
                )
            }
        },
        navigationIcon = {
            IconButton(
                onClick = { scope.launch { viewModel.openDrawer() } },
                content = { Icon(Icons.Default.Menu, null) }
            )
        }
    )
}
