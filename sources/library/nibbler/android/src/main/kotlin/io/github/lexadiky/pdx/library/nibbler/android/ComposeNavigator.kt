package io.github.lexadiky.pdx.library.nibbler.android

import androidx.navigation.NavController
import io.github.lexadiky.pdx.library.nibbler.Navigator
import io.github.lexadiky.pdx.library.nibbler.Route
import kotlinx.coroutines.flow.MutableStateFlow

class ComposeNavigator(
    private val composeNavigator: NavController
) : Navigator {

    override val currentRoute: MutableStateFlow<Route> = MutableStateFlow(Route.INDEX)

    override suspend fun navigate(route: Route) {
        currentRoute.value = route
    }
}
