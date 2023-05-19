package io.github.lexadiky.pdx.domain.pokemon.entity

import io.lexadiky.pokeapi.entity.common.ResourcePointer
import io.lexadiky.pokeapi.entity.language.Language
import kotlinx.serialization.SerialName

enum class PokemonLanguage(internal val languageTag: String) {
    @SerialName("roomaji")
    JA_ROOMAJI("roomaji"),

    @SerialName("en")
    ENGLISH("en"),

    @SerialName("ja")
    JAPANESE("ja"),

    @SerialName("ja-Hrkt")
    JA_KATAKANA("ja-Hrkt"),

    @SerialName("ko")
    KOREAN("ko"),

    @SerialName("zh-Hant")
    CHINESE_TRADITIONAL("zh-Hant"),

    @SerialName("zh-Hans")
    CHINESE_SIMPLIFIED("zh-Hans"),

    @SerialName("it")
    ITALIAN("it"),

    @SerialName("fr")
    FRENCH("fr"),

    @SerialName("es")
    SPANISH("es"),

    @SerialName("de")
    GERMAN("de");

    companion object {

        fun default(): PokemonLanguage = ENGLISH
    }
}

fun ResourcePointer<Language>.asLanguage(): PokemonLanguage {
    return PokemonLanguage.values().firstOrNull { it.languageTag == this.name }
        ?: PokemonLanguage.default()
}
