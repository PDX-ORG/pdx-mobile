package io.github.lexadiky.pdx.domain.pokemon.usecase.pokemon

import arrow.core.Either
import arrow.core.continuations.either
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonArchetype
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonDetails
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonSprites
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonStat
import io.github.lexadiky.pdx.domain.pokemon.entity.asPokemonStat
import io.github.lexadiky.pdx.domain.pokemon.entity.asType
import io.github.lexadiky.pdx.lib.core.error.GenericError
import io.lexadiky.pokeapi.PokeApiClient
import io.lexadiky.pokeapi.entity.pokemon.Pokemon
import kotlin.reflect.KProperty1
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.typeOf

class GetPokemonVarietyDetails(
    private val client: PokeApiClient
) {

    suspend operator fun invoke(varietyId: String): Either<GenericError, PokemonDetails> = either {
        val variety = client.pokemon.get(varietyId)
            .bind { GenericError("can't load variety", it) }

        mapPokemonDetails(variety)
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
        defaultShiny = defaultVariety.sprites.frontShiny ?: "",
        all = extractAllSpritesWithReflection(defaultVariety.sprites)
            .filter { it.isNotBlank() }
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

    companion object {

        private const val POKEMON_DIMENSION_MODIFIER = 10
    }
}
