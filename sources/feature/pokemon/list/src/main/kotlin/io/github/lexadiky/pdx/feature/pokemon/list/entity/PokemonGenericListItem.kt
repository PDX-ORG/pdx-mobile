package io.github.lexadiky.pdx.feature.pokemon.list.entity

import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonType
import io.github.lexadiky.pdx.feature.generic.list.entity.GenericListItem
import io.github.lexadiky.pdx.library.resources.image.ImageResource
import io.github.lexadiky.pdx.library.resources.string.StringResource

data class PokemonGenericListItem(
    override val id: String,
    override val title: StringResource,
    override val note: StringResource,
    override val primaryImage: ImageResource,
    override val secondaryImage: ImageResource?,
    override val tags: List<GenericListItem.Tag>,
    override val isFavorite: Boolean,
    val textSearchIndex: String,
    val types: List<PokemonType>
) : GenericListItem
