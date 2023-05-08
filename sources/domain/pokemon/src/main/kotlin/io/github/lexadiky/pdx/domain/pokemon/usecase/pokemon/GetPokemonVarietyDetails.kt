package io.github.lexadiky.pdx.domain.pokemon.usecase.pokemon

import arrow.core.Either
import arrow.core.continuations.either
import io.github.lexadiky.akore.blogger.BLogger
import io.github.lexadiky.akore.blogger.error
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonDetails

class GetPokemonVarietyDetails(
    private val getPokemonDetails: GetPokemonSpeciesDetailsUseCase
) {

    suspend operator fun invoke(speciesId: String, varietyId: String): Either<Error, PokemonDetails> = either {
        val details = getPokemonDetails(speciesId)
            .mapLeft { Exception("can't load species") }
            .bind()

        val variety = Either.catch { details.varieties.first { it.name == varietyId } }
            .mapLeft { Exception("can't find variety") }
            .bind()

        variety
    }.mapLeft { error: Throwable ->
        BLogger.tag("GetPokemonVarietyDetails")
            .error("can't find specie's variety: $speciesId -> $varietyId", error)
        Error
    }

    object Error
}
