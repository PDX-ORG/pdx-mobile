@file:OptIn(ExperimentalMaterialNavigationApi::class)

package io.github.lexadiky.akore.lechuck.robo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraph
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.bottomSheet
import io.github.lexadiky.akore.lechuck.robo.fsdialog.fullScreenDialog
import io.github.lexadiky.akore.lechuck.robo.page.PageContext

class NaviNavGraphBuilder(private val internal: NavGraphBuilder) {

    fun page(route: String, content: @Composable PageContext.() -> Unit) {
        internal.composable(route) { backstackEntry ->
            PageContentWrapper(backstackEntry, content)
        }
    }

    fun modal(route: String, content: @Composable PageContext.() -> Unit) {
        internal.bottomSheet(route) { backstackEntry ->
            PageContentWrapper(backstackEntry, content)
        }
    }

    fun fullScreen(route: String, content: @Composable PageContext.() -> Unit) {
        internal.fullScreenDialog(route) { backstackEntry ->
            PageContentWrapper(backstackEntry, content)
        }
    }

    @Composable
    private fun PageContentWrapper(
        backstackEntry: NavBackStackEntry,
        content: @Composable() (PageContext.() -> Unit),
    ) {
        val context = remember(backstackEntry) { PageContext(backstackEntry) }
        context.content()
    }

    fun build(): NavGraph = internal.build()
}
