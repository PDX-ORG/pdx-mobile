@file:OptIn(ExperimentalMaterial3Api::class)

package io.github.lexadiky.pdx.feature.pokemon.list.ui

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.github.lexadiky.pdx.feature.pokemonlist.R
import io.github.lexadiky.akore.alice.robo.di
import io.github.lexadiky.akore.alice.robo.inject
import io.github.lexadiky.pdx.lib.navigation.decoration.Decoration
import io.github.lexadiky.pdx.ui.uikit.widget.SearchField


@Composable
internal fun PokemonFilter(isVisible: Boolean, viewModel: PokemonFilterViewModel = di.inject()) {
    Decoration(decoration = "pdx://toolbar/title") {
        if (isVisible) {
            SearchField(
                text = viewModel.state.query.text,
                onTextChanged = { viewModel.updateTextQuery(it) },
                modifier = Modifier.fillMaxWidth()
                    .height(40.dp)
            )
        } else {
            Text(
                text = stringResource(id = R.string.pokemon_list_title),
                modifier = Modifier
            )
        }
    }
    if (isVisible) {
        Column {
            Row {
                if (viewModel.state.showTypeFilterDialog) {
                    TypeFilterDialog(
                        onClear = { viewModel.hideTypeFilterDialog(clearTypeFilters = true) },
                        onOk = { viewModel.hideTypeFilterDialog(clearTypeFilters = false) },
                        onItemClicked = { viewModel.onTypeSelected(it) },
                        selected = viewModel.state.query.selectedTypes
                    )
                }
                FilterChip(
                    selected = false,
                    onClick = { viewModel.showTypeFilterDialog() },
                    label = { Text(text = stringResource(id = R.string.pokemon_list_query_type_button)) }
                )
            }
        }
    }
}
