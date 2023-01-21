package io.github.lexadiky.pdx.lib.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraph
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import io.github.lexadiky.pdx.lib.navigation.page.PageContext

class PdxNavGraphBuilder(private val internal: NavGraphBuilder) {

    @Deprecated("exposes underlying framework")
    fun composable(route: String, content: @Composable (NavBackStackEntry) -> Unit) {
        internal.composable(route, content = content)
    }

    fun page(route: String, content: @Composable PageContext.() -> Unit) {
        internal.composable(route) { backstackEntry ->
            val context = remember { PageContext(backstackEntry) }
            context.content()
        }
    }

    fun build(): NavGraph = internal.build()
}