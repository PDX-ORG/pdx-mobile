package io.github.lexadiky.pdx.library.nibbler.decoration

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import io.github.lexadiky.akore.blogger.BLogger
import io.github.lexadiky.akore.blogger.info
import io.github.lexadiky.pdx.library.nibbler.NavigateCommand
import io.github.lexadiky.pdx.library.nibbler.Navigator
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf

private typealias NoArgComposable = @Composable () -> Unit

private val updateTicker: MutableStateFlow<Int> = MutableStateFlow(0)
private val contentCache: MutableMap<String, MutableMap<String, NoArgComposable>> = HashMap()

class DecorationViewModel(
    private val navigator: Navigator
) : ViewModel() {

    @Composable
    @OptIn(ExperimentalCoroutinesApi::class)
    fun content(route: String): Flow<NoArgComposable> = remember {
        updateTicker.flatMapConcat {
            val navigateCommand = navigator.navigateCommand.value
            if (navigateCommand is NavigateCommand.GoTo) {
                flowOf(
                    contentCache.get(route)
                        ?.get(navigateCommand.uri)
                        ?: getDefaultContentForRoute(route)
                )
            } else {
                emptyFlow()
            }
        }
    }

    fun setContent(route: String, content: NoArgComposable) {
        val navigateCommand = navigator.navigateCommand.value
        if (navigateCommand is NavigateCommand.GoTo) {
            contentCache.getOrPut(route) { HashMap() }
                .set(navigateCommand.uri, content)
            refresh()
        }
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
