@file:OptIn(ExperimentalMaterial3Api::class)

package io.github.lexadiky.pdx

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import androidx.core.view.WindowCompat
import io.github.lexadiky.akore.alice.robo.DIApplication
import io.github.lexadiky.akore.alice.robo.DIFeature
import io.github.lexadiky.akore.alice.robo.di
import io.github.lexadiky.akore.alice.robo.inject
import io.github.lexadiky.akore.lechuck.Navigator
import io.github.lexadiky.akore.lechuck.robo.LocalComposeNavigationContext
import io.github.lexadiky.akore.lechuck.robo.NavigationFeature
import io.github.lexadiky.akore.lechuck.robo.NavigationHost
import io.github.lexadiky.akore.lechuck.utils.navigate
import io.github.lexadiky.pdx.library.navigation.NavigationHostStyles
import io.github.lexadiky.pdx.library.navigation.NavigationModule
import io.github.lexadiky.pdx.library.system.SystemModule
import io.github.lexadiky.pdx.library.uikit.theme.PdxTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        val application = application as PdxApplication

        setContent {
            DIApplication(application.diContainer) {
                DIFeature(SystemModule(this)) {
                    Content()
                }
            }
        }
    }

    @Composable
    private fun Content() {
        PdxTheme {
            NavigationFeature(
                startDestination = "pdx://type",
                style = NavigationHostStyles.default()
            ) {
                val navigator = di.inject<Navigator>()
                val scope = rememberCoroutineScope()

                Column {
                    TopAppBar(
                        title = { Text(stringResource(id = R.string.app_toolbar_title)) },
                        navigationIcon = {
                            IconButton(
                                onClick = {
                                    scope.launch {
                                        navigator.navigate("pdx://settings")
                                    }
                                }
                            ) {
                                Icon(Icons.Default.Settings, null)
                            }
                        }
                    )
                    DIFeature(NavigationModule(LocalComposeNavigationContext.current)) {
                        NavigationHost(routing())
                    }
                }
            }
        }
    }
}
