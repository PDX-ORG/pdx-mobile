package io.github.lexadiky.akore.lechuck.utils

import io.github.lexadiky.akore.lechuck.NavigationRoute
import io.github.lexadiky.akore.lechuck.Navigator
import java.net.URI

suspend fun Navigator.navigate(uri: URI) {
    navigate(NavigationRoute.from(uri))
}

suspend fun Navigator.navigate(text: String) {
    navigate(NavigationRoute.from(text))
}
