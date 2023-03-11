package io.github.lexadiky.pdx.domain.pokemon.usecase

import arrow.core.Either
import arrow.core.continuations.either
import arrow.core.identity
import io.github.lexadiky.akore.blogger.BLogger
import io.github.lexadiky.akore.blogger.error
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonArchetype
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonDetails
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonLanguage
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonSpeciesDetails
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonSprites
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonStat
import io.github.lexadiky.pdx.domain.pokemon.entity.UseRomajiLocaleFlag
import io.github.lexadiky.pdx.domain.pokemon.entity.asLanguage
import io.github.lexadiky.pdx.domain.pokemon.entity.asPokemonStat
import io.github.lexadiky.pdx.domain.pokemon.entity.asType
import io.github.lexadiky.pdx.domain.pokemon.util.asPokemonLanguage
import io.github.lexadiky.pdx.lib.locale.LocaleManager
import io.lexadiky.pokeapi.PokeApiClient
import io.lexadiky.pokeapi.entity.pokemon.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import kotlin.reflect.KProperty1
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.typeOf

class GetPokemonSpeciesDetailsUseCase(
    private val pokeApiClient: PokeApiClient,
    private val localeManager: LocaleManager
) {

    suspend operator fun invoke(id: String): Either<Error, PokemonSpeciesDetails> = either {
        withContext(Dispatchers.IO) {
            val species = pokeApiClient.pokemonSpecies.get(name = id).bind(::identity)
            val defaultVariety = pokeApiClient.pokemon.get(species.varieties.first { it.isDefault })
                .bind(::identity)

            val names = species.names.associate { name -> name.language.asLanguage() to name }
            val localeName = if (localeManager.settings.has(UseRomajiLocaleFlag)) {
                names[PokemonLanguage.JA_ROOMAJI]?.name
            } else {
                names[localeManager.settings.system.asPokemonLanguage()]?.name
            } ?: names.values.first().name

            PokemonSpeciesDetails(
                name = species.name,
                localeName = localeName,
                primaryVariety = mapPokemonDetails(defaultVariety),
                isLegendary = species.isLegendary,
                isMythical = species.isMythical,
                varieties = species.varieties.map {
                    async {
                        pokeApiClient.pokemon.get(it).bind(::identity)
                            .let(::mapPokemonDetails)
                    }
                }.awaitAll()
            )
        }
    }.mapLeft { error ->
        BLogger.tag("GetPokemonSpeciesDetailsUseCase")
            .error("can't load pokemon species", error)
        Error
    }

    private fun mapPokemonDetails(variety: Pokemon): PokemonDetails {
        val stats = variety.stats.associate { slot ->
            slot.stat.asPokemonStat() to slot.baseStat
        }
        return PokemonDetails(
            name = variety.name,
            types = variety.types.map { it.type.asType() },
            sprites = extractSprites(variety),
            stats = stats,
            archetype = makeArchetype(stats),
            height = variety.height.toDouble() / POKEMON_DIMENSION_MODIFIER,
            weight = variety.weight.toDouble() / POKEMON_DIMENSION_MODIFIER
        )
    }

    private fun makeArchetype(stats: Map<PokemonStat, Int>): PokemonArchetype {
        if (stats.values.distinct().size == 1) {
            return PokemonArchetype.PerfectlyBalanced
        }

        return when (stats.maxByOrNull { it.value }?.key) {
            PokemonStat.SpAttack -> PokemonArchetype.SpecialAttacker
            PokemonStat.Attack -> PokemonArchetype.PhysicalAttacker
            PokemonStat.Speed -> PokemonArchetype.Speedster
            else -> PokemonArchetype.Unknown
        }
    }

    private fun extractSprites(defaultVariety: Pokemon) = PokemonSprites(
        default = defaultVariety.sprites.frontDefault ?: "",
        all = extractAllSpritesWithReflection(defaultVariety.sprites)
    )

    // TODO maybe should consider something more sane
    @Suppress("UNCHECKED_CAST")
    private fun extractAllSpritesWithReflection(sprites: Any): List<String> {
        val nullableStringType = typeOf<String?>()

        val cls = sprites::class
        val allProperties = cls.declaredMemberProperties
        val groupProperties = allProperties.filter { it.returnType != nullableStringType }
        val urlProperties = allProperties.filter { it.returnType == nullableStringType }
        return urlProperties.mapNotNull {
            (it as KProperty1<Any, String?>).get(sprites)
        } + extractAllSpritesWithReflection(groupProperties, sprites)
    }

    @Suppress("UNCHECKED_CAST")
    private fun extractAllSpritesWithReflection(groupProperties: List<KProperty1<out Any, *>>, sprites: Any) =
        groupProperties.flatMap { property ->
            (property as KProperty1<Any, Any?>).get(sprites)
                ?.let { extractAllSpritesWithReflection(it) }
                .orEmpty()
        }

    object Error

    companion object {

        private const val POKEMON_DIMENSION_MODIFIER = 100
    }
}
