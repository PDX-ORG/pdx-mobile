package io.github.lexadiky.pdx.feature.pokemon.details.subpage.moves

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import io.github.lexadiky.akore.alice.robo.di
import io.github.lexadiky.akore.alice.robo.viewModel
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonDetails
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonSpeciesDetails
import io.github.lexadiky.pdx.lib.core.lce.Lce

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
    Column {
        val moves by viewModel.state.moves.collectAsState(initial = emptyList())
        moves.forEach { lce ->
            when (lce) {
                is Lce.Content -> Text(text = lce.value.name)
                is Lce.Error -> Text(text = "Error")
                Lce.Loading -> Text(text = "Loading")
            }

        }
    }
}
