package io.github.lexadiky.pdx.feature.pokemon.list.entity

import io.github.lexadiky.pdx.feature.generic.list.entity.GenericListBanner

class PokemonGenericBannerItem(
    private val position: Int,
    override val bannerId: String,
    override val id: String
) : GenericListBanner<PokemonGenericListItem> {

    override fun shouldPlace(position: Int, item: PokemonGenericListItem): Boolean {
       return position == this.position
    }
}