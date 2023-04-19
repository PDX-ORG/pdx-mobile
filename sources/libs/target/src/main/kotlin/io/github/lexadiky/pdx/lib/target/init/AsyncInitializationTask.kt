package io.github.lexadiky.pdx.lib.target.init

interface AsyncInitializationTask {

    val id: String

    val blocking: Boolean get() = false

    suspend fun run(context: ApplicationInitializerContext)
}
