package io.github.lexadiky.akore.lechuck.robo

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.OnBackPressedDispatcher
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import io.github.lexadiky.akore.blogger.BLogger
import io.github.lexadiky.akore.blogger.error
import io.github.lexadiky.akore.blogger.info
import io.github.lexadiky.akore.lechuck.NavigationRoute
import io.github.lexadiky.akore.lechuck.Navigator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class RoboNavigator internal constructor(
    private val context: Context,
    internal val controller: NavHostController,
    private val navGraph: NavGraph,
) : Navigator {
    private var latestNavigatedRouteExpression: String? = navGraph.startDestinationRoute
    private val mutex = Mutex()

    override val currentRoute: MutableStateFlow<NavigationRoute?> = MutableStateFlow(null)

    init {
        controller.addOnDestinationChangedListener { _, destination, bundle ->
            updateCurrentRoute(bundle, destination)
            latestNavigatedRouteExpression = null
        }
    }

    override suspend fun navigate(route: NavigationRoute) = mutex.withLock {
        if (route.isHttp) {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(route.asString())).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(browserIntent)
        } else {
            if (route.asString() == latestNavigatedRouteExpression) {
                return
            }
            controller.navigate(route = route.asString())
            latestNavigatedRouteExpression = route.asString()
        }
    }

    override suspend fun hasRoute(route: NavigationRoute): Boolean = mutex.withLock {
        return navGraph.findNode(route.asString()) != null
    }

    override suspend fun routes(): List<NavigationRoute> = mutex.withLock {
        return navGraph.toList()
            .mapNotNull { it.route }
            .map { NavigationRoute.from(it) }
    }

    private fun updateCurrentRoute(bundle: Bundle?, destination: NavDestination) {
        val preciseRote = (bundle?.get(ROUTE_INTENT_KEY) as? Intent)?.data
        if (preciseRote == null) {
            BLogger.tag("RoboNavigator")
                .error("can't determine precise destination")
            destination.route?.let { route ->
                currentRoute.value = NavigationRoute.from(route)
            }
        } else {
            currentRoute.value = NavigationRoute.from(preciseRote.toString())
        }
    }

    companion object {

        private const val ROUTE_INTENT_KEY = "android-support-nav:controller:deepLinkIntent"
    }
}
