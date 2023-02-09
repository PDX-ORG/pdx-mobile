package io.github.lexadiky.pdx.feature.type.details.entity

import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonLanguage
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonPreview
import io.github.lexadiky.pdx.lib.resources.image.ImageResource
import io.github.lexadiky.pdx.lib.resources.image.from
import io.github.lexadiky.pdx.lib.resources.string.StringResource
import io.github.lexadiky.pdx.lib.resources.string.from

internal class TypePokemonPreview(
    val name: StringResource,
    val image: ImageResource,
    val id: String
) {

    companion object {

        fun from(preview: PokemonPreview): TypePokemonPreview {
            return TypePokemonPreview(
                name = StringResource.from(preview.localNames[PokemonLanguage.ENGLISH]!!),
                image = ImageResource.from(preview.normalSprite ?: ""),
                id = preview.name
            )
        }
    }
}
