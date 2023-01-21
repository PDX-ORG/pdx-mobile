package io.github.lexadiky.pdx.feature.type.details

import android.annotation.SuppressLint
import androidx.compose.foundation.background
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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow
import com.google.android.material.color.utilities.Scheme
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonType
import io.github.lexadiky.pdx.lib.arc.di.DIFeature
import io.github.lexadiky.pdx.lib.arc.di.di
import io.github.lexadiky.pdx.lib.errorhandler.ErrorDialog
import io.github.lexadiky.pdx.ui.uikit.resources.ImageTransformation
import io.github.lexadiky.pdx.ui.uikit.resources.render
import io.github.lexadiky.pdx.ui.uikit.resources.renderNow
import io.github.lexadiky.pdx.ui.uikit.theme.grid
import io.github.lexadiky.pdx.ui.uikit.theme.pdx
import io.github.lexadiky.pdx.ui.uikit.util.saturation
import io.github.lexadiky.pdx.ui.uikit.util.toColorScheme
import io.github.lexadiky.pdx.ui.uikit.widget.PillChip
import io.github.lexadiky.pdx.ui.uikit.widget.PillChipDefaults
import io.github.lexadiky.pdx.ui.uikit.widget.SmallWikiPreview

@Composable
fun TypeDetailsPage(typeId: String) {
    DIFeature(TypeDetailsModule) {
        TypeDetailsPageImpl(viewModel = di.inject(typeId))
    }
}

@SuppressLint("RestrictedApi")
@Composable
internal fun TypeDetailsPageImpl(viewModel: TypeDetailsViewModel) {
    val primaryColor = viewModel.state.type.toColorResource().render()

    val colorScheme = remember(viewModel.state) {
        Scheme.light(primaryColor.toArgb())
            .toColorScheme()
    }

    ErrorDialog(viewModel.state.error) {
        viewModel.hideError()
    }

    val backgroundBrush = Brush.linearGradient(
        colors = listOf(
            primaryColor.saturation(1.2f),
            MaterialTheme.colorScheme.surface
        )
    )
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.grid.x2),
        modifier = Modifier
            .background(backgroundBrush)
            .fillMaxWidth()
            .defaultMinSize(minHeight = 400.dp)
            .padding(MaterialTheme.grid.x2)
    ) {
        item {
            Text(
                text = viewModel.state.type.toStringResource().renderNow(),
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
                    TextButton(onClick = { viewModel.openPokemonList() }) {
                        Text(
                            text = stringResource(id = R.string.type_featured_pokemon_link),
                            style = MaterialTheme.typography.titleMedium
                                .copy(color = MaterialTheme.colorScheme.pdx.link)
                        )
                    }
                }
                viewModel.state.featuredPokemon.forEach { preview ->
                    SmallWikiPreview(
                        title = preview.name.renderNow(),
                        preTitle = "",
                        icon = preview.image.render(
                            transformations = listOf(ImageTransformation.CropTransparent)
                        ),
                        onClick = { viewModel.openPokemonDetails(preview) },
                        isOutlined = true,
                        colors = CardDefaults.outlinedCardColors(
                            containerColor = colorScheme.surfaceVariant
                                .copy(alpha = 0.4f)
                        )
                    )
                }
            }
        }

        item {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = { viewModel.openMovesList() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorScheme.primary
                    ),
                    modifier = Modifier.fillMaxWidth(0.85f)
                ) {
                    Text(
                        text = stringResource(id = R.string.type_moves_button)
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.size(MaterialTheme.grid.x4))
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
                    val labelColor = type.toColorResource().render()
                    PillChip(
                        label = { Text(text = type.toStringResource().render().value) },
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