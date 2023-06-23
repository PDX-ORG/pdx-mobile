package io.github.lexadiky.pdx.domain.pokemon.usecase

import arrow.core.Either
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonType
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonTypeDamageRelation
import io.github.lexadiky.pdx.library.core.error.GenericError
import io.github.lexadiky.pdx.library.core.resource.ResourceReader
import io.github.lexadiky.pdx.library.core.resource.read

class GetPokemonTypeDamageRelationsUseCase internal constructor(
    private val resourceReader: ResourceReader
) {

    suspend operator fun invoke(): Either<GenericError, Map<PokemonType, PokemonTypeDamageRelation>> =
        resourceReader.read("type_chart.json")
}
