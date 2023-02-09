package io.github.lexadiky.pdx.feature.pokemon.list

import io.github.lexadiky.akore.alice.module
import io.github.lexadiky.akore.alice.robo.viewModel
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
        viewModel { params -> PokemonFilterViewModel(inject(), params.get()) }
        viewModel { params ->
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
