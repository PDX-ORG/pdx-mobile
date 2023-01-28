package io.github.lexadiky.akore.alice

import org.koin.core.KoinApplication
import org.koin.core.parameter.parametersOf
import org.koin.dsl.koinApplication

class DIContainer(val application: KoinApplication) {

    private var registeredModules: HashSet<String> = HashSet()

    constructor(vararg modules: DIModule) : this(koinApplication {
        modules(modules.map { it.koinModule })
    })

    fun registerFeature(modules: Array<out DIModule>) {
        val newModules = modules.filter { module -> module.name !in registeredModules }
        newModules.forEach { module ->
                registeredModules += module.name
        }
        application.modules(newModules.map { it.koinModule })
    }

    inline fun <reified T : Any> lookup(): T {
        return application.koin.get()
    }

    inline fun <reified T : Any> lookup(vararg parameters: Any): T {
        return application.koin.get {
            parametersOf(*parameters)
        }
    }
}
