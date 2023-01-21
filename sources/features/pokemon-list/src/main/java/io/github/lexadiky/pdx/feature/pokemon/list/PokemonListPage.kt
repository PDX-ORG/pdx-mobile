@file:OptIn(ExperimentalMaterial3Api::class)

package io.github.lexadiky.pdx.feature.pokemon.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import io.github.lexadiky.pdx.feature.generic.list.GenericListPage
import io.github.lexadiky.pdx.feature.pokemon.list.ui.PokemonFilterViewModel
import io.github.lexadiky.pdx.feature.pokemon.list.ui.TypeFilterDialog
import io.github.lexadiky.pdx.feature.pokemon.list.entity.PokemonGenericListItem
import io.github.lexadiky.pdx.feature.pokemon.list.ui.PokemonFilter
import io.github.lexadiky.pdx.feature.pokemonlist.R
import io.github.lexadiky.pdx.lib.arc.di.DIFeature
import io.github.lexadiky.pdx.lib.arc.di.di
import io.github.lexadiky.pdx.lib.navigation.decoration.Decoration

@Composable
fun PokemonListPage() {
    DIFeature(PokemonListModule) {
        Decoration(decoration = "pdx://toolbar/title") {
            Text(stringResource(id = R.string.pokemon_list_title))
        }
        GenericListPage<PokemonGenericListItem>(
            viewModel = di.inject(),
            filterBlock = { callback ->
                PokemonFilter(
                    viewModel = di.inject(callback)
                )
            }
        )
    }
}
