package io.github.lexadiky.pdx.domain.pokemon

import io.github.lexadiky.akore.alice.module
import io.github.lexadiky.akore.alice.util.single
import io.github.lexadiky.akore.blogger.BLogger
import io.github.lexadiky.akore.blogger.info
import io.github.lexadiky.pdx.domain.pokemon.repository.FavoritePokemonRepository
import io.github.lexadiky.pdx.domain.pokemon.repository.ViewedPokemonRepository
import io.github.lexadiky.pdx.domain.pokemon.usecase.FindPokemonPreviewUseCase
import io.github.lexadiky.pdx.domain.pokemon.usecase.GetAbilityUseCase
import io.github.lexadiky.pdx.domain.pokemon.usecase.GetPokemonAbilitiesUseCase
import io.github.lexadiky.pdx.domain.pokemon.usecase.GetPokemonPokedexDescriptions
import io.github.lexadiky.pdx.domain.pokemon.usecase.GetPokemonPreviewSampleUseCase
import io.github.lexadiky.pdx.domain.pokemon.usecase.GetPokemonPreviewUseCase
import io.github.lexadiky.pdx.domain.pokemon.usecase.GetPokemonSpeciesDetailsUseCase
import io.github.lexadiky.pdx.domain.pokemon.usecase.GetPokemonTypeDamageRelations
import io.github.lexadiky.pdx.domain.pokemon.usecase.GetPokemonVarietyDetails
import io.github.lexadiky.pdx.domain.pokemon.usecase.GetPokemonGameVersion
import io.github.lexadiky.pdx.domain.pokemon.usecase.favorite.IsPokemonFavorite
import io.github.lexadiky.pdx.domain.pokemon.usecase.favorite.SaveFavoritePokemon
import io.github.lexadiky.pdx.domain.pokemon.usecase.move.GetMoveDetails
import io.github.lexadiky.pdx.domain.pokemon.usecase.move.GetPokemonMoves
import io.github.lexadiky.pdx.domain.pokemon.usecase.move.MoveDomainMapper
import io.github.lexadiky.pdx.domain.pokemon.usecase.prefetch.PrefetchPokemonData
import io.github.lexadiky.pdx.domain.pokemon.usecase.viewed.GetLatestViewedPokemonUseCase
import io.github.lexadiky.pdx.domain.pokemon.usecase.viewed.MarkPokemonSpeciesAsViewedUseCase
import io.github.lexadiky.pdx.lib.locale.LocaleManagerModule
import io.lexadiky.pokeapi.PokeApiClient
import io.lexadiky.pokeapi.network.CacheSettings
import io.lexadiky.pokeapi.util.PokeApiClientLogger
import java.io.File
import kotlin.time.Duration.Companion.seconds

val PokemonDomainModule by module("domain-pokemon") {
    import(LocaleManagerModule)

    single { GetPokemonPreviewUseCase(inject(), inject()) }
    single { GetPokemonTypeDamageRelations(inject()) }
    single { GetPokemonSpeciesDetailsUseCase(inject(), inject()) }
    single { FindPokemonPreviewUseCase(inject()) }
    single { GetPokemonPreviewSampleUseCase(inject()) }
    single { GetPokemonVarietyDetails(inject()) }

    single { MarkPokemonSpeciesAsViewedUseCase(inject()) }
    single { GetLatestViewedPokemonUseCase(inject()) }
    single { GetPokemonAbilitiesUseCase(inject(), inject()) }
    single { GetAbilityUseCase(inject(), inject()) }

    single { SaveFavoritePokemon(inject()) }
    single { IsPokemonFavorite(inject()) }

    single { GetPokemonPokedexDescriptions(inject(), inject(), inject()) }
    single { GetPokemonGameVersion(inject(), inject()) }

    single { GetPokemonMoves(inject(), inject()) }
    single { GetMoveDetails(inject(), inject()) }

    single { PrefetchPokemonData(inject()) }

    internal {
        single {
            PokeApiClient {
                cache = CacheSettings.FileStorage(File(System.getProperty("java.io.tmpdir"), "pokeapi"))
                timeout = 30.seconds
                logger = object : PokeApiClientLogger {
                    override fun onNetworkReceive(method: String, statusCode: Int, url: String) =
                        BLogger.tag("PokeApi").info("RECEIVE: $method/$statusCode <= $url")

                    override fun onNetworkSend(method: String, url: String) =
                        BLogger.tag("PokeApi").info("SEND: $method => $url")
                }
            }
        }
        single { FavoritePokemonRepository(inject()) }
        single { ViewedPokemonRepository(inject(), inject()) }
        single { MoveDomainMapper(inject()) }
    }
}
