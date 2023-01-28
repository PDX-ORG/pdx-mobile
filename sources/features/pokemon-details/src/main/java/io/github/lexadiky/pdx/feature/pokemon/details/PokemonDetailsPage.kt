package io.github.lexadiky.pdx.feature.pokemon.details

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import io.github.lexadiky.akore.alice.robo.DIFeature
import io.github.lexadiky.akore.alice.robo.di
import io.github.lexadiky.akore.alice.robo.inject
import io.github.lexadiky.pdx.lib.navigation.page.PageContext
import io.github.lexadiky.pdx.ui.uikit.resources.render

@Composable
fun PageContext.PokemonDetailsPage(pokemonId: String) {
    DIFeature(PokemonDetailsModule) {
        PokemonDetailsPageImpl(di.inject(pokemonId))
    }
}

@Composable
private fun PokemonDetailsPageImpl(viewModel: PokemonDetailsViewModel) {
    Text(text = "PKMN: ${viewModel.state.name.render().value}")
}