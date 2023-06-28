package io.github.lexadiky.pdx.domain.pokemon.usecase.evolution

import arrow.core.Either
import arrow.core.continuations.either
import io.github.lexadiky.pdx.domain.pokemon.entity.EvolutionNode
import io.github.lexadiky.pdx.domain.pokemon.entity.evolution.EvolutionMethod
import io.github.lexadiky.pdx.domain.pokemon.usecase.pokemon.GetPokemonPreviewUseCase
import io.github.lexadiky.pdx.library.core.error.GenericError
import io.lexadiky.pokeapi.PokeApiClient
import io.lexadiky.pokeapi.entity.evolution.EvolutionChain

class GetPokemonEvolutionDetailsUseCase internal constructor(
    private val client: PokeApiClient,
    private val loadPreview: GetPokemonPreviewUseCase,
) {

    suspend operator fun invoke(speciesId: String): Either<GenericError, EvolutionNode> = either {
        val species = client.pokemonSpecies.get(speciesId)
            .bind { GenericError("can't load pokemon species", it) }

        val evolutionChain = client.evolutionChain.get(species.evolutionChain)
            .bind { GenericError("can't load evolution chain", it) }

        val currentNode = findCurrentNode(evolutionChain.chain, speciesId)
            ?: shift(GenericError.originate("can't find pokemon in its own evolution chain"))

        EvolutionNode(
            from = evolvesFrom(evolutionChain.chain, currentNode).bind(),
            to = evolvesTo(currentNode).bind(),
            current = loadPreview(speciesId).bind()
        )
    }

    private fun findCurrentNode(chain: EvolutionChain.ChainLink, speciesId: String): EvolutionChain.ChainLink? {
        return if (chain.species.name == speciesId) {
            chain
        } else {
            chain.evolvesTo.firstNotNullOfOrNull { findCurrentNode(it, speciesId) }
        }
    }

    private suspend fun evolvesTo(currentNode: EvolutionChain.ChainLink): Either<GenericError, List<EvolutionNode.Variation>> = either {
        currentNode.evolvesTo.map { link ->
            EvolutionNode.Variation(
                species = loadPreview(link.species.name!!).bind(),
                method = extractEvolutionMethod(link.evolutionDetails)
            )
        }
    }

    private suspend fun evolvesFrom(
        rootNode: EvolutionChain.ChainLink,
        currentNode: EvolutionChain.ChainLink,
    ): Either<GenericError, EvolutionNode.Variation?> = either {
        val parentLink = findParentChainLink(rootNode, currentNode)
            ?: return@either null

        val preview = loadPreview(parentLink.species.name!!).bind()

        EvolutionNode.Variation(
            species = preview,
            method = extractEvolutionMethod(currentNode.evolutionDetails)
        )
    }

    private fun findParentChainLink(rootNode: EvolutionChain.ChainLink, currentNode: EvolutionChain.ChainLink): EvolutionChain.ChainLink? {
        if (rootNode == currentNode) {
            return null
        }

        for (childLink in rootNode.evolvesTo) {
            if (childLink == currentNode) {
                return rootNode
            }
        }

        return rootNode.evolvesTo.firstNotNullOfOrNull { findParentChainLink(it, currentNode) }
    }

    private fun extractEvolutionMethod(evolutionDetails: List<EvolutionChain.EvolutionDetails>): EvolutionMethod {
        if (evolutionDetails.isEmpty()) {
            return EvolutionMethod.Unknown
        }

        if (evolutionDetails.size == 1) {
            return extractEvolutionMethod(evolutionDetails.first())
        }

        val methods = evolutionDetails.map { extractEvolutionMethod(it) }
        return if (methods.size == 1) {
            methods.first()
        } else {
            EvolutionMethod.Or(methods)
        }

    }

    private fun extractEvolutionMethod(evolutionDetails: EvolutionChain.EvolutionDetails): EvolutionMethod {
        val methods = buildList<EvolutionMethod> {
            if (evolutionDetails.minLevel != null) {
                add(EvolutionMethod.LevelUp(evolutionDetails.minLevel!!))
            }
        }

        return when {
            methods.size == 1 -> methods.first()
            methods.isEmpty() -> EvolutionMethod.Unknown
            else -> EvolutionMethod.And(methods)
        }
    }
}
