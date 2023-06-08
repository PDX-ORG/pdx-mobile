package io.github.lexadiky.pdx.feature.type.details

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow
import io.github.lexadiky.akore.alice.robo.DIFeature
import io.github.lexadiky.akore.alice.robo.di
import io.github.lexadiky.akore.alice.robo.viewModel
import io.github.lexadiky.pdx.domain.pokemon.asset.assets
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonType
import io.github.lexadiky.pdx.feature.type.details.entity.TypePokemonPreview
import io.github.lexadiky.pdx.library.errorhandler.ErrorDialog
import io.github.lexadiky.pdx.library.uikit.resources.ImageTransformation
import io.github.lexadiky.pdx.library.uikit.resources.render
import io.github.lexadiky.pdx.library.uikit.theme.grid
import io.github.lexadiky.pdx.library.uikit.theme.pdx
import io.github.lexadiky.pdx.library.uikit.widget.PillChip
import io.github.lexadiky.pdx.library.uikit.widget.PillChipDefaults
import io.github.lexadiky.pdx.library.uikit.widget.SmallWikiPreview

@Composable
fun TypeDetailsPage(typeId: String) {
    DIFeature(TypeDetailsModule) {
        TypeDetailsPageImpl(viewModel = di.viewModel(typeId))
    }
}

private const val MIN_HEIGHT_DP = 400

@SuppressLint("RestrictedApi")
@Composable
internal fun TypeDetailsPageImpl(viewModel: TypeDetailsViewModel) {
    ErrorDialog(viewModel.state.error) {
        viewModel.hideError()
    }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.grid.x2),
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = MIN_HEIGHT_DP.dp)
            .padding(MaterialTheme.grid.x2)
    ) {
        item {
            Text(
                text = viewModel.state.type.assets.title.render(),
                style = MaterialTheme.typography.titleLarge
            )
        }

        item {
            viewModel.state.damageTable?.let { ddt ->
                DamageChart(
                    title = stringResource(id = R.string.type_damage_section_to),
                    table = ddt.to,
                    onTypeClicked = { viewModel.openTypeDetails(it) }
                )
            }
        }

        item {
            viewModel.state.damageTable?.let { ddt ->
                DamageChart(
                    title = stringResource(id = R.string.type_damage_section_from),
                    table = ddt.from,
                    onTypeClicked = { viewModel.openTypeDetails(it) }
                )
            }
        }

        item {
            Spacer(modifier = Modifier.size(MaterialTheme.grid.x2))
        }

        item {
            FeaturedPokemonSection(
                state = viewModel.state,
                openPokemonList = { viewModel.openPokemonList() },
                openPokemonDetails = { viewModel.openPokemonDetails(it) }
            )
        }

        item {
            MovesListLink(
                openMovesList = { viewModel.openMovesList() }
            )
        }

        item {
            Spacer(modifier = Modifier.size(MaterialTheme.grid.x4))
        }
    }
}

private const val MOVES_LINK_WIDTH_RATIO = 0.85f

@Composable
private fun MovesListLink(
    openMovesList: () -> Unit,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(
            onClick = { openMovesList() },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier.fillMaxWidth(MOVES_LINK_WIDTH_RATIO)
        ) {
            Text(
                text = stringResource(id = R.string.type_moves_button)
            )
        }
    }
}

@Composable
private fun FeaturedPokemonSection(
    state: TypeDetailsState,
    openPokemonList: () -> Unit,
    openPokemonDetails: (TypePokemonPreview) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.grid.x1)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.type_featured_pokemon_title),
                style = MaterialTheme.typography.titleMedium,
            )
            TextButton(onClick = { openPokemonList() }) {
                Text(
                    text = stringResource(id = R.string.type_featured_pokemon_link),
                    style = MaterialTheme.typography.titleMedium
                        .copy(color = MaterialTheme.colorScheme.pdx.link)
                )
            }
        }
        state.featuredPokemon.forEach { preview ->
            SmallWikiPreview(
                title = preview.name.render(),
                icon = preview.image.render(
                    transformations = listOf(ImageTransformation.CropTransparent)
                ),
                onClick = { openPokemonDetails(preview) },
            )
        }
    }
}

@Composable
internal fun DamageChart(
    title: String,
    table: Map<PokemonType, Float>,
    onTypeClicked: (PokemonType) -> Unit,
    modifier: Modifier = Modifier
) {
    if (table.isNotEmpty()) {
        Column(
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.grid.x1),
            modifier = modifier
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
            )
            FlowRow(
                mainAxisSpacing = MaterialTheme.grid.x1,
                crossAxisSpacing = MaterialTheme.grid.x1,
            ) {
                table.forEach { (type, value) ->
                    val labelColor = type.assets.color.render()
                    PillChip(
                        label = { Text(text = type.assets.title.render()) },
                        labelColor = labelColor,
                        trail = { Text(text = stringResource(id = R.string.type_damage_modifier, value)) },
                        trailColor = PillChipDefaults.trailColor(labelColor),
                        textColor = MaterialTheme.colorScheme.onError,
                        onClick = { onTypeClicked(type) }
                    )
                }
            }
        }
    }
}
