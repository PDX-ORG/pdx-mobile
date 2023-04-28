package io.github.lexadiky.pdx.feature.pokemon.details.subpage.evolution

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import io.github.lexadiky.akore.alice.robo.di
import io.github.lexadiky.akore.alice.robo.viewModel
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonDetails
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonSpeciesDetails

@Composable
internal fun EvolutionSubPage(
    pokemonSpeciesDetails: PokemonSpeciesDetails?,
    pokemonDetails: PokemonDetails?
) {
    if (pokemonSpeciesDetails != null && pokemonDetails != null) {
        EvolutionSubPageImpl(di.viewModel(pokemonSpeciesDetails, pokemonDetails))
    }
}

@Composable
private fun EvolutionSubPageImpl(vm: EvolutionSubPageViewModel) {
    Text(text = "Evolution Info: ${vm.state.id}")
}
