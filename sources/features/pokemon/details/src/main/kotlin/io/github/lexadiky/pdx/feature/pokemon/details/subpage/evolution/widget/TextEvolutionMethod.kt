package io.github.lexadiky.pdx.feature.pokemon.details.subpage.evolution.widget

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import io.github.lexadiky.pdx.ui.uikit.theme.grid

@Composable
internal fun RowScope.TextEvolutionMethod(text: String) {
    Text(
        text = text,
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
