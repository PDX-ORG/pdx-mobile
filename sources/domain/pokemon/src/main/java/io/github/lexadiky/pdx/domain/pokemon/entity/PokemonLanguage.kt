package io.github.lexadiky.pdx.domain.pokemon.entity

import io.lexadiky.pokeapi.entity.common.ResourcePointer
import io.lexadiky.pokeapi.entity.language.Language
import kotlinx.serialization.SerialName

enum class PokemonLanguage {
    @SerialName("roomaji")
    JA_ROOMAJI,
    @SerialName("en")
    ENGLISH,
    @SerialName("ja")
    JAPANESE,

    UNKNOWN
}

// not implemented
fun ResourcePointer<Language>.asLanguage(): PokemonLanguage {
    return when (this.name) {
        "en" ->  PokemonLanguage.ENGLISH
        else -> PokemonLanguage.UNKNOWN
    }
}