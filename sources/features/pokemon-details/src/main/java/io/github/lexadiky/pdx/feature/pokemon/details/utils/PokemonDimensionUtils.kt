package io.github.lexadiky.pdx.feature.pokemon.details.utils

import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonDetails
import io.github.lexadiky.pdx.feature.pokemon.details.entitiy.PokemonPhysicalDimension
import io.github.lexadiky.pdx.lib.resources.image.ImageResource
import io.github.lexadiky.pdx.lib.resources.image.from
import io.github.lexadiky.pdx.lib.resources.string.StringResource
import io.github.lexadiky.pdx.lib.resources.string.format
import io.github.lexadiky.pdx.lib.resources.string.from
import io.github.lexadiky.pdx.lib.uikit.R

internal fun extractDimensions(details: PokemonDetails?): List<PokemonPhysicalDimension> = buildList {
    details ?: return@buildList

    add(
        PokemonPhysicalDimension(
        icon = ImageResource.from(R.drawable.uikit_ic_height),
        label = StringResource.from(io.github.lexadiky.pdx.feature.pokemon.details.R.string.feature_pokemon_details_dimension_height)
            .format(details.height)
    )
    )
    add(
        PokemonPhysicalDimension(
        icon = ImageResource.from(R.drawable.uikit_ic_scale),
        label = StringResource.from(io.github.lexadiky.pdx.feature.pokemon.details.R.string.feature_pokemon_details_dimension_weight)
            .format(details.weight)
    )
    )
}