package io.github.lexadiky.pdx.feature.pokemon.details.subpage.info

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import io.github.lexadiky.akore.alice.robo.di
import io.github.lexadiky.akore.alice.robo.viewModel
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonDetails
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonSpeciesDetails
import io.github.lexadiky.pdx.feature.pokemon.details.entitiy.PokemonPhysicalDimension
import io.github.lexadiky.pdx.lib.errorhandler.ErrorDialog
import io.github.lexadiky.pdx.ui.uikit.resources.render
import io.github.lexadiky.pdx.ui.uikit.theme.grid

@Composable
internal fun InfoSubPage(
    pokemonSpeciesDetails: PokemonSpeciesDetails?,
    pokemonDetails: PokemonDetails?
) {
    if (pokemonSpeciesDetails != null && pokemonDetails != null) {
        InfoSubPageImpl(di.viewModel(pokemonSpeciesDetails, pokemonDetails))
    }
}

@Composable
private fun InfoSubPageImpl(
    viewModel: InfoSubPageViewModel
) {
    ErrorDialog(viewModel.state.error) {
        viewModel.hideError()
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.grid.x2),
        modifier = Modifier.padding(MaterialTheme.grid.x2)
    ) {
        ElevatedCard(
            modifier = Modifier.fillMaxWidth()
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

        viewModel.state.descriptions.forEachIndexed { index, description ->
            Column {
                Text(
                    text = description.title.render(),
                    style = MaterialTheme.typography.titleMedium
                )
                Text(text = description.text.render())
            }
            if (viewModel.state.descriptions.lastIndex != index) {
                Divider()
            }
        }
    }
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