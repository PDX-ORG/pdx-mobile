package io.github.lexadiky.pdx.lib.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModelStore
import androidx.navigation.NavGraph
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import io.github.lexadiky.pdx.lib.arc.di.DIFeature
import io.github.lexadiky.pdx.lib.arc.di.di

private const val START_DESTINATION = "pdx://pokemon"

object NavigationFeatureContext

@Composable
fun NavigationFeature(
    routing: PdxNavGraphBuilder.() -> Unit,
    startDestination: String = START_DESTINATION,
    content: @Composable NavigationFeatureContext.() -> Unit
) {
    val controller = rememberNavController()
    val navGraph = remember(controller, routing) {
        val builder = PdxNavGraphBuilder(
            internal = NavGraphBuilder(
                provider = controller.navigatorProvider,
                startDestination = startDestination,
                route = null
            )
        )
        builder.apply(routing)
        controller.setViewModelStore(ViewModelStore())
        builder.build()
    }

    DIFeature(NavigationModule(controller, navGraph)) {
        NavigationFeatureContext.content()
    }
}

@Composable
fun NavigationFeatureContext.NavigationHost() {
    val navController = di.inject<NavHostController>()
    val graph = di.inject<NavGraph>()
    NavHost(navController, graph)
}
