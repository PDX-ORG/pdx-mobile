package io.github.lexadiky.pdx.domain.pokemon

import io.github.lexadiky.akore.alice.module
import io.github.lexadiky.akore.alice.util.single
import io.github.lexadiky.akore.blogger.BLogger
import io.github.lexadiky.akore.blogger.info
import io.github.lexadiky.pdx.domain.pokemon.usecase.FindPokemonPreviewUseCase
import io.github.lexadiky.pdx.domain.pokemon.usecase.GetAbilityUseCase
import io.github.lexadiky.pdx.domain.pokemon.usecase.GetPokemonAbilitiesUseCase
import io.github.lexadiky.pdx.domain.pokemon.usecase.GetPokemonPreviewSampleUseCase
import io.github.lexadiky.pdx.domain.pokemon.usecase.GetPokemonPreviewUseCase
import io.github.lexadiky.pdx.domain.pokemon.usecase.GetPokemonSpeciesDetailsUseCase
import io.github.lexadiky.pdx.domain.pokemon.usecase.GetPokemonTypeDamageRelations
import io.github.lexadiky.pdx.domain.pokemon.usecase.GetPokemonVarietyDetails
import io.github.lexadiky.pdx.domain.pokemon.usecase.viewed.GetLatestViewedPokemonUseCase
import io.github.lexadiky.pdx.domain.pokemon.usecase.viewed.MarkPokemonSpeciesAsViewedUseCase
import io.github.lexadiky.pdx.lib.locale.LocaleManager
import io.github.lexadiky.pdx.lib.locale.LocaleManagerModule
import io.lexadiky.pokeapi.PokeApiClient
import io.lexadiky.pokeapi.util.CacheSettings
import io.lexadiky.pokeapi.util.PokeApiClientLogger
import java.io.File

val PokemonDomainModule by module("pokemon-domain") {
    import(LocaleManagerModule)

    single { GetPokemonPreviewUseCase(inject(), inject()) }
    single { GetPokemonTypeDamageRelations(inject()) }
    single { GetPokemonSpeciesDetailsUseCase(inject(), inject()) }
    single { FindPokemonPreviewUseCase(inject()) }
    single { GetPokemonPreviewSampleUseCase(inject()) }
    single { GetPokemonVarietyDetails(inject()) }

    single { MarkPokemonSpeciesAsViewedUseCase(inject()) }
    single { GetLatestViewedPokemonUseCase(inject(), inject()) }
    single { GetPokemonAbilitiesUseCase(inject(), inject()) }
    single { GetAbilityUseCase(inject(), inject()) }

    internal {
        single {
            PokeApiClient {
                cache = CacheSettings.FileStorage(File(System.getProperty("java.io.tmpdir"), "pokeapi"))
                logger = object : PokeApiClientLogger {
                    override fun onNetworkReceive(method: String, statusCode: Int, url: String) =
                        BLogger.tag("PokeApi").info("RECEIVE: $method/$statusCode <= $url")

                    override fun onNetworkSend(method: String, url: String) =
                        BLogger.tag("PokeApi").info("SEND: $method => $url")
                }
            }
        }
    }
}
