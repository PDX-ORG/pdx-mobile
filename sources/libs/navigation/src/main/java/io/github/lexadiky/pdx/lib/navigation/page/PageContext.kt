package io.github.lexadiky.pdx.lib.navigation.page

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisallowComposableCalls
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.navigation.NavBackStackEntry
import io.github.lexadiky.pdx.lib.navigation.Navigator

class PageContext(
    @PublishedApi internal val backstackEntry: NavBackStackEntry,
) {

    @Composable
    inline fun argument(
        name: String,
        crossinline default: @DisallowComposableCalls () -> String
    ): String {
        return remember(name) {
            backstackEntry.arguments?.getString(name, null) ?: default()
        }
    }

}