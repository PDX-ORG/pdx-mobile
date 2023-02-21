package io.github.lexadiky.pdx.lib.navigation

import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.compose.runtime.derivedStateOf
import androidx.core.os.toPersistableBundle
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import io.github.lexadiky.pdx.generated.analytics.NavigationEventsSpec
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Suppress("RedundantSuspendModifier")
class Navigator internal constructor(
    private val context: Context,
    internal val controller: NavHostController,
    private val navGraph: NavGraph,
    private val navigationEventsSpec: NavigationEventsSpec,
) {

    val currentRoute: String? get() = controller.currentBackStackEntry?.destination?.route
    val currentAbsoluteRouteFlow: Flow<String?> get() = controller.currentBackStackEntryFlow
        .map { it.destination.route }
        .map(::convertRouteToAbsolute)

    suspend fun navigate(route: NavigationRoute) {
        navigationEventsSpec.devNavigationNavigate(route)
        if (route.startsWith(LINK_PREFIX_HTTPS) || route.startsWith(LINK_PREFIX_HTTP)) {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(route)).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(browserIntent)
        } else {
            controller.navigate(route = route)
        }
    }

    suspend fun hasRoute(route: NavigationRoute): Boolean {
        return navGraph.findNode(route) != null
    }

    fun listRoutes(): List<String> {
        return navGraph.toList()
            .mapNotNull { it.route }
    }

    private fun convertRouteToAbsolute(route: String?): String? =
        route?.split(QUERY_SEPARATOR)?.firstOrNull()

    companion object {

        private const val QUERY_SEPARATOR = "?"
        private const val LINK_PREFIX_HTTPS = "https://"
        private const val LINK_PREFIX_HTTP = "http://"
    }
}