package io.github.lexadiky.pdx.feature.pokemon.details.subpage.moves

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import io.github.lexadiky.akore.alice.robo.di
import io.github.lexadiky.akore.alice.robo.viewModel
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonDetails
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonMove
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonSpeciesDetails
import io.github.lexadiky.pdx.feature.pokemon.details.entitiy.PokemonMoveData
import io.github.lexadiky.pdx.lib.core.lce.Lce
import io.github.lexadiky.pdx.ui.uikit.theme.grid

@Composable
internal fun MovesSubPage(
    pokemonSpeciesDetails: PokemonSpeciesDetails?,
    pokemonDetails: PokemonDetails?
) {
    if (pokemonSpeciesDetails != null && pokemonDetails != null) {
        MovesSubPageImpl(di.viewModel(pokemonSpeciesDetails, pokemonDetails))
    }
}

@Composable
private fun MovesSubPageImpl(viewModel: MovesSubPageViewModel) {
    val moves by viewModel.state.moves.collectAsState(initial = emptyList())
    val configuration = LocalConfiguration.current
    LazyColumn(
        modifier = Modifier
            .sizeIn(maxHeight = configuration.screenHeightDp.dp)
    ) {
        items(moves) { lce ->
            Column {
                when (lce) {
                    is Lce.Content -> MoveCard(lce.value)
                    is Lce.Error -> Text(text = "Error")
                    Lce.Loading -> Text(text = "Loading")
                }
                Divider(modifier = Modifier.padding(MaterialTheme.grid.x2))
            }
        }
    }
}

@Composable
private fun MoveCard(move: PokemonMoveData) {
    Text(text = move.localeName)
}
