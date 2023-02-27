package io.github.lexadiky.pdx.domain.pokemon.util

import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonLanguage
import io.github.lexadiky.pdx.domain.pokemon.entity.asLanguage
import io.lexadiky.pokeapi.entity.common.Name

fun List<Name>.ofCurrentLocale(): String {
    return first { it.language.asLanguage() == PokemonLanguage.ENGLISH }
        .name
}