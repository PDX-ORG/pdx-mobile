package io.github.lexadiky.akore.lechuck.robo.decoration

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import io.github.lexadiky.akore.lechuck.robo.LocalComposeNavigationContext
import io.github.lexadiky.akore.lechuck.robo.RoboNavigator

@Composable
fun Decoration(decoration: String, content: @Composable () -> Unit) {
    val controller: DecorationController = LocalComposeNavigationContext.current.decorationController
    val navigator: RoboNavigator =  LocalComposeNavigationContext.current.roboNavigator
    val currentRoute = navigator.currentRoute.value
    controller.Render(decoration, currentRoute?.asString(), content)
}

@Composable
fun DecorationHost(decoration: String, defaultContent: @Composable () -> Unit) {
    val controller: DecorationController =  LocalComposeNavigationContext.current.decorationController
    controller.RegisterHost(
        decoration = decoration,
        defaultContent = defaultContent
    )
}
