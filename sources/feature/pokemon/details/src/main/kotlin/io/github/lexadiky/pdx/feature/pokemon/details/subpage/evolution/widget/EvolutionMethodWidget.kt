package io.github.lexadiky.pdx.feature.pokemon.details.subpage.evolution.widget

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import io.github.lexadiky.pdx.domain.pokemon.entity.evolution.EvolutionMethod
import io.github.lexadiky.pdx.feature.pokemon.details.R

@Composable
internal fun EvolutionMethodWidget(methodVR: EvolutionMethod) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
    ) {
        when (methodVR) {
            is EvolutionMethod.LevelUp -> TextEvolutionMethod(
                stringResource(id = R.string.feature_pokemon_evolution_method_level_up, methodVR.atLevel)
            )
            else -> TextEvolutionMethod(
                stringResource(id = R.string.feature_pokemon_evolution_method_unknown)
            )
        }
    }
}
