package io.github.lexadiky.pdx.feature.pokemon.list

import io.github.lexadiky.akore.alice.module
import io.github.lexadiky.akore.alice.robo.singleViewModel
import io.github.lexadiky.akore.alice.util.single
import io.github.lexadiky.pdx.domain.achievement.AchievementModule
import io.github.lexadiky.pdx.domain.pokemon.PokemonDomainModule
import io.github.lexadiky.pdx.feature.generic.list.GenericListViewModel
import io.github.lexadiky.pdx.feature.generic.list.domain.GenericListBannerDataSource
import io.github.lexadiky.pdx.feature.generic.list.domain.GenericListItemDataSource
import io.github.lexadiky.pdx.feature.generic.list.domain.GenericListNavigator
import io.github.lexadiky.pdx.feature.pokemon.list.entity.PokemonGenericBannerItem
import io.github.lexadiky.pdx.feature.pokemon.list.entity.PokemonGenericListItem
import io.github.lexadiky.pdx.feature.pokemon.list.ui.PokemonFilterViewModel
import io.github.lexadiky.pdx.feature.pokemon.list.usecase.PokemonGenericBannerItemSource
import io.github.lexadiky.pdx.feature.pokemon.list.usecase.PokemonGenericListItemSource
import io.github.lexadiky.pdx.feature.pokemon.list.usecase.PokemonGenericListNavigator

internal val PokemonListModule by module("pokemon-list") {
    import(PokemonDomainModule)
    import(AchievementModule)
    internal {
        singleViewModel { params ->
            PokemonFilterViewModel(params.get(), params.get())
        }
        singleViewModel { params ->
            GenericListViewModel<PokemonGenericListItem>(
                initialSearchQuery = params.get(),
                dataSource = inject(),
                bannerSource = inject(),
                navigator = inject(),
                shakeDetector = inject(),
                achievementManager = inject()
            )
        }
        single<GenericListBannerDataSource<PokemonGenericListItem>> { PokemonGenericBannerItemSource() }
        single<GenericListItemDataSource<PokemonGenericListItem>> { PokemonGenericListItemSource(inject()) }
        single<GenericListNavigator<PokemonGenericListItem>> { PokemonGenericListNavigator(inject()) }
    }
}