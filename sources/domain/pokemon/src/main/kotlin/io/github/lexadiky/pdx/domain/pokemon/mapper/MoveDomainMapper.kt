package io.github.lexadiky.pdx.domain.pokemon.mapper

import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonMove
import io.github.lexadiky.pdx.domain.pokemon.entity.asLanguage
import io.github.lexadiky.pdx.domain.pokemon.entity.asType
import io.github.lexadiky.pdx.domain.pokemon.util.asPokemonLanguage
import io.github.lexadiky.pdx.domain.pokemon.util.ofCurrentLocale
import io.github.lexadiky.pdx.library.core.fts.FtsIndex
import io.github.lexadiky.pdx.library.core.utils.removeNewLines
import io.github.lexadiky.pdx.library.locale.LocaleManager
import io.lexadiky.pokeapi.entity.move.Move

internal class MoveDomainMapper(
    private val localeManager: LocaleManager,
) {

    fun map(item: Move): PokemonMove {
        val flavorText = item.flavorTextEntries
            .lastOrNull { it.language.asLanguage() == localeManager.settings.system.asPokemonLanguage() }
            ?: item.flavorTextEntries.firstOrNull()

        val localeShortEffect = item.effectEntries
            .lastOrNull { it.language.asLanguage() == localeManager.settings.system.asPokemonLanguage() }
            ?: item.effectEntries.firstOrNull()

        return PokemonMove(
            name = item.name,
            localeName = item.names
                .ofCurrentLocale(localeManager),
            localeFlavourText = flavorText?.flavorText?.removeNewLines(),
            type = item.type.asType(),
            pp = item.pp,
            power = item.power,
            accuracy = item.accuracy,
            ftsIndex = createFtsIndex(item),
            localeShortEffect = localeShortEffect?.shortEffect
                ?.formatPokeApi(item),
            effectChance = item.effectChance
        )
    }

    private fun String.formatPokeApi(move: Move) =
        replace("\$effect_chance%", "${move.effectChance}%")

    private fun createFtsIndex(item: Move): FtsIndex {
        return FtsIndex.build(
            item.names.map { it.name }
        )
    }
}
