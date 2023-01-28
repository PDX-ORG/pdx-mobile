@file:OptIn(KoinInternalApi::class)

package io.github.lexadiky.akore.alice.robo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import io.github.lexadiky.akore.alice.DIContainer
import io.github.lexadiky.akore.alice.DIModule
import org.koin.androidx.compose.koinViewModel
import org.koin.core.annotation.KoinInternalApi
import org.koin.core.parameter.parametersOf

@Composable
inline fun <reified T: Any> DIContainer.inject(): T = remember { lookup() }

@Composable
inline fun <reified T: Any> DIContainer.inject(vararg parameters: Any): T = remember(*parameters) {
    lookup(*parameters)
}

@Composable
inline fun <reified T : ViewModel> DIContainer.viewModel(key: String, vararg parameters: Any): T {
    return koinViewModel(
        scope = application.koin.scopeRegistry.rootScope,
        key = key,
        parameters = { parametersOf(*parameters) }
    )
}

private val LocalDIContainer = compositionLocalOf<DIContainer> {
    error("no di container attached")
}

@Composable
fun DIApplication(container: DIContainer, content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalDIContainer provides container) {
        content()
    }
}

@Composable
fun DIFeature(vararg includes: DIModule, content: @Composable () -> Unit) {
    val diContainer = LocalDIContainer.current
    diContainer.registerFeature(includes)
    content()
}

val di: DIContainer
    @Composable get() = LocalDIContainer.current