package io.github.lexadiky.pdx.target.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import io.github.lexadiky.akore.alice.robo.DIApplication
import io.github.lexadiky.pdx.feature.drawer.Drawer
import io.github.lexadiky.pdx.feature.rateapp.RateAppDialog
import io.github.lexadiky.pdx.feature.toolbar.Toolbar
import io.github.lexadiky.pdx.feature.toolbar.rememberToolbarConnector
import io.github.lexadiky.pdx.library.nibbler.android.NibblerRoot
import io.github.lexadiky.pdx.library.uikit.theme.PdxTheme
import io.github.lexadiky.pdx.library.uikit.widget.scaffold.PdxScaffold

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
            NibblerRoot(routing()) { page ->
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val toolbarConnector = rememberToolbarConnector(
                    onDrawerToggled = { if (drawerState.isOpen) drawerState.close() else drawerState.open() }
                )

                RateAppDialog()
                PdxScaffold(
                    drawerState = drawerState,
                    drawerContent = { Drawer() },
                    topBar = { Toolbar(toolbarConnector) },
                    content = { paddingValues ->
                        Box(modifier = Modifier.padding(paddingValues)) {
                            page()
                        }
                    }
                )
            }
        }
    }

    companion object {

        private const val DRAWER_CLOSE_DELAY = 200
    }
}
