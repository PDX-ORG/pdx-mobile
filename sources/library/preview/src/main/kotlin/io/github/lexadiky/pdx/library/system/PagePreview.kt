@file:OptIn(ExperimentalMaterial3Api::class)

package io.github.lexadiky.pdx.library.system

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import io.github.lexadiky.akore.alice.robo.DIApplication
import io.github.lexadiky.akore.lechuck.robo.NavigationFeature
import io.github.lexadiky.pdx.library.navigation.NavigationHostStyles
import io.github.lexadiky.pdx.library.target.InitialDIContainerBuilder
import io.github.lexadiky.pdx.library.uikit.theme.PdxTheme
import io.github.lexadiky.pdx.library.uikit.widget.scaffold.PdxScaffold

@Composable
fun PagePreview(
    title: String = "Preview",
    content: @Composable () -> Unit,
) {
    val context = LocalContext.current
    val diContainer = remember {
        InitialDIContainerBuilder()
            .build(context)
    }

    DIApplication(diContainer) {
        PdxTheme {
            NavigationFeature(
                startDestination = "pdx-preview://start",
                style = NavigationHostStyles.default(),
                content = {
                    PdxScaffold(
                        drawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
                        drawerContent = {},
                        topBar = {
                            TopAppBar(
                                title = { Text(title) },
                                navigationIcon = {
                                    IconButton(
                                        content = { Icon(Icons.Default.Menu, null) },
                                        onClick = {},
                                    )
                                }
                            )
                        },
                        content = { paddingValues ->
                            Box(
                                modifier = Modifier
                                    .padding(paddingValues)
                            ) {
                                content()
                            }
                        }
                    )
                }
            )
        }
    }
}
