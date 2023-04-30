package io.github.lexadiky.pdx.feature.pokemon.details.subpage.info

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonDetails
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonPokedexDescription
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonSpeciesDetails
import io.github.lexadiky.pdx.domain.pokemon.usecase.GetPokemonPokedexDescriptions
import io.github.lexadiky.pdx.feature.pokemon.details.R
import io.github.lexadiky.pdx.feature.pokemon.details.entitiy.PokemonDescriptionData
import io.github.lexadiky.pdx.feature.pokemon.details.utils.extractDimensions
import io.github.lexadiky.pdx.lib.core.error.GenericError
import io.github.lexadiky.pdx.lib.core.lce.DynamicLceList
import io.github.lexadiky.pdx.lib.core.lce.mapLce
import io.github.lexadiky.pdx.lib.errorhandler.UIError
import io.github.lexadiky.pdx.lib.resources.string.StringResource
import io.github.lexadiky.pdx.lib.resources.string.format
import io.github.lexadiky.pdx.lib.resources.string.from
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

internal class InfoSubPageViewModel(
    private val species: PokemonSpeciesDetails,
    pokemon: PokemonDetails,
    private val getPokemonPokedexDescriptions: GetPokemonPokedexDescriptions
) : ViewModel() {

    var state by mutableStateOf(InfoSubPageState())
        private set

    init {
        state = state.copy(
            dimensions = extractDimensions(species, pokemon, true)
        )

        viewModelScope.launch {
            state = when (val data = getPokemonPokedexDescriptions(species.name)) {
                is Either.Left -> state.copy(error = UIError.generic())
                is Either.Right -> state.copy(
                    descriptions = data.value.toData(),
                )
            }
        }
    }

    fun hideError() {
        state = state.copy(error = null)
    }

    private fun DynamicLceList<GenericError, PokemonPokedexDescription>.toData(): DynamicLceList<GenericError, PokemonDescriptionData> {
        return map { list ->
            list.mapLce { description ->
                val gameVersions = description.gameVersions
                val title = when (gameVersions.size) {
                    0 -> StringResource.from(R.string.feature_pokemon_info_section_description_title_0)
                    1 -> StringResource.from(gameVersions.first().localeName)
                    2 -> StringResource.from(R.string.feature_pokemon_info_section_description_title_joiner_2)
                        .format(gameVersions.first().localeName, gameVersions.last().localeName)
                    else -> StringResource.from(R.string.feature_pokemon_info_section_description_title_joiner_x)
                        .format(gameVersions.first().localeName, gameVersions.last().localeName)
                }

                PokemonDescriptionData(
                    artificialId = gameVersions.map { it.localeName }
                        .toString(),
                    title = title,
                    text = StringResource.from(description.text)
                )
            }
        }
    }
}
