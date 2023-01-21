@file:OptIn(KoinInternalApi::class)

package io.github.lexadiky.pdx.lib.arc.di

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import org.koin.androidx.compose.koinViewModel
import org.koin.androidx.viewmodel.resolveViewModel
import org.koin.core.KoinApplication
import org.koin.core.annotation.KoinInternalApi
import org.koin.core.parameter.parametersOf
import org.koin.dsl.koinApplication

class DIContainer(@PublishedApi internal val application: KoinApplication) {

    private var registeredModules: HashSet<String> = HashSet()

    constructor(vararg modules: DIModule) : this(koinApplication {
        modules(modules.map { it.koinModule })
    })

    internal fun registerFeature(modules: Array<out DIModule>) {
        val newModules = modules.filter { module -> module.name !in registeredModules }
        newModules.forEach { module ->
                registeredModules += module.name
        }
        application.modules(newModules.map { it.koinModule })
    }

    @Composable
    inline fun <reified T> inject(): T {
        return remember {
            application.koin.get()
        }
    }

    @Composable
    inline fun <reified T : Any> inject(vararg paramerers: Any): T {
        return remember {
            application.koin.get {
                parametersOf(*paramerers)
            }
        }
    }

    @Composable
    inline fun <reified T : ViewModel> viewModel(key: String, vararg paramerers: Any): T {
        return koinViewModel(
            scope = application.koin.scopeRegistry.rootScope,
            key = key,
            parameters = { parametersOf(*paramerers) }
        )
    }

    inline fun <reified T : Any> lookup(): T {
        return application.koin.get()
    }
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