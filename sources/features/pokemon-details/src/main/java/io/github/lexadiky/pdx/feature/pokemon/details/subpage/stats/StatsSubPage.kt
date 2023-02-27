@file:OptIn(ExperimentalMaterial3Api::class)

package io.github.lexadiky.pdx.feature.pokemon.details.subpage.stats

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import io.github.lexadiky.akore.alice.robo.di
import io.github.lexadiky.akore.alice.robo.viewModel
import io.github.lexadiky.pdx.domain.pokemon.asset.assets
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonDetails
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonSpeciesDetails
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonStat
import io.github.lexadiky.pdx.feature.pokemon.details.R
import io.github.lexadiky.pdx.feature.pokemon.details.entitiy.PokemonAbilityData
import io.github.lexadiky.pdx.ui.uikit.resources.render
import io.github.lexadiky.pdx.ui.uikit.theme.animation
import io.github.lexadiky.pdx.ui.uikit.theme.circular
import io.github.lexadiky.pdx.ui.uikit.theme.grid
import io.github.lexadiky.pdx.ui.uikit.util.saturation
import io.github.lexadiky.pdx.ui.uikit.widget.PillChip

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
            .padding(vertical = MaterialTheme.grid.x2)
            .wrapContentHeight()
            .fillMaxWidth()
    ) {
        val anchorColor = viewModel.state.types.firstOrNull()?.assets?.color?.render() ?: Color.Transparent
        viewModel.state.baseStats.forEach { (type, value) ->
            StatBar(
                stat = type,
                value = value,
                color = anchorColor,
                modifier = Modifier.padding(horizontal = MaterialTheme.grid.x2)
            )
        }
        StatInfoBar(
            state = viewModel.state,
            anchorColor = anchorColor,
            modifier = Modifier.padding(horizontal = MaterialTheme.grid.x2)
        )
        Divider(
            modifier = Modifier.padding(horizontal = MaterialTheme.grid.x2)
        )
        AbilityBar(
            state = viewModel.state,
            onClick = { viewModel.openAbilityDetails(it) }
        )
    }
}

@Composable
private fun StatInfoBar(
    state: StatsSubPageState,
    anchorColor: Color,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.grid.x2),
        modifier = modifier
    ) {
        val archetypeTitle = state.archetype?.assets?.title

        PillChip(
            label = { Text(stringResource(R.string.feature_pokemon_details_section_stats_total_value)) },
            labelColor = anchorColor,
            trail = { Text(text = state.totalBaseStatValue.toString()) },
            trailColor = anchorColor.saturation(),
            textColor = MaterialTheme.colorScheme.onPrimary
        )

        archetypeTitle?.let { title ->
            PillChip(
                label = { Text(stringResource(R.string.feature_pokemon_details_section_stats_archetype)) },
                labelColor = anchorColor,
                trail = { Text(text = title.render()) },
                trailColor = anchorColor.saturation(),
                textColor = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun AbilityBar(state: StatsSubPageState, onClick: (PokemonAbilityData) -> Unit) {
    Column(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.grid.x1)
    ) {
        state.abilities.forEach { ability ->
            AbilityItem(
                title = ability.localeName.render(),
                subtitle = ability.type.render(),
                onClick = { onClick.invoke(ability) }
            )
        }
    }
}

@Composable
private fun AbilityItem(title: String, subtitle: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier.padding(horizontal = MaterialTheme.grid.x2)
    ) {
        ElevatedCard(
            onClick = onClick
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = MaterialTheme.grid.x2,
                        top = MaterialTheme.grid.x1,
                        bottom = MaterialTheme.grid.x1
                    )
            ) {
                Column {
                    Text(text = title, fontWeight = FontWeight.SemiBold)
                    Text(text = subtitle, style = MaterialTheme.typography.bodyMedium)
                }
                IconButton(onClick = { onClick() }) {
                    Icon(painter = rememberVectorPainter(Icons.Outlined.Info), contentDescription = null)
                }
            }
        }
    }
}

@Composable
private fun StatBar(
    stat: PokemonStat,
    value: Int,
    color: Color,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.grid.x2),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
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
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }
    }
}
