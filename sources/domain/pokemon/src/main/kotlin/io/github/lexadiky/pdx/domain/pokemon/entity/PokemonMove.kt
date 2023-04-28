package io.github.lexadiky.pdx.domain.pokemon.entity

import io.github.lexadiky.pdx.lib.core.fts.FtsIndex

data class PokemonMove(
    val name: String,
    val localeName: String,
    val localeFlavourText: String?,
    val type: PokemonType,
    val pp: Int?,
    val power: Int?,
    val accuracy: Int?,
    val ftsIndex: FtsIndex,
    val localeShortEffect: String?,
    val effectChance: Int?
)
