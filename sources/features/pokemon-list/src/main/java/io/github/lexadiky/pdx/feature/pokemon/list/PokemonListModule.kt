package io.github.lexadiky.pdx.feature.pokemon.list

import io.github.lexadiky.akore.alice.module
import io.github.lexadiky.akore.alice.robo.singleViewModel
import io.github.lexadiky.akore.alice.util.single
import io.github.lexadiky.pdx.domain.pokemon.PokemonDomainModule
import io.github.lexadiky.pdx.feature.generic.list.GenericListViewModel
import io.github.lexadiky.pdx.feature.generic.list.domain.GenericListItemDataSource
import io.github.lexadiky.pdx.feature.generic.list.domain.GenericListNavigator
import io.github.lexadiky.pdx.feature.pokemon.list.entity.PokemonGenericListItem
import io.github.lexadiky.pdx.feature.pokemon.list.ui.PokemonFilterViewModel
import io.github.lexadiky.pdx.feature.pokemon.list.usecase.PokemonGenericListItemSource
import io.github.lexadiky.pdx.feature.pokemon.list.usecase.PokemonGenericListNavigator

internal val PokemonListModule by module("pokemon-list") {
    import(PokemonDomainModule)
    internal {
        singleViewModel { params ->
            PokemonFilterViewModel(params.get(), params.get())
        }
        singleViewModel { params ->
            GenericListViewModel<PokemonGenericListItem>(
                dataSource = inject(),
                navigator = inject(),
                initialSearchQuery = params.get()
            )
        }
        single<GenericListItemDataSource<PokemonGenericListItem>> { PokemonGenericListItemSource(inject()) }
        single<GenericListNavigator<PokemonGenericListItem>> { PokemonGenericListNavigator(inject()) }
    }
}