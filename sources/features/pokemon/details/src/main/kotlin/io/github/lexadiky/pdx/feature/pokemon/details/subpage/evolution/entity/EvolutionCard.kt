package io.github.lexadiky.pdx.feature.pokemon.details.subpage.evolution.entity

import io.github.lexadiky.pdx.lib.resources.image.ImageResource
import io.github.lexadiky.pdx.lib.resources.string.StringResource

sealed interface EvolutionCard {

    class Pokemon(
        val title: StringResource,
        val image: ImageResource
    ): EvolutionCard
}
