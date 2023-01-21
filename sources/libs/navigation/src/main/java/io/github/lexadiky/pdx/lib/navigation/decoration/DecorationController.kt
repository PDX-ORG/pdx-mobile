package io.github.lexadiky.pdx.lib.navigation.decoration

import android.os.Bundle
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import androidx.navigation.get
import io.github.lexadiky.pdx.lib.navigation.Navigator

class DecorationController(
    private val navigator: Navigator
) : NavController.OnDestinationChangedListener {
    private val graphCache: HashMap<String, CachedRegistredHost> = HashMap()

    init {
        navigator.controller.addOnDestinationChangedListener(this)
    }

    override fun onDestinationChanged(controller: NavController, destination: NavDestination, arguments: Bundle?) {
        graphCache.values.forEach { host ->
            host.navController.navigate(START_DESTINATION)
        }
    }

    @Composable
    internal fun Render(decoration: String, route: String, content: @Composable () -> Unit) {
        val currentEntry by navigator.controller.currentBackStackEntryAsState()
        LaunchedEffect(decoration, route, currentEntry) {
            val host = graphCache[decoration] ?: return@LaunchedEffect
            val composeNavigator = host.navController.navigatorProvider[ComposeNavigator::class]
            val wrappedContent: @Composable (NavBackStackEntry) -> Unit = { content() }
            host.navController.graph.addDestination(
                ComposeNavigator.Destination(composeNavigator, wrappedContent).apply {
                    this.route = route
                }
            )
            host.navController.navigate(route)
        }
    }

    @Composable
    internal fun RegisterHost(decoration: String, defaultContent: @Composable () -> Unit) {
        val navController = rememberNavController()
        val graph = navController.createGraph(START_DESTINATION) {
            composable(START_DESTINATION) { defaultContent() }
        }
        NavHost(navController = navController, graph = graph)
        LaunchedEffect(decoration) {
            graphCache[decoration] = CachedRegistredHost(
                decoration = decoration,
                navController = navController
            )
        }
    }

    private data class CachedRegistredHost(
        val decoration: String,
        val navController: NavController
    )

    companion object {

        private const val START_DESTINATION = "pdx://_internal/decor/start"
    }
}
