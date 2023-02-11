package io.github.lexadiky.pdx.lib.navigation.decoration

import androidx.compose.runtime.Composable
import io.github.lexadiky.akore.alice.robo.di
import io.github.lexadiky.akore.alice.robo.inject
import io.github.lexadiky.pdx.lib.navigation.Navigator

@Composable
fun Decoration(decoration: String, content: @Composable () -> Unit) {
    val controller: DecorationController = di.inject()
    val navigator: Navigator = di.inject()
    controller.Render(decoration, navigator.currentRoute!!, content)
}

@Composable
fun DecorationHost(decoration: String, defaultContent: @Composable () -> Unit) {
    val controller: DecorationController = di.inject()
    controller.RegisterHost(
        decoration = decoration,
        defaultContent = defaultContent
    )
}
