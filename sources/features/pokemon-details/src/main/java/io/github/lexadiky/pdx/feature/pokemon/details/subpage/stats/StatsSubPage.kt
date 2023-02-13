package io.github.lexadiky.pdx.feature.pokemon.details.subpage.stats

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import io.github.lexadiky.akore.alice.robo.di
import io.github.lexadiky.akore.alice.robo.inject
import io.github.lexadiky.akore.alice.robo.viewModel
import io.github.lexadiky.pdx.domain.pokemon.asset.assets
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonDetails
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonSpeciesDetails
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonStat
import io.github.lexadiky.pdx.feature.pokemon.details.R
import io.github.lexadiky.pdx.ui.uikit.resources.render
import io.github.lexadiky.pdx.ui.uikit.theme.animation
import io.github.lexadiky.pdx.ui.uikit.theme.circular
import io.github.lexadiky.pdx.ui.uikit.theme.grid

@Composable
internal fun StatsSubPage(
    pokemonSpeciesDetails: PokemonSpeciesDetails?,
    selectedVariety: PokemonDetails?,
) {
    if (pokemonSpeciesDetails == null || selectedVariety == null) {
        return
    }
    StatsSubPageImpl(
        viewModel = di.viewModel(
            pokemonSpeciesDetails,
            selectedVariety
        )
    )
}

@Composable
private fun StatsSubPageImpl(viewModel: StatsSubPageViewModel) {
    Column(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.grid.x2),
        modifier = Modifier
            .padding(MaterialTheme.grid.x2)
            .wrapContentHeight()
            .fillMaxWidth()
    ) {
        viewModel.state.baseStats.forEach { (type, value) ->
            StatBar(
                stat = type,
                value = value,
                color = viewModel.state.types.firstOrNull()?.assets?.color?.render()
                    ?: Color.Transparent
            )
        }
        Text(
            text = stringResource(
                R.string.feature_pokemon_details_section_stats_total_value,
                viewModel.state.totalBaseStatValue
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun StatBar(stat: PokemonStat, value: Int, color: Color) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.grid.x2),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stat.assets.shortTitle.render(),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth(0.2f)
        )
        Box(
            modifier = Modifier
                .clip(MaterialTheme.shapes.circular)
                .background(MaterialTheme.colorScheme.secondary.copy(alpha = 0.5f))
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            val anim = remember { Animatable(0.1f) }
            LaunchedEffect(value) {
                anim.animateTo(value / 225f, MaterialTheme.animation.linearSlow())
            }

            Box(
                modifier = Modifier
                    .clip(MaterialTheme.shapes.circular)
                    .background(color)
                    .fillMaxWidth(anim.value)
                    .wrapContentHeight()
            ) {
                Text(
                    text = value.toString(),
                    maxLines = 1,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }
    }
}
