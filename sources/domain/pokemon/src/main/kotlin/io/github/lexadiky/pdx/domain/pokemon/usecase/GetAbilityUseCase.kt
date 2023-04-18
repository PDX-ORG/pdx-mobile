package io.github.lexadiky.pdx.domain.pokemon.usecase

import arrow.core.Either
import arrow.core.flatMap
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonAbility
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonLanguage
import io.github.lexadiky.pdx.domain.pokemon.entity.asLanguage
import io.github.lexadiky.pdx.domain.pokemon.util.asPokemonLanguage
import io.github.lexadiky.pdx.domain.pokemon.util.normalizePokeApiText
import io.github.lexadiky.pdx.domain.pokemon.util.ofCurrentLocale
import io.github.lexadiky.pdx.lib.core.ErrorType
import io.github.lexadiky.pdx.lib.locale.LocaleManager
import io.lexadiky.pokeapi.PokeApiClient
import io.lexadiky.pokeapi.entity.ability.Ability
import java.lang.Exception

class GetAbilityUseCase(
    private val pokeApiClient: PokeApiClient,
    private val localeManager: LocaleManager
) {

    suspend operator fun invoke(id: String, isHidden: Boolean): Either<Error, PokemonAbility> {
        return Either.catch { pokeApiClient.ability.get(id).getOrThrow() }
            .mapLeft { Error.Network(it) }
            .flatMap { ability -> mapAbility(ability, isHidden) }
    }

    private fun mapAbility(ability: Ability, isHidden: Boolean) = Either.catch {
        PokemonAbility(
            name = ability.name,
            nameLocale = ability.names.ofCurrentLocale(localeManager),
            isHidden = isHidden,
            effect = extractEffect(ability),
            flavourText = extractFlavour(ability)
        )
    }.mapLeft { Error.Generic(it) }

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

    sealed class Error(cause: Throwable, message: String) : Throwable(message, cause), ErrorType.Any {

        class Network(cause: Throwable) : Error(cause, "can't load ability details, due to network error"),
                ErrorType.Network

        class Generic(cause: Throwable) : Error(cause, "can't load ability details, unknown error"),
                ErrorType.Generic
    }
}
