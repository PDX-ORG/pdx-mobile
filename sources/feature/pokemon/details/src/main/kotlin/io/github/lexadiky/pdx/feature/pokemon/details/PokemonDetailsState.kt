package io.github.lexadiky.pdx.feature.pokemon.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonDetails
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonSpeciesDetails
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonType
import io.github.lexadiky.pdx.feature.pokemon.details.entitiy.PokemonDetailsSection
import io.github.lexadiky.pdx.feature.pokemon.details.entitiy.PokemonPhysicalDimension
import io.github.lexadiky.pdx.feature.pokemon.details.utils.extractDimensions
import io.github.lexadiky.pdx.library.core.lce.Lce
import io.github.lexadiky.pdx.library.core.lce.contentOrNull
import io.github.lexadiky.pdx.library.errorhandler.UIError
import io.github.lexadiky.pdx.library.resources.image.ImageResource
import io.github.lexadiky.pdx.library.resources.image.from
import io.github.lexadiky.pdx.library.resources.image.placeholder
import io.github.lexadiky.pdx.library.resources.string.StringResource
import io.github.lexadiky.pdx.library.resources.string.from
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

internal data class PokemonDetailsState(
    val id: String,
    val pokemonSpeciesDetails: PokemonSpeciesDetails? = null,
    val varieties: ImmutableList<Lce<*, PokemonDetails>> = persistentListOf(),
    val selectedVariety: Lce<*, PokemonDetails> = Lce.Loading,
    val availableDetailsSections: ImmutableList<PokemonDetailsSection> = persistentListOf(),
    val error: UIError? = null,
    val isFavorite: Boolean = false,
    val isSpritesViewerEnabled: Boolean = false,
) {

    val isLoaded: Boolean = pokemonSpeciesDetails != null

    val availableVarieties: Int = pokemonSpeciesDetails?.availableVarietiesCount ?: 0

    val name: StringResource? = pokemonSpeciesDetails?.localeName
        ?.let { StringResource.from(it) }

    val types: List<PokemonType> = selectedVariety.contentOrNull()?.types.orEmpty()

    val dimensions: List<PokemonPhysicalDimension> = extractDimensions(
        pokemonSpeciesDetails,
        selectedVariety.contentOrNull(),
        false
    )

    @Composable
    fun image(index: Int): ImageResource = remember(index) {
        varieties.getOrNull(index)?.contentOrNull()?.sprites?.default
            ?.let { ImageResource.from(it) }
            ?: ImageResource.placeholder()
    }
}
