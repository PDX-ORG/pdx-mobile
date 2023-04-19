package io.github.lexadiky.akore.lechuck.robo.decoration

import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import io.github.lexadiky.akore.lechuck.robo.RoboNavigator
import kotlinx.coroutines.delay

class DecorationController(
    private val navigator: RoboNavigator
) {
    private val decorations: MutableMap<String, MutableMap<String, @Composable () -> Unit>> =
        HashMap()
    private var trigger by mutableStateOf(false)

    @Composable
    internal fun Render(decoration: String, route: String?, content: @Composable () -> Unit) {
        if (route == null) return
        val currentRoute by navigator.currentRoute.collectAsState()
        LaunchedEffect(decoration, route) {
            if (currentRoute?.asString() == route) {
                decorations.getOrPut(decoration) { HashMap() }
                    .put(route, content)
                trigger = !trigger
            }
        }
        DisposableEffect(decoration, route) {
            onDispose {
                decorations[decoration]?.remove(route)
            }
        }
    }

    @Composable
    internal fun RegisterHost(decoration: String, defaultContent: @Composable () -> Unit) {
        val currentRoute by navigator.currentRoute.collectAsState()
        if (currentRoute == null) return

        val content = remember(decoration, currentRoute, trigger) {
            decorations.getOrDefault(decoration, HashMap())
                .getOrDefault(currentRoute!!.asString(), defaultContent)
        }

        var contentHolder by remember { mutableStateOf(defaultContent) }
        LaunchedEffect(content, Unit) {
            delay(50)
            contentHolder = content
        }

        Crossfade(targetState = contentHolder) { ch ->
            ch.invoke()
        }
    }

    companion object {

        private const val DEFAULT_ROUTE = "__default__"
    }
}

