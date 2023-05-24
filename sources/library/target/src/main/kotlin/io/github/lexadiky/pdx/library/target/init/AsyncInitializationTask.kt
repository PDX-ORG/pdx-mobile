package io.github.lexadiky.pdx.library.target.init

interface AsyncInitializationTask {

    val id: String

    val blocking: Boolean get() = false

    suspend fun run(context: ApplicationInitializerContext)
}
