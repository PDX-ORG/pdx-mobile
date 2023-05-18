package io.github.lexadiky.pdx.domain.pokemon.util

import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonLanguage
import io.github.lexadiky.pdx.domain.pokemon.entity.asLanguage
import io.github.lexadiky.pdx.lib.locale.LocaleManager
import io.lexadiky.pokeapi.entity.common.Name
import java.util.Locale

fun List<Name>.ofCurrentLocale(manager: LocaleManager): String {
    val currentLanguage = manager.settings.system.asPokemonLanguage()
    return firstOrNull { it.language.asLanguage() == currentLanguage }
        ?.name
        ?: this.firstOrNull()?.name
        ?: ""
}

internal fun Locale.asPokemonLanguage(): PokemonLanguage {
    val localeCode = this.toLanguageTag()
    return PokemonLanguage.values()
        .firstOrNull { pokemonLanguage -> localeCode == pokemonLanguage.languageTag }
        ?: PokemonLanguage.default()
}
