package io.github.lexadiky.pdx.feature.pokemon.details.subpage.moves

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import io.github.lexadiky.akore.alice.robo.di
import io.github.lexadiky.akore.alice.robo.viewModel
import io.github.lexadiky.pdx.domain.pokemon.asset.assets
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonSpeciesDetails
import io.github.lexadiky.pdx.feature.pokemon.details.entitiy.PokemonMoveData
import io.github.lexadiky.pdx.feature.pokemon.details.entitiy.move.MoveSort
import io.github.lexadiky.pdx.feature.pokemon.details.subpage.moves.sort.MoveSortWidget
import io.github.lexadiky.pdx.lib.core.lce.Lce
import io.github.lexadiky.pdx.lib.core.lce.contentOrNull
import io.github.lexadiky.pdx.lib.uikit.R
import io.github.lexadiky.pdx.ui.uikit.resources.render
import io.github.lexadiky.pdx.ui.uikit.theme.circular
import io.github.lexadiky.pdx.ui.uikit.theme.grid
import io.github.lexadiky.pdx.ui.uikit.theme.transparent
import io.github.lexadiky.pdx.ui.uikit.widget.PlaceholderDefaults
import io.github.lexadiky.pdx.ui.uikit.widget.SearchField
import io.github.lexadiky.pdx.ui.uikit.widget.placeholder

@Composable
internal fun MovesSubPage(pokemonSpeciesDetails: PokemonSpeciesDetails?) {
    if (pokemonSpeciesDetails != null) {
        MovesSubPageImpl(di.viewModel(pokemonSpeciesDetails))
    }
}

private const val EMPTY_PLACEHOLDER_SIZE = 10

@Composable
private fun MovesSubPageImpl(viewModel: MovesSubPageViewModel) {
    val moves by viewModel.state.moves.collectAsState(initial = emptyList())
    val configuration = LocalConfiguration.current
    LazyColumn(
        modifier = Modifier
            .sizeIn(maxHeight = configuration.screenHeightDp.dp)
    ) {
        item {
            ControlPanel(
                searchQuery = viewModel.state.filter.query,
                onSortUpdated = { viewModel.onSortUpdated(it) },
                onSearchQueryUpdated = { viewModel.onQueryUpdated(it) }
            )
        }
        if (moves.isNotEmpty())  {
            itemsIndexed(
                items = moves,
                key = { idx, lce -> lce.contentOrNull()?.name ?: idx }
            ) { idx, lce ->
                Column {
                    Crossfade(
                        targetState = lce,
                        label = "move-card-cross-fade"
                    ) { currentLce ->
                        when (currentLce) {
                            is Lce.Content ->
                                MoveCard(
                                    move = currentLce.value,
                                    onItemClick = { viewModel.onMoveClicked(currentLce.value) },
                                    onTypeClick = { viewModel.onTypeClicked(currentLce.value.type) }
                                )

                            is Lce.Loading -> MoveCardPlaceholder()
                            else -> Unit
                        }
                    }
                }
            }
        } else {
            items(EMPTY_PLACEHOLDER_SIZE) {
                MoveCardPlaceholder()
            }
        }
    }
}

@Composable
private fun ControlPanel(
    searchQuery: String,
    onSortUpdated: (MoveSort) -> Unit,
    onSearchQueryUpdated: (String) -> Unit
) {
    Column {
        SearchField(
            text = searchQuery,
            onTextChanged = { onSearchQueryUpdated(it) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = MaterialTheme.grid.x2,
                    vertical = MaterialTheme.grid.x1
                )
        )
        LazyRow(
            contentPadding = PaddingValues(
                start = MaterialTheme.grid.x2,
                end = MaterialTheme.grid.x2,
            )
        ) {
            item {
                MoveSortWidget(onUpdated = onSortUpdated)
            }
        }
    }
}

@Composable
private fun MoveCard(
    move: PokemonMoveData,
    onItemClick: () -> Unit,
    onTypeClick: () -> Unit,
) {
    ListItem(
        headlineContent = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.grid.x1)
            ) {
                Text(text = move.localeName.render())
                Row(
                    horizontalArrangement = Arrangement.spacedBy(MaterialTheme.grid.x05)
                ) {
                    if (move.power != null) {
                        MoveStatLabel(
                            icon = painterResource(id = R.drawable.uikit_ic_swords),
                            label = move.powerLabel.render()
                        )
                    }
                    if (move.accuracy != null) {
                        MoveStatLabel(
                            icon = painterResource(id = R.drawable.uikit_ic_accuracy),
                            label = move.accuracyLabel.render()
                        )
                    }
                }
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
                text = move.localeEffectText.render(),
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

@Composable
private fun MoveStatLabel(icon: Painter, label: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = icon,
            contentDescription = null,
            modifier = Modifier.size(MaterialTheme.grid.x2)
        )

        Text(
            text = label,
            maxLines = 1
        )
    }
}

@Composable
@Suppress("MagicNumber")
private fun MoveCardPlaceholder() {
    ListItem(
        headlineContent = {
            Text(
                text = "",
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .placeholder(true, PlaceholderDefaults.ShrinkedTextHeight)
            )
        },
        leadingContent = {
            Box(
                modifier = Modifier
                    .size(MaterialTheme.grid.x4)
                    .placeholder(true, shape = MaterialTheme.shapes.circular)
            )
        },
        trailingContent = {
            Text(
                text = "25",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.placeholder(true)
            )
        },
        supportingContent = {
            Column {
                Text(
                    text = "",
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .placeholder(true, PlaceholderDefaults.ShrinkedTextHeight)
                )
            }

        },
        colors = ListItemDefaults.colors(
            containerColor = MaterialTheme.colorScheme.transparent
        ),
    )
}