package io.github.lexadiky.pdx.library.target.init.impl

import io.github.lexadiky.pdx.domain.pokemon.usecase.prefetch.PrefetchPokemonDataUseCase
import io.github.lexadiky.pdx.library.target.init.ApplicationInitializerContext
import io.github.lexadiky.pdx.library.target.init.AsyncInitializationTask
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PrefetchPokemonDataAsyncTask(
    private val prefetchPokemonData: PrefetchPokemonDataUseCase
) : AsyncInitializationTask {

    override val id: String = "prefetch-pokemon-data"
    override val blocking: Boolean = false

    override suspend fun run(context: ApplicationInitializerContext): Unit = withContext(Dispatchers.IO) {
        prefetchPokemonData()
    }
}
