package io.github.lexadiky.pdx.feature.pokemon.list

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import io.github.lexadiky.pdx.feature.generic.list.GenericListPage
import io.github.lexadiky.pdx.feature.pokemon.list.entity.PokemonGenericListItem
import io.github.lexadiky.pdx.feature.pokemon.list.entity.PokemonSearchQuery
import io.github.lexadiky.pdx.feature.pokemon.list.entity.parse
import io.github.lexadiky.pdx.feature.pokemon.list.ui.PokemonFilter
import io.github.lexadiky.pdx.feature.pokemonlist.R
import io.github.lexadiky.akore.alice.robo.DIFeature
import io.github.lexadiky.akore.alice.robo.di
import io.github.lexadiky.akore.alice.robo.inject
import io.github.lexadiky.akore.alice.robo.viewModel
import io.github.lexadiky.pdx.lib.navigation.decoration.Decoration
import io.github.lexadiky.pdx.lib.navigation.page.PageContext

@Composable
fun PageContext.PokemonListPage() {
    val query = PokemonSearchQuery.parse(context = this)

    DIFeature(PokemonListModule) {
        Decoration(decoration = "pdx://toolbar/title") {
            Text(stringResource(id = R.string.pokemon_list_title))
        }
        GenericListPage<PokemonGenericListItem>(
            viewModel = di.inject(query),
            filterBlock = { callback ->
                PokemonFilter(
                    viewModel = di.viewModel(key = query.toString(), callback, query)
                )
            }
        )
    }
}
