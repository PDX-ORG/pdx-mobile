package io.github.lexadiky.pdx.feature.pokemon.list

import androidx.compose.runtime.Composable
import io.github.lexadiky.akore.alice.robo.DIFeature
import io.github.lexadiky.akore.alice.robo.di
import io.github.lexadiky.akore.alice.robo.inject
import io.github.lexadiky.akore.alice.robo.viewModel
import io.github.lexadiky.pdx.feature.generic.list.GenericListPage
import io.github.lexadiky.pdx.feature.pokemon.list.entity.PokemonGenericListItem
import io.github.lexadiky.pdx.feature.pokemon.list.entity.PokemonSearchQuery
import io.github.lexadiky.pdx.feature.pokemon.list.entity.parse
import io.github.lexadiky.pdx.feature.pokemon.list.ui.PokemonFilter
import io.github.lexadiky.pdx.lib.navigation.page.PageContext

@Composable
fun PageContext.PokemonListPage() {
    val query = PokemonSearchQuery.parse(context = this)

    DIFeature(PokemonListModule) {
        GenericListPage<PokemonGenericListItem>(
            viewModel = di.inject(query),
            filterBlock = { isVisible, callback ->
                PokemonFilter(
                    viewModel = di.viewModel(key = query.toString(), callback, query),
                    isVisible = isVisible
                )
            }
        )
    }
}
