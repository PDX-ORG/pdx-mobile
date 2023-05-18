package io.github.lexadiky.pdx.domain.pokemon

import io.github.lexadiky.akore.alice.module
import io.github.lexadiky.akore.alice.util.single
import io.github.lexadiky.pdx.domain.pokemon.factory.PokeApiClientFactory
import io.github.lexadiky.pdx.domain.pokemon.mapper.MoveDomainMapper
import io.github.lexadiky.pdx.domain.pokemon.repository.FavoritePokemonRepository
import io.github.lexadiky.pdx.domain.pokemon.repository.ViewedPokemonRepository
import io.github.lexadiky.pdx.domain.pokemon.usecase.GetPokemonGameVersionUseCase
import io.github.lexadiky.pdx.domain.pokemon.usecase.GetPokemonTypeDamageRelationsUseCase
import io.github.lexadiky.pdx.domain.pokemon.usecase.ability.GetAbilityUseCase
import io.github.lexadiky.pdx.domain.pokemon.usecase.ability.GetPokemonAbilitiesUseCase
import io.github.lexadiky.pdx.domain.pokemon.usecase.evolution.GetPokemonEvolutionDetailsUseCase
import io.github.lexadiky.pdx.domain.pokemon.usecase.favorite.IsPokemonFavoriteUseCase
import io.github.lexadiky.pdx.domain.pokemon.usecase.favorite.SaveFavoritePokemonUseCase
import io.github.lexadiky.pdx.domain.pokemon.usecase.move.GetMoveDetailsUseCase
import io.github.lexadiky.pdx.domain.pokemon.usecase.move.GetPokemonMovesUseCase
import io.github.lexadiky.pdx.domain.pokemon.usecase.pokemon.FindPokemonPreviewUseCase
import io.github.lexadiky.pdx.domain.pokemon.usecase.pokemon.GetAllPokemonPreviewsUseCase
import io.github.lexadiky.pdx.domain.pokemon.usecase.pokemon.GetPokemonDetailsBySpeciesUseCase
import io.github.lexadiky.pdx.domain.pokemon.usecase.pokemon.GetPokemonPokedexDescriptionsUseCase
import io.github.lexadiky.pdx.domain.pokemon.usecase.pokemon.GetPokemonPreviewSampleUseCase
import io.github.lexadiky.pdx.domain.pokemon.usecase.pokemon.GetPokemonPreviewUseCase
import io.github.lexadiky.pdx.domain.pokemon.usecase.pokemon.GetPokemonSpeciesDetailsUseCase
import io.github.lexadiky.pdx.domain.pokemon.usecase.pokemon.GetPokemonVarietyDetailsUseCase
import io.github.lexadiky.pdx.domain.pokemon.usecase.prefetch.PrefetchPokemonDataUseCase
import io.github.lexadiky.pdx.domain.pokemon.usecase.viewed.GetLatestViewedPokemonUseCase
import io.github.lexadiky.pdx.domain.pokemon.usecase.viewed.MarkPokemonSpeciesAsViewedUseCase
import io.github.lexadiky.pdx.lib.locale.LocaleManagerModule

val PokemonDomainModule by module("domain-pokemon") {
    import(LocaleManagerModule)

    single { GetAllPokemonPreviewsUseCase(inject(), inject()) }
    single { GetPokemonTypeDamageRelationsUseCase(inject()) }
    single { GetPokemonSpeciesDetailsUseCase(inject(), inject(), inject()) }
    single { FindPokemonPreviewUseCase(inject()) }
    single { GetPokemonPreviewSampleUseCase(inject()) }
    single { GetPokemonVarietyDetailsUseCase(inject()) }

    single { MarkPokemonSpeciesAsViewedUseCase(inject()) }
    single { GetLatestViewedPokemonUseCase(inject()) }
    single { GetPokemonAbilitiesUseCase(inject(), inject()) }
    single { GetAbilityUseCase(inject(), inject()) }
    single { GetPokemonDetailsBySpeciesUseCase(inject(), inject()) }

    single { SaveFavoritePokemonUseCase(inject()) }
    single { IsPokemonFavoriteUseCase(inject()) }

    single { GetPokemonPokedexDescriptionsUseCase(inject(), inject(), inject()) }
    single { GetPokemonGameVersionUseCase(inject(), inject()) }

    single { GetPokemonMovesUseCase(inject(), inject()) }
    single { GetMoveDetailsUseCase(inject(), inject()) }

    single { GetPokemonPreviewUseCase(inject()) }
    single { GetPokemonEvolutionDetailsUseCase(inject(), inject()) }

    single { PrefetchPokemonDataUseCase(inject()) }

    internal {
        single { PokeApiClientFactory().create() }
        single { FavoritePokemonRepository(inject()) }
        single { ViewedPokemonRepository(inject(), inject()) }
        single { MoveDomainMapper(inject()) }
    }
}
