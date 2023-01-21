package io.github.lexadiky.pdx.lib.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavGraph
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import io.github.lexadiky.pdx.lib.navigation.page.PageContext
import io.github.lexadiky.pdx.lib.navigation.util.registerPage404

class PdxNavGraphBuilder(private val internal: NavGraphBuilder) {

    init {
        internal.registerPage404()
    }

    fun page(route: String, content: @Composable PageContext.() -> Unit) {
        internal.composable(route) { backstackEntry ->
            val context = remember(backstackEntry) { PageContext(backstackEntry) }
            context.content()
        }
    }

    fun build(): NavGraph = internal.build()
}