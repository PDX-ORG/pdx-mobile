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

    @SerialName("ja-Hrkt")
    JA_KATAKANA,

    @SerialName("ko")
    KOREAN,

    @SerialName("zh-Hant")
    CHINESE_TRADITIONAL,

    @SerialName("zh-Hans")
    CHINESE_SIMPLIFIED,

    @SerialName("it")
    ITALIAN,

    @SerialName("fr")
    FRENCH,

    @SerialName("es")
    SPANISH,

    @SerialName("de")
    GERMAN,

    UNKNOWN
}

// not implemented
fun ResourcePointer<Language>.asLanguage(): PokemonLanguage {
    return when (this.name) {
        "en" -> PokemonLanguage.ENGLISH
        "roomaji" -> PokemonLanguage.JA_ROOMAJI
        "ja" -> PokemonLanguage.JAPANESE
        else -> PokemonLanguage.UNKNOWN
    }
}
