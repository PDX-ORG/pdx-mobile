package io.github.lexadiky.akore.lechuck

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface Navigator {

    val currentRoute: StateFlow<NavigationRoute?>

    suspend fun navigate(route: NavigationRoute)

    suspend fun routes(): List<NavigationRoute>

    suspend fun hasRoute(route: NavigationRoute): Boolean
}
