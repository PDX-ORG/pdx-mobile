@file:OptIn(ExperimentalMaterial3Api::class)

package io.github.lexadiky.pdx.feature.typechart.chart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import io.github.lexadiky.akore.alice.robo.di
import io.github.lexadiky.akore.alice.robo.viewModel
import io.github.lexadiky.akore.lechuck.robo.decoration.Decoration
import io.github.lexadiky.pdx.domain.pokemon.asset.assets
import io.github.lexadiky.pdx.feature.typechart.R
import io.github.lexadiky.pdx.feature.typechart.ui.EffectChart
import io.github.lexadiky.pdx.library.arc.Page
import io.github.lexadiky.pdx.library.errorhandler.ErrorDialog
import io.github.lexadiky.pdx.ui.uikit.resources.render
import io.github.lexadiky.pdx.ui.uikit.theme.grid

@Composable
internal fun TypeChartPage(viewModel: TypeChartSocket = di.viewModel()) = Page(viewModel) { state, act ->
    ErrorDialog(state.error) {
        act(TypeChartAction.HideError)
    }
    Decoration(decoration = "pdx://toolbar/title") {
        Text(text = stringResource(id = R.string.type_tab_chart_title))
    }

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        TypeSelectionCard(state, act)

        EffectChart(
            title = stringResource(id = R.string.type_chart_section_attack_title),
            table = state.attackDamageRelationTable,
            modifier = Modifier.padding(MaterialTheme.grid.x2),
            onTypeClicked = { act(TypeChartAction.Navigate.TypeDetails(it)) }
        )
        EffectChart(
            title = stringResource(id = R.string.type_chart_section_defence_title),
            table = state.defenceDamageRelationTable,
            modifier = Modifier.padding(MaterialTheme.grid.x2),
            onTypeClicked = { act(TypeChartAction.Navigate.TypeDetails(it)) }
        )
    }
}

private const val TYPE_ROW_CHUNK = 3

@Composable
private fun TypeSelectionCard(state: TypeChartState, act: (TypeChartAction) -> Unit) {
    Card(modifier = Modifier.padding(MaterialTheme.grid.x2)) {
        Column(
            modifier = Modifier.padding(MaterialTheme.grid.x2)
        ) {
            state.allTypes.chunked(TYPE_ROW_CHUNK).forEach { typeChunk ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(MaterialTheme.grid.x1)
                ) {
                    typeChunk.forEach { type ->
                        FilterChip(
                            selected = type in state.selectedTypes,
                            onClick = { act(TypeChartAction.TypeClicked(type)) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = type.assets.color.render(),
                                selectedLabelColor = MaterialTheme.colorScheme.onError
                            ),
                            label = { Text(text = type.assets.title.render()) },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }
    }
}
