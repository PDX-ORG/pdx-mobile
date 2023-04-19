package io.github.lexadiky.akore.lechuck.robo.page

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisallowComposableCalls
import androidx.compose.runtime.remember
import androidx.navigation.NavBackStackEntry

class PageContext(
    @PublishedApi internal val backstackEntry: NavBackStackEntry,
) {

    @Composable
    inline fun argument(
        name: String,
        crossinline default: @DisallowComposableCalls () -> String = { "$name is required" }
    ): String {
        return remember(name) {
            backstackEntry.arguments?.getString(name, null) ?: default()
        }
    }
}
