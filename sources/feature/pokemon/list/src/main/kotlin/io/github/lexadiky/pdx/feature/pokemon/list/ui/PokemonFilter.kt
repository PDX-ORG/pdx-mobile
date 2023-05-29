@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)

package io.github.lexadiky.pdx.feature.pokemon.list.ui

import io.github.lexadiky.pdx.feature.pokemon.list.R
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import io.github.lexadiky.akore.alice.robo.di
import io.github.lexadiky.akore.alice.robo.viewModel
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonType
import io.github.lexadiky.akore.lechuck.robo.decoration.Decoration
import io.github.lexadiky.pdx.library.uikit.theme.grid
import io.github.lexadiky.pdx.library.uikit.widget.SearchField

@Composable
internal fun PokemonFilter(isVisible: Boolean, viewModel: PokemonFilterViewModel = di.viewModel()) {
    LaunchedEffect(!isVisible) {
        if (!isVisible) {
            viewModel.clearFilter()
        }
    }
    Decoration(decoration = "pdx://toolbar/title") {
        AnimatedContent(targetState = isVisible) { visible ->
            if (visible) {
                SearchField(
                    text = viewModel.state.query.text,
                    onTextChanged = { viewModel.updateTextQuery(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(MaterialTheme.grid.x5)
                )
            } else {
                Text(
                    text = stringResource(id = R.string.pokemon_list_title), modifier = Modifier
                )
            }
        }
    }
    AnimatedVisibility(
        visible = isVisible, enter = expandVertically(expandFrom = Alignment.Top), exit = shrinkVertically(shrinkTowards = Alignment.Top)
    ) {
        Column {
            Row(
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.grid.x1)
            ) {
                if (viewModel.state.showTypeFilterDialog) {
                    TypeFilterDialog(onClear = { viewModel.hideTypeFilterDialog(clearTypeFilters = true) },
                        onOk = { viewModel.hideTypeFilterDialog(clearTypeFilters = false) },
                        onItemClicked = { viewModel.onTypeSelected(it) },
                        selected = viewModel.state.query.selectedTypes
                    )
                }
                FilterChip(selected = false,
                    onClick = { viewModel.showTypeFilterDialog() },
                    colors = typeFilterColors(viewModel.state.query.selectedTypes),
                    label = { Text(text = stringResource(id = R.string.pokemon_list_query_type_button)) })
                FilterChip(selected = false,
                    onClick = { viewModel.toggleOnlyFilters() },
                    colors = favoriteFilterColors(viewModel.state.query.onlyFavorites),
                    label = { Text(text = stringResource(id = R.string.pokemon_list_query_favorite_button)) })
            }
        }
    }
}

@Composable
private fun typeFilterColors(selectedTypes: List<PokemonType>) = if (selectedTypes.isEmpty()) {
    FilterChipDefaults.filterChipColors()
} else {
    FilterChipDefaults.filterChipColors(
        containerColor = MaterialTheme.colorScheme.primaryContainer
    )
}

@Composable
private fun favoriteFilterColors(isEnabled: Boolean) = if (isEnabled) {
    FilterChipDefaults.filterChipColors(
        containerColor = MaterialTheme.colorScheme.primaryContainer
    )
} else {
    FilterChipDefaults.filterChipColors()
}
