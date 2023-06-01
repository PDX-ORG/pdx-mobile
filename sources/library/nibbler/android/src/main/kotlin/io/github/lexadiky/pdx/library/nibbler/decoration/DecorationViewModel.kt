package io.github.lexadiky.pdx.library.nibbler.decoration

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import io.github.lexadiky.akore.blogger.BLogger
import io.github.lexadiky.akore.blogger.info
import io.github.lexadiky.pdx.library.nibbler.Navigator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.zip

private typealias NoArgComposable = @Composable () -> Unit

class DecorationViewModel(
    private val navigator: Navigator
) : ViewModel() {

    private val updateTicker: MutableStateFlow<Int> = MutableStateFlow(0)
    private val contentCache: MutableMap<String, MutableMap<String, NoArgComposable>> = HashMap()

    @Composable
    fun content(route: String): Flow<NoArgComposable> = remember(route) {
        updateTicker.zip(navigator.currentRoute) { _, r -> r }
            .mapLatest { currentRoute ->
                contentCache.get(route)
                    ?.get(navigator.currentRoute.value.uri)
                    ?: getDefaultContentForRoute(route)
            }
    }

    fun setContent(route: String, content: NoArgComposable) {
        contentCache.getOrPut(route) { HashMap() }
            .set(navigator.currentRoute.value.uri, content)
        refresh()
    }

    fun setDefaultContentForRoute(route: String, defaultContent: NoArgComposable) {
        contentCache.getOrPut(route) { HashMap() }
            .set(route, defaultContent)
        refresh()
    }

    private fun getDefaultContentForRoute(route: String): NoArgComposable {
        return contentCache.get(route)?.get(route) ?: {}
    }

    private fun refresh() {
        BLogger.tag("DecorationViewModel")
            .info("refreshing")
        updateTicker.value = updateTicker.value + 1
    }
}
