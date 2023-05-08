package io.github.lexadiky.pdx.domain.pokemon.usecase.evolution

import arrow.core.Either
import arrow.core.continuations.either
import arrow.core.traverse
import io.github.lexadiky.pdx.domain.pokemon.entity.EvolutionNode
import io.github.lexadiky.pdx.domain.pokemon.usecase.pokemon.GetPokemonPreviewUseCase
import io.github.lexadiky.pdx.lib.core.error.GenericError
import io.lexadiky.pokeapi.PokeApiClient
import io.lexadiky.pokeapi.entity.common.ResourcePointer
import io.lexadiky.pokeapi.entity.evolution.EvolutionChain
import io.lexadiky.pokeapi.entity.pokemon.PokemonSpecies

class GetPokemonEvolutionDetails internal constructor(
    private val client: PokeApiClient,
    private val loadPreview: GetPokemonPreviewUseCase
) {

    suspend operator fun invoke(speciesId: String): Either<GenericError, EvolutionNode?> = either {
        val (parent, current) = fetchPokeApiData(speciesId).bind()
            ?: return@either null

        EvolutionNode(
            from = parent?.species?.let { loadVariation(it, current.evolutionDetails).bind() },
            to = current.evolvesTo.traverse { childLink ->
                loadVariation(childLink.species, childLink.evolutionDetails)
            }.bind()
        )
    }

    private suspend fun loadVariation(
        species: ResourcePointer<PokemonSpecies>,
        details: List<EvolutionChain.EvolutionDetails>
    ): Either<GenericError, EvolutionNode.Variation> = loadPreview(species.name!!)
        .map { preview ->
            EvolutionNode.Variation(
                species = preview
            )
        }

    private suspend fun fetchPokeApiData(speciesId: String): Either<GenericError, Pair<EvolutionChain.ChainLink?, EvolutionChain.ChainLink>?> =
        either {
            val species = client.pokemonSpecies.get(speciesId)
                .bind { GenericError("can't load pokemon species", it) }
            val evolutionChain = client.evolutionChain.get(species.evolutionChain)
                .bind { GenericError("can't load evolution chain", it) }
            findLinks(speciesId, evolutionChain.chain)
                ?: shift(GenericError.originate("can't find pokemon species in evolution chain"))
        }

    private fun findLinks(speciesId: String, root: EvolutionChain.ChainLink): Pair<EvolutionChain.ChainLink?, EvolutionChain.ChainLink>? {
        return if (root.species.name == speciesId) {
            null to root
        } else {
            root.evolvesTo
                .firstNotNullOfOrNull { findLinks(speciesId, it) }
                ?.let { root to it.second }
        }
    }
}
