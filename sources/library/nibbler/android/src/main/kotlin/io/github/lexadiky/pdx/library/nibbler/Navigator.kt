package io.github.lexadiky.pdx.library.nibbler

import java.net.URI
import kotlinx.coroutines.flow.StateFlow

interface Navigator {

    val navigateCommand: StateFlow<NavigateCommand>

    suspend fun navigate(route: Route)
}

suspend fun Navigator.navigate(route: String) {
    navigate(Route.from(route))
}

suspend fun Navigator.navigate(route: URI) {
    navigate(Route.from(route.toString()))
}
