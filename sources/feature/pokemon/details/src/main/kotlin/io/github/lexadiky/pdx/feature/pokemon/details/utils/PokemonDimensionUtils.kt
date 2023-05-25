package io.github.lexadiky.pdx.feature.pokemon.details.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonDetails
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonSpeciesDetails
import io.github.lexadiky.pdx.feature.pokemon.details.entitiy.PokemonPhysicalDimension
import io.github.lexadiky.pdx.library.resources.image.ImageResource
import io.github.lexadiky.pdx.library.resources.image.from
import io.github.lexadiky.pdx.library.resources.string.StringResource
import io.github.lexadiky.pdx.library.resources.string.format
import io.github.lexadiky.pdx.library.resources.string.from
import io.github.lexadiky.pdx.library.uikit.UikitDrawable
import io.github.lexadiky.pdx.library.uikit.resources.from

internal fun extractDimensions(
    species: PokemonSpeciesDetails?,
    details: PokemonDetails?,
    addMythicalOrLegendaryInfo: Boolean,
): List<PokemonPhysicalDimension> = buildList {
    details ?: return@buildList
    species ?: return@buildList

    add(
        PokemonPhysicalDimension(
            icon = ImageResource.from(UikitDrawable.uikit_ic_height),
            label = StringResource.from(io.github.lexadiky.pdx.feature.pokemon.details.R.string.feature_pokemon_details_dimension_height)
                .format(details.height)
        )
    )
    add(
        PokemonPhysicalDimension(
            icon = ImageResource.from(UikitDrawable.uikit_ic_scale),
            label = StringResource.from(io.github.lexadiky.pdx.feature.pokemon.details.R.string.feature_pokemon_details_dimension_weight)
                .format(details.weight)
        )
    )

    if (addMythicalOrLegendaryInfo) {
        if (species.isLegendary) {
            add(
                PokemonPhysicalDimension(
                    icon = ImageResource.from(Icons.Default.Star),
                    label = StringResource.from(io.github.lexadiky.pdx.feature.pokemon.details.R.string.feature_pokemon_details_dimension_is_legendary)
                )
            )
        } else if (species.isMythical) {
            add(
                PokemonPhysicalDimension(
                    icon = ImageResource.from(Icons.Default.Star),
                    label = StringResource.from(io.github.lexadiky.pdx.feature.pokemon.details.R.string.feature_pokemon_details_dimension_is_mythical)
                )
            )
        }
    }
}
