package io.github.lexadiky.pdx.feature.pokemon.details.subpage.evolution

import androidx.lifecycle.viewModelScope
import arrow.core.Either
import io.github.lexadiky.akore.lechuck.Navigator
import io.github.lexadiky.akore.lechuck.utils.navigate
import io.github.lexadiky.pdx.domain.pokemon.entity.EvolutionNode
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonDetails
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonPreview
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonSpeciesDetails
import io.github.lexadiky.pdx.domain.pokemon.usecase.evolution.GetPokemonEvolutionDetailsUseCase
import io.github.lexadiky.pdx.feature.pokemon.details.subpage.evolution.entity.EvolutionLinkPokemonVR
import io.github.lexadiky.pdx.feature.pokemon.details.subpage.evolution.entity.EvolvesToVR
import io.github.lexadiky.pdx.library.arc.ViewModelSocket
import io.github.lexadiky.pdx.library.errorhandler.classify
import io.github.lexadiky.pdx.library.resources.image.ImageResource
import io.github.lexadiky.pdx.library.resources.image.from
import io.github.lexadiky.pdx.library.resources.image.orPlaceholder
import io.github.lexadiky.pdx.library.resources.string.StringResource
import io.github.lexadiky.pdx.library.resources.string.from
import kotlinx.coroutines.launch

@Suppress("UnusedPrivateMember") // TODO remove
internal class EvolutionSubPageSocket(
    private val pokemonSpeciesDetails: PokemonSpeciesDetails,
    pokemonDetails: PokemonDetails,
    getPokemonEvolutionDetails: GetPokemonEvolutionDetailsUseCase,
    private val navigator: Navigator
) : ViewModelSocket<EvolutionSubPageState, EvolutionSubPageAction>(EvolutionSubPageState()) {

    init {
        viewModelScope.launch {
            val data = getPokemonEvolutionDetails.invoke(pokemonSpeciesDetails.name)
                .classify(EvolutionSubPageSocket::class)
            state = when (data) {
                is Either.Left -> state.copy(error = data.value)
                is Either.Right -> data.value?.let { updateState(it) } ?: state
            }
        }
    }

    private fun updateState(node: EvolutionNode): EvolutionSubPageState {
        return state.copy(
            evolvesFrom = node.from?.let { variation ->
                EvolvesToVR(
                    pokemon = pokemonPreviewToVR(variation.species),
                    method = variation.method
                )
            },
            current = pokemonPreviewToVR(pokemonSpeciesDetails.asPreview()),
            evolvesTo = node.to.map {  variation ->
                EvolvesToVR(
                    pokemon = pokemonPreviewToVR(variation.species),
                    method = variation.method
                )
            }
        )
    }

    private fun pokemonPreviewToVR(species: PokemonPreview) =
        EvolutionLinkPokemonVR(
            speciesId = species.name,
            localName = StringResource.from(species.localeName),
            image = species.normalSprite?.let { ImageResource.from(it) }
                .orPlaceholder()
        )

    override suspend fun onAction(action: EvolutionSubPageAction) = when(action) {
        is EvolutionSubPageAction.Navigate.PokemonDetails -> navigateToPokemonDetails(action.speciesId)
    }

    private suspend fun navigateToPokemonDetails(speciesId: String) {
        navigator.navigate("pdx://pokemon/$speciesId")
    }
}
