package io.github.lexadiky.akore.alice

import org.koin.core.module.Module
import org.koin.core.parameter.ParametersHolder
import org.koin.core.scope.Scope
import org.koin.dsl.module

class DIModule(val name: String, internal val koinModule: Module)

class ModuleBuilder(val module: Module) {

    val integrityChecker = ModuleIntegrityChecker(
        isEnabled = true
    )

    var inInternalScope: Boolean = false

    fun import(other: DIModule) {
        if (inInternalScope) {
            throw IllegalStateException("imports in internal scope are not supported")
        }
        module.includes(other.koinModule)
    }

    inline fun <reified T: Any> single(crossinline provider: DIScope.(DIParametersHolder) -> T) {
        integrityChecker.check(cls = T::class, allowInternal = inInternalScope)

        module.single(definition = { params ->
            val diParametersHolder = DIParametersHolder(params)
            DIScope(this).provider(diParametersHolder)
        })
    }

    inline fun internal(scope: ModuleBuilder.() -> Unit) {
        inInternalScope = true
        scope()
        inInternalScope = false
    }

    @JvmInline
    value class DIScope(@PublishedApi internal val scope: Scope) {

        inline fun <reified T : Any> inject() = scope.get<T>()
    }

    @JvmInline
    value class DIParametersHolder(@PublishedApi internal val parameters: ParametersHolder) {

        inline fun <reified T : Any> get(): T = parameters.get()
    }
}

fun module(name: String, definition: ModuleBuilder.() -> Unit): Lazy<DIModule> {
    return lazy {
        DIModule(name, ModuleBuilder(module { }).apply(definition).module)
    }
}

fun eagerModule(name: String, definition: ModuleBuilder.() -> Unit): DIModule =
    DIModule(name, ModuleBuilder(module { }).apply(definition).module)
