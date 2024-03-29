package io.github.lexadiky.pdx.feature.typechart.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.google.accompanist.flowlayout.FlowRow
import io.github.lexadiky.pdx.domain.pokemon.asset.assets
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonType
import io.github.lexadiky.pdx.feature.type.chart.R
import io.github.lexadiky.pdx.feature.typechart.entity.TypeDamageValue
import io.github.lexadiky.pdx.library.uikit.resources.render
import io.github.lexadiky.pdx.library.uikit.theme.grid
import io.github.lexadiky.pdx.library.uikit.widget.PillChip
import io.github.lexadiky.pdx.library.uikit.widget.PillChipDefaults

@Composable
internal fun EffectChart(
    title: String,
    table: List<TypeDamageValue>,
    onTypeClicked: (PokemonType) -> Unit,
    modifier: Modifier = Modifier
) {
    if (table.isNotEmpty()) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.grid.x2)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            FlowRow(
                mainAxisSpacing = MaterialTheme.grid.x1,
                crossAxisSpacing = MaterialTheme.grid.x1,
            ) {
                table.forEach { relation ->
                    val labelColor = relation.type.assets.color.render()
                    PillChip(
                        label = { Text(text = relation.type.assets.title.render()) },
                        labelColor = labelColor,
                        trail = { Text(text = stringResource(id = R.string.type_chart_modifier, relation.value)) },
                        trailColor = PillChipDefaults.trailColor(labelColor),
                        textColor = MaterialTheme.colorScheme.onError,
                        onClick = { onTypeClicked(relation.type) },
                    )
                }
            }
        }
    }
}
