@file:OptIn(ExperimentalMaterialNavigationApi::class)

package io.github.lexadiky.pdx.library.nibbler.android.graph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.bottomSheet
import io.github.lexadiky.pdx.library.nibbler.android.PageContext

class RoutingBuilderContext(private val internal: NavGraphBuilder) {

    fun page(route: String, vararg alias: String, content: @Composable PageContext.() -> Unit) {
        internal.composable(route) { backstackEntry ->
            PageContentWrapper(backstackEntry, content)
        }
        alias.forEach { aliasRoute ->
            internal.composable(aliasRoute) { backstackEntry ->
                PageContentWrapper(backstackEntry, content)
            }
        }
    }

    fun modal(route: String, content: @Composable PageContext.() -> Unit) {
        internal.bottomSheet(route) { backstackEntry ->
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
}
