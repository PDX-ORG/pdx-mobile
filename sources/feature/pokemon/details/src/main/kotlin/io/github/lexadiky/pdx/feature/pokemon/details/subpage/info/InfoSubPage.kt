package io.github.lexadiky.pdx.feature.pokemon.details.subpage.info

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import io.github.lexadiky.akore.alice.robo.di
import io.github.lexadiky.akore.alice.robo.viewModel
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonDetails
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonSpeciesDetails
import io.github.lexadiky.pdx.feature.pokemon.details.entitiy.PokemonPhysicalDimension
import io.github.lexadiky.pdx.library.core.lce.Lce
import io.github.lexadiky.pdx.library.core.lce.contentOrNull
import io.github.lexadiky.pdx.library.errorhandler.ErrorDialog
import io.github.lexadiky.pdx.library.uikit.resources.render
import io.github.lexadiky.pdx.library.uikit.theme.grid
import io.github.lexadiky.pdx.library.uikit.theme.transparent
import io.github.lexadiky.pdx.library.uikit.widget.PlaceholderDefaults
import io.github.lexadiky.pdx.library.uikit.widget.placeholder

@Composable
internal fun InfoSubPage(
    pokemonSpeciesDetails: PokemonSpeciesDetails?,
    pokemonDetails: PokemonDetails?,
) {
    if (pokemonSpeciesDetails != null && pokemonDetails != null) {
        InfoSubPageImpl(di.viewModel(pokemonSpeciesDetails, pokemonDetails))
    }
}

private const val ITEM_KEY_TOP_SPACER = "ITEM_KEY_TOP_SPACER"

@Composable
private fun InfoSubPageImpl(
    viewModel: InfoSubPageViewModel,
) {
    ErrorDialog(viewModel.state.error) {
        viewModel.hideError()
    }

    Column {
        val configuration = LocalConfiguration.current

        LazyColumn(
            contentPadding = PaddingValues(vertical = MaterialTheme.grid.x2),
            modifier = Modifier
                .heightIn(max = configuration.screenHeightDp.dp)
        ) {
            item {
                ElevatedCard(
                    modifier = Modifier
                        .padding(horizontal = MaterialTheme.grid.x2)
                        .fillMaxWidth()
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier
                            .padding(MaterialTheme.grid.x2)
                            .fillMaxWidth()
                    ) {
                        viewModel.state.dimensions.forEach { dimension ->
                            DimensionItem(dimension = dimension)
                        }
                    }
                }
            }
            item(ITEM_KEY_TOP_SPACER) {
                Box(modifier = Modifier.height(MaterialTheme.grid.x2))
            }
            itemsIndexed(
                items = viewModel.state.descriptions,
                key = { index, item ->
                    item.contentOrNull()?.artificialId ?: index
                }
            ) { index, lce ->
                Crossfade(
                    label = "description-crossfade",
                    targetState = lce
                ) { item ->
                    when (item) {
                        Lce.Loading -> DescriptionPlacholder()
                        is Lce.Content -> ListItem(
                            headlineContent = { Text(text = item.value.title.render()) },
                            supportingContent = { Text(text = item.value.text.render()) },
                            colors = ListItemDefaults.colors(
                                containerColor = MaterialTheme.colorScheme.transparent
                            )
                        )
                        else -> Unit
                    }
                }
            }
        }
    }
}

@Composable
private fun DescriptionPlacholder() {
    ListItem(
        headlineContent = {
            Text(
                text = "",
                modifier = Modifier
                    .fillMaxWidth(PlaceholderDefaults.Width.Medium)
                    .placeholder(true, PlaceholderDefaults.ShrinkedTextHeight)
            )
        },
        supportingContent = {
            Column {
                Text(
                    text = "",
                    modifier = Modifier
                        .fillMaxWidth(PlaceholderDefaults.Width.Large)
                        .placeholder(
                            true,
                            PlaceholderDefaults.ShrinkedTextHeight
                        )
                )
                Text(
                    text = "",
                    modifier = Modifier
                        .fillMaxWidth(PlaceholderDefaults.Width.Big)
                        .placeholder(
                            true,
                            PlaceholderDefaults.ShrinkedTextHeight
                        )
                )
            }
        },
        colors = ListItemDefaults.colors(
            containerColor = MaterialTheme.colorScheme.transparent
        )
    )
}

@Composable
private fun DimensionItem(dimension: PokemonPhysicalDimension) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = dimension.icon.render(),
            contentDescription = null
        )
        Text(text = dimension.label.render())
    }
}
