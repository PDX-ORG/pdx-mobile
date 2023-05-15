package io.github.lexadiky.pdx.feature.pokemon.details.subpage.evolution.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import io.github.lexadiky.pdx.domain.pokemon.entity.evolution.EvolutionMethod
import io.github.lexadiky.pdx.feature.pokemon.details.R
import io.github.lexadiky.pdx.ui.uikit.theme.grid

@Composable
fun EvolutionMethodWidget(methodVR: EvolutionMethod) {
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
        when (methodVR) {
            EvolutionMethod.Unknown -> TextEvolutionMethod(
                stringResource(id = R.string.feature_pokemon_evolution_method_unkown)
            )

            is EvolutionMethod.LevelUp -> TextEvolutionMethod(
                stringResource(id = R.string.feature_pokemon_evolution_method_level_up, methodVR.atLevel)
            )
        }
    }
}
