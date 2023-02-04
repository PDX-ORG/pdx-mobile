package io.github.lexadiky.pdx.domain.pokemon.usecase

import arrow.core.Either
import arrow.core.continuations.either
import arrow.core.identity
import io.github.lexadiky.akore.blogger.BLogger
import io.github.lexadiky.akore.blogger.error
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonDetails
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonSpeciesDetails
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonSprites
import io.github.lexadiky.pdx.domain.pokemon.entity.asLanguage
import io.github.lexadiky.pdx.domain.pokemon.entity.asPokemonStat
import io.github.lexadiky.pdx.domain.pokemon.entity.asType
import io.lexadiky.pokeapi.PokeApiClient
import io.lexadiky.pokeapi.entity.pokemon.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import kotlin.reflect.KProperty
import kotlin.reflect.KProperty1
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.typeOf

class GetPokemonSpeciesDetailsUseCase(private val pokeApiClient: PokeApiClient) {

    suspend operator fun invoke(id: String): Either<Error, PokemonSpeciesDetails> = either {
        withContext(Dispatchers.IO) {
            val species = pokeApiClient.pokemonSpecies.get(name = id).bind(::identity)
            val defaultVariety = pokeApiClient.pokemon.get(species.varieties.first { it.isDefault })
                .bind(::identity)

            PokemonSpeciesDetails(
                name = species.name,
                localizedNames = species.names.associate { name ->
                    name.language.asLanguage() to name.name
                },
                primaryVariety = mapPokemonDetails(defaultVariety),
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

    private fun mapPokemonDetails(defaultVariety: Pokemon): PokemonDetails {
        return PokemonDetails(
            name = defaultVariety.name,
            types = defaultVariety.types.map { it.type.asType() },
            sprites = extractSprites(defaultVariety),
            stats = defaultVariety.stats.associate { slot ->
                slot.stat.asPokemonStat() to slot.baseStat
            }
        )
    }

    private fun extractSprites(defaultVariety: Pokemon) = PokemonSprites(
        default = defaultVariety.sprites.frontDefault ?: "",
        all = extractAllSpritesWithReflection(defaultVariety.sprites)
    )

    // TODO maybe should consider something more sane
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

    private fun extractAllSpritesWithReflection(groupProperties: List<KProperty1<out Any, *>>, sprites: Any) =
        groupProperties.flatMap { property ->
            (property as KProperty1<Any, Any?>).get(sprites)
                ?.let { extractAllSpritesWithReflection(it) }
                .orEmpty()
        }

    object Error
}