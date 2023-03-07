package io.github.lexadiky.pdx.lib.target.init.impl

import io.github.lexadiky.pdx.domain.pokemon.usecase.prefetch.PrefetchPokemonData
import io.github.lexadiky.pdx.lib.target.init.ApplicationInitializerContext
import io.github.lexadiky.pdx.lib.target.init.AsyncInitializationTask
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PrefetchPokemonDataAsyncTask(
    private val prefetchPokemonData: PrefetchPokemonData
) : AsyncInitializationTask {

    override val id: String = "prefetch-pokemon-data"
    override val blocking: Boolean = false

    override suspend fun run(context: ApplicationInitializerContext): Unit = withContext(Dispatchers.IO) {
        prefetchPokemonData()
    }
}
