@file:OptIn(ExperimentalMaterial3Api::class)

package io.github.lexadiky.pdx.feature.pokemon.list.ui

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.google.accompanist.flowlayout.FlowRow
import io.github.lexadiky.pdx.domain.pokemon.asset.assets
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonType
import io.github.lexadiky.pdx.feature.pokemon.list.R
import io.github.lexadiky.pdx.library.uikit.resources.render
import io.github.lexadiky.pdx.library.uikit.theme.grid

@Composable
internal fun TypeFilterDialog(
    selected: List<PokemonType>,
    onItemClicked: (PokemonType) -> Unit,
    onClear: () -> Unit,
    onOk: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { onOk() },
        confirmButton = {
            TextButton(onClick = onOk) {
                Text(text = stringResource(id = R.string.pokemon_list_type_filter_ok))
            }
        },
        dismissButton = {
            TextButton(onClick = onClear) {
                Text(text = stringResource(id = R.string.pokemon_list_type_filter_clear))
            }
        },
        text = {
            FlowRow(mainAxisSpacing = MaterialTheme.grid.x1) {
                PokemonType.values().forEach { type ->
                    FilterChip(
                        selected = type in selected,
                        onClick = { onItemClicked(type) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = type.assets.color.render(),
                            selectedLabelColor = MaterialTheme.colorScheme.onError
                        ),
                        label = { Text(text = type.assets.title.render()) }
                    )
                }
            }
        }
    )
}
