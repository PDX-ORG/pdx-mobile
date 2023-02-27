package io.github.lexadiky.pdx.domain.pokemon.usecase

import arrow.core.Either
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonAbility
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonLanguage
import io.github.lexadiky.pdx.domain.pokemon.entity.asLanguage
import io.github.lexadiky.pdx.domain.pokemon.util.ofCurrentLocale
import io.lexadiky.pokeapi.PokeApiClient
import io.lexadiky.pokeapi.entity.ability.Ability

class GetAbilityUseCase(
    private val pokeApiClient: PokeApiClient
) {

    suspend operator fun invoke(id: String, isHidden: Boolean) : Either<Error, PokemonAbility> {
        return Either.catch { pokeApiClient.ability.get(id).getOrThrow() }
            .map { ability ->
                PokemonAbility(
                    name = ability.name,
                    nameLocale = ability.names.ofCurrentLocale(),
                    isHidden = isHidden,
                    effect = extractEffect(ability),
                    flavourText = extractFlavour(ability)
                )
            }.mapLeft { Error }
    }

    private fun extractEffect(ability: Ability): PokemonAbility.Effect? {
        val effect = ability.effectEntries.firstOrNull { it.language.asLanguage() == PokemonLanguage.ENGLISH }
            ?: return null

        return PokemonAbility.Effect(
            textLocale = effect.flavorText,
            shortTextLocale = effect.versionGroup
        )
    }

    private fun extractFlavour(ability: Ability): List<PokemonAbility.Flavour> {
        return ability.flavorTextEntries.filter { it.language.asLanguage() == PokemonLanguage.ENGLISH }
            .map {
                PokemonAbility.Flavour(
                    textLocale = it.flavorText.replace("\n", " "),
                )
            }
    }

    object Error
}