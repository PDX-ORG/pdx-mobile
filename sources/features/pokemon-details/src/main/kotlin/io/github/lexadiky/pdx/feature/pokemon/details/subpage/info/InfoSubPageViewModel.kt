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
import io.github.lexadiky.pdx.lib.errorhandler.UIError
import io.github.lexadiky.pdx.lib.resources.string.StringResource
import io.github.lexadiky.pdx.lib.resources.string.format
import io.github.lexadiky.pdx.lib.resources.string.from
import kotlinx.coroutines.launch

internal class InfoSubPageViewModel(
    private val species: PokemonSpeciesDetails,
    private val pokemon: PokemonDetails,
    private val getPokemonPokedexDescriptions: GetPokemonPokedexDescriptions
) : ViewModel() {

    var state by mutableStateOf(InfoSubPageState())
        private set

    init {
        viewModelScope.launch {
            state = when (val data = getPokemonPokedexDescriptions(species.name)) {
                is Either.Left -> state.copy(error = UIError.generic())
                is Either.Right -> state.copy(
                    descriptions = data.value.toData(),
                    dimensions = extractDimensions(species, pokemon, true)
                )
            }
        }
    }

    fun hideError() {
        state = state.copy(error = null)
    }

    private fun List<PokemonPokedexDescription>.toData(): List<PokemonDescriptionData> {
        return groupBy { it.text }.map { (text, fullDescription) ->
            val title = when (fullDescription.size) {
                1 -> StringResource.from(fullDescription.first().gameVersion.localeName)
                2 -> StringResource.from(R.string.feature_pokemon_info_section_description_title_joiner_2)
                    .format(fullDescription.first().gameVersion.localeName, fullDescription.last().gameVersion.localeName)
                else -> StringResource.from(R.string.feature_pokemon_info_section_description_title_joiner_x)
                    .format(fullDescription.first().gameVersion.localeName, fullDescription.last().gameVersion.localeName)
            }

            PokemonDescriptionData(
                title = title,
                text = StringResource.from(text)
            )
        }
    }
}
