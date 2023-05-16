package io.github.lexadiky.pdx

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import io.github.lexadiky.akore.alice.robo.DIApplication
import io.github.lexadiky.akore.alice.robo.DIFeature
import io.github.lexadiky.akore.lechuck.robo.LocalComposeNavigationContext
import io.github.lexadiky.akore.lechuck.robo.NavigationFeature
import io.github.lexadiky.akore.lechuck.robo.NavigationHost
import io.github.lexadiky.pdx.feature.drawer.Drawer
import io.github.lexadiky.pdx.feature.rateapp.RateAppDialog
import io.github.lexadiky.pdx.feature.toolbar.Toolbar
import io.github.lexadiky.pdx.feature.toolbar.rememberToolbarConnector
import io.github.lexadiky.pdx.lib.navigation.NavigationHostStyles
import io.github.lexadiky.pdx.lib.navigation.NavigationModule
import io.github.lexadiky.pdx.lib.system.SystemModule
import io.github.lexadiky.pdx.ui.uikit.theme.PdxTheme
import io.github.lexadiky.pdx.ui.uikit.widget.scaffold.PdxScaffold
import kotlin.time.Duration.Companion.milliseconds
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        val application = application as PdxApplication

        setContent {
            DIApplication(application.diContainer) {
                Content()
            }
        }
    }

    @Composable
    private fun Content() {
        PdxTheme {
            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            val toolbarConnector = rememberToolbarConnector(
                onDrawerToggled = { if (drawerState.isOpen) drawerState.close() else drawerState.open() }
            )

            NavigationFeature(
                startDestination = "pdx://home",
                style = NavigationHostStyles.default()
            ) {
                val navigator = LocalComposeNavigationContext.current.navigator

                LaunchedEffect(Unit) {
                    navigator.currentRoute
                        .collectLatest {
                            delay(DRAWER_CLOSE_DELAY.milliseconds)
                            drawerState.close()
                        }
                }

                DIFeature(
                    NavigationModule(LocalComposeNavigationContext.current),
                    SystemModule(this@MainActivity)
                ) {
                    RateAppDialog()
                    PdxScaffold(
                        drawerState = drawerState,
                        drawerContent = { Drawer() },
                        topBar = { Toolbar(toolbarConnector) },
                        content = { paddingValues ->
                            Box(modifier = Modifier.padding(paddingValues)) {
                                NavigationHost(routing())
                            }
                        }
                    )
                }
            }
        }
    }

    companion object {

        private const val DRAWER_CLOSE_DELAY = 200
    }
}
