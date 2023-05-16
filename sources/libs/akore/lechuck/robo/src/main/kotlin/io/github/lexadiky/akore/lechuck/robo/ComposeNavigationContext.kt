package io.github.lexadiky.akore.lechuck.robo

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import io.github.lexadiky.akore.lechuck.Navigator
import io.github.lexadiky.akore.lechuck.robo.decoration.DecorationController

class ComposeNavigationContext(
    val controller: NavHostController,
    context: Context,
    val startDestination: String
) {
    internal val roboNavigator: RoboNavigator = RoboNavigator(context, controller)

    val navigator: Navigator get() = roboNavigator
    val decorationController = DecorationController(roboNavigator)
}

val LocalComposeNavigationContext = compositionLocalOf<ComposeNavigationContext> {
    error("no ComposeNavigationContext available at current composition")
}

@Composable
fun WithLocalComposeNavigationContext(
    controller: NavHostController,
    startDestination: String,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val composeNavigationContext = remember(controller, content, startDestination) {
        ComposeNavigationContext(controller, context, startDestination)
    }
    CompositionLocalProvider(LocalComposeNavigationContext provides composeNavigationContext) {
        content()
    }
}
