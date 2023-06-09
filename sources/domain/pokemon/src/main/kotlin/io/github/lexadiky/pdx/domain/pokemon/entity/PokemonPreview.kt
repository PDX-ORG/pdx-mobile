package io.github.lexadiky.pdx.domain.pokemon.entity

import io.github.lexadiky.pdx.library.core.fts.FtsIndex

data class PokemonPreview(
    val name: String,
    val nationalDexNumber: Int,
    val localeName: String,
    val normalSprite: String?,
    val shinySprite: String?,
    val types: List<PokemonType>,
    val searchIndex: FtsIndex
)
