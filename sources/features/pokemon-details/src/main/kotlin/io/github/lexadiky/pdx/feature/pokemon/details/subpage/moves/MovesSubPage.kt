package io.github.lexadiky.pdx.feature.pokemon.details.subpage.moves

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AssistChip
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.placeholder
import io.github.lexadiky.akore.alice.robo.di
import io.github.lexadiky.akore.alice.robo.viewModel
import io.github.lexadiky.pdx.domain.pokemon.asset.assets
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonDetails
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonSpeciesDetails
import io.github.lexadiky.pdx.feature.pokemon.details.entitiy.PokemonMoveData
import io.github.lexadiky.pdx.lib.core.lce.Lce
import io.github.lexadiky.pdx.ui.uikit.resources.render
import io.github.lexadiky.pdx.ui.uikit.theme.circular
import io.github.lexadiky.pdx.ui.uikit.theme.grid
import io.github.lexadiky.pdx.ui.uikit.theme.transparent

@Composable
internal fun MovesSubPage(
    pokemonSpeciesDetails: PokemonSpeciesDetails?,
    pokemonDetails: PokemonDetails?,
) {
    if (pokemonSpeciesDetails != null && pokemonDetails != null) {
        MovesSubPageImpl(di.viewModel(pokemonSpeciesDetails, pokemonDetails))
    }
}

@Composable
private fun MovesSubPageImpl(viewModel: MovesSubPageViewModel) {
    val moves by viewModel.state.movesSorted.collectAsState(initial = emptyList())
    val configuration = LocalConfiguration.current
    LazyColumn(
        modifier = Modifier
            .sizeIn(maxHeight = configuration.screenHeightDp.dp)
    ) {
        item {
            ControlPanel()
        }
        items(moves) { lce ->
            Column {
                when (lce) {
                    is Lce.Content ->
                        MoveCard(
                            move = lce.value,
                            onItemClick = { viewModel.onMoveClicked(lce.value) },
                            onTypeClick = { viewModel.onTypeClicked(lce.value.type) }
                        )
                    else -> Unit
                }
            }
        }
    }
}

@Composable
private fun ControlPanel() {
    LazyRow(
        contentPadding = PaddingValues(
            start = MaterialTheme.grid.x2,
            end = MaterialTheme.grid.x2,
            top = MaterialTheme.grid.x1
        )
    ) {
        item {
            var isMenuOpen by remember { mutableStateOf(false) }
            AssistChip(
                onClick = { isMenuOpen = !isMenuOpen },
                label = { Text(text = "Sort by") }

            )

            DropdownMenu(
                expanded = isMenuOpen,
                onDismissRequest = { isMenuOpen = false },

            ) {
                repeat(10) {
                    DropdownMenuItem(text = { Text(text = "My Option") }, onClick = { /*TODO*/ })
                }
            }
        }
    }
}

@Composable
private fun MoveCard(
    move: PokemonMoveData,
    onItemClick: () -> Unit,
    onTypeClick: () -> Unit
) {
    ListItem(
        headlineContent = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.grid.x1)
            ) {
                Text(text = move.localeName.render())
            }
        },
        leadingContent = {
            Image(
                painter = move.type.assets.icon.render(),
                contentDescription = null,
                modifier = Modifier
                    .size(MaterialTheme.grid.x4)
                    .clip(MaterialTheme.shapes.circular)
                    .clickable { onTypeClick() }
            )
        },
        trailingContent = {
            Text(
                text = move.ppLabel.render(),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
        },
        supportingContent = {
            Text(
                text = move.localeFlavourText.render(),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        },
        colors = ListItemDefaults.colors(
            containerColor = MaterialTheme.colorScheme.transparent
        ),
        modifier = Modifier
            .clickable { onItemClick() }
    )
}
