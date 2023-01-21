@file:OptIn(ExperimentalMaterial3Api::class)

package io.github.lexadiky.pdx.feature.toolbar

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.AssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.lexadiky.pdx.lib.arc.di.DIFeature
import io.github.lexadiky.pdx.lib.arc.di.di
import io.github.lexadiky.pdx.lib.navigation.decoration.DecorationHost
import io.github.lexadiky.pdx.ui.uikit.resources.render
import io.github.lexadiky.pdx.ui.uikit.util.scroll.LocalPrimeScrollState
import io.github.lexadiky.pdx.ui.uikit.util.scroll.asTopAppBarState
import kotlinx.coroutines.launch

@Composable
fun Toolbar(connector: ToolbarConnector) {
    DIFeature(ToolbarModule) {
        ToolbarImpl(viewModel = di.inject(connector))
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
                    text = viewModel.state.title.render().value
                )
            }
        },
        navigationIcon = {
            IconButton(
                onClick = { scope.launch { viewModel.openDrawer() } },
                content = { Icon(Icons.Default.Menu, null) }
            )
        },
        actions = {
            DecorationHost("pdx://toolbar/actions") {
                // nothing
            }
        }
    )
}