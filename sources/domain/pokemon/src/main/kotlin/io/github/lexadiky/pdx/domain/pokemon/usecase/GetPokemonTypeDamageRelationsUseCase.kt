package io.github.lexadiky.pdx.domain.pokemon.usecase

import arrow.core.Either
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonType
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonTypeDamageRelation
import io.github.lexadiky.pdx.library.core.error.GenericError
import io.github.lexadiky.pdx.library.fs.statist.StaticResourceProvider
import io.github.lexadiky.pdx.library.fs.statist.provide

class GetPokemonTypeDamageRelationsUseCase internal constructor(
    private val resourceProvider: StaticResourceProvider
) {

    suspend operator fun invoke(): Either<GenericError, Map<PokemonType, PokemonTypeDamageRelation>> =
        resourceProvider.provide<Map<PokemonType, PokemonTypeDamageRelation>>("bundle://type_chart.json").read()
            .mapLeft { GenericError("can't load pokemon type relations", it.reason) }
}
