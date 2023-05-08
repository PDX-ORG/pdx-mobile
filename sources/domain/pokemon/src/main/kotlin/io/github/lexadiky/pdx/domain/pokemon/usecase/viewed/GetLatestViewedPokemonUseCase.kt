package io.github.lexadiky.pdx.domain.pokemon.usecase.viewed

import io.github.lexadiky.pdx.domain.pokemon.repository.ViewedPokemonRepository

class GetLatestViewedPokemonUseCase internal constructor(
    private val repository: ViewedPokemonRepository
) {
    suspend operator fun invoke(size: Int) = repository
        .latest(size)
        .mapLeft { Error }

    object Error
}
