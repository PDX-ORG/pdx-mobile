package io.github.lexadiky.pdx.domain.pokemon.usecase

import arrow.core.Either
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonAbility
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonLanguage
import io.github.lexadiky.pdx.domain.pokemon.entity.asLanguage
import io.github.lexadiky.pdx.domain.pokemon.util.asPokemonLanguage
import io.github.lexadiky.pdx.domain.pokemon.util.normalizePokeApiText
import io.github.lexadiky.pdx.domain.pokemon.util.ofCurrentLocale
import io.github.lexadiky.pdx.lib.locale.LocaleManager
import io.lexadiky.pokeapi.PokeApiClient
import io.lexadiky.pokeapi.entity.ability.Ability

class GetAbilityUseCase(
    private val pokeApiClient: PokeApiClient,
    private val localeManager: LocaleManager
) {

    suspend operator fun invoke(id: String, isHidden: Boolean) : Either<Error, PokemonAbility> {
        return Either.catch { pokeApiClient.ability.get(id).getOrThrow() }
            .map { ability ->
                PokemonAbility(
                    name = ability.name,
                    nameLocale = ability.names.ofCurrentLocale(localeManager),
                    isHidden = isHidden,
                    effect = extractEffect(ability),
                    flavourText = extractFlavour(ability)
                )
            }.mapLeft { Error }
    }

    private fun extractEffect(ability: Ability): PokemonAbility.Effect? {
        val effect = ability.effectEntries.firstOrNull { it.language.asLanguage() == localeManager.settings.system.asPokemonLanguage() }
            ?: return null

        return PokemonAbility.Effect(
            textLocale = effect.effect,
            shortTextLocale = effect.shortEffect
        )
    }

    private fun extractFlavour(ability: Ability): List<PokemonAbility.Flavour> {
        return ability.flavorTextEntries.filter { it.language.asLanguage() == localeManager.settings.system.asPokemonLanguage() }
            .map {
                PokemonAbility.Flavour(
                    textLocale = it.flavorText.normalizePokeApiText(),
                )
            }
    }

    object Error
}