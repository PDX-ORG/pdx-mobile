package io.github.lexadiky.pdx.feature.pokemon.details.subpage.evolution

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import io.github.lexadiky.akore.alice.robo.di
import io.github.lexadiky.akore.alice.robo.viewModel
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonDetails
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonSpeciesDetails
import io.github.lexadiky.pdx.feature.pokemon.details.subpage.evolution.entity.EvolutionLinkPokemonVR
import io.github.lexadiky.pdx.lib.arc.Page
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
        }
        EvolutionMethod()
        state.current?.let { pokemon ->
            PokemonPreview(pokemon, act)
        }
        if (state.isSingleToSpecies) {
            EvolutionMethod()
            state.evolvesTo.first().let { link ->
                PokemonPreview(link.pokemon, act)
            }
        }
    }
}

@Composable
private fun EvolutionMethod() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = MaterialTheme.grid.x2)
                .fillMaxHeight()
                .background(MaterialTheme.colorScheme.outline)
                .wrapContentSize(Alignment.TopStart)
                .width(MaterialTheme.grid.s1)
        )
        Text(
            text = "Evolves at level 20",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = MaterialTheme.grid.x2,
                    bottom = MaterialTheme.grid.x2,
                    end = MaterialTheme.grid.x2
                )
                .weight(1f)
        )
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
