package io.github.lexadiky.pdx.domain.pokemon.usecase

import arrow.core.Either
import io.github.lexadiky.pdx.domain.pokemon.entity.DiscoveryPokemonPreview
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonPreview
import io.github.lexadiky.pdx.lib.locale.LocaleManager

class FindPokemonPreviewUseCase(
    private val getPokemonPreviewUseCase: GetPokemonPreviewUseCase,
) {

    suspend operator fun invoke(pokemonId: String): Either<Error, PokemonPreview> =
        getPokemonPreviewUseCase().map { list -> list.first { it.name == pokemonId } }
            .mapLeft { Error }

    object Error
}