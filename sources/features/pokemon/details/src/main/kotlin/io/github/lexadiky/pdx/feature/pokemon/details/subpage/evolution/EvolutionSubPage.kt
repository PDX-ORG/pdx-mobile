package io.github.lexadiky.pdx.feature.pokemon.details.subpage.evolution

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.lexadiky.akore.alice.robo.di
import io.github.lexadiky.akore.alice.robo.viewModel
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonDetails
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonSpeciesDetails
import io.github.lexadiky.pdx.feature.pokemon.details.subpage.evolution.entity.EvolutionLinkPokemonVR
import io.github.lexadiky.pdx.feature.pokemon.details.subpage.evolution.widget.EvolutionMethodWidget
import io.github.lexadiky.pdx.library.arc.Page
import io.github.lexadiky.pdx.ui.uikit.resources.ImageTransformation
import io.github.lexadiky.pdx.ui.uikit.resources.render
import io.github.lexadiky.pdx.ui.uikit.theme.grid
import io.github.lexadiky.pdx.ui.uikit.widget.SmallWikiPreview

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
private fun EvolutionSubPageImpl(vm: EvolutionSubPageSocket) = Page(vm) { state, act ->
    Column(
        modifier = Modifier
            .padding(MaterialTheme.grid.x2),

    ) {
        state.evolvesFrom?.let { link ->
            PokemonPreview(link.pokemon, act)
            EvolutionMethodWidget(link.method)
        }
        state.current?.let { pokemon ->
            PokemonPreview(pokemon, act)
        }
        if (state.isSingleToSpecies) {
            state.evolvesTo.first().let { link ->
                EvolutionMethodWidget(link.method)
                PokemonPreview(link.pokemon, act)
            }
        }
    }
}

@Composable
private fun PokemonPreview(pokemon: EvolutionLinkPokemonVR, act: (EvolutionSubPageAction) -> Unit) {
    SmallWikiPreview(
        title = pokemon.localName.render(),
        icon = pokemon.image.render(
            transformations = listOf(
                ImageTransformation.CropTransparent
            )
        ),
        onClick = { act(EvolutionSubPageAction.Navigate.PokemonDetails(pokemon.speciesId)) },
        colors = CardDefaults.elevatedCardColors(),
    )
}
