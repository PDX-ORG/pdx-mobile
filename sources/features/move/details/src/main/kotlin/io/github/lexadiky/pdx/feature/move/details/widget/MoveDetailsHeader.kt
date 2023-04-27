package io.github.lexadiky.pdx.feature.move.details.widget

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import io.github.lexadiky.pdx.domain.pokemon.asset.assets
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonType
import io.github.lexadiky.pdx.feature.move.details.MoveDetailsState
import io.github.lexadiky.pdx.ui.uikit.resources.render
import io.github.lexadiky.pdx.ui.uikit.theme.circular
import io.github.lexadiky.pdx.ui.uikit.theme.grid

@Composable
internal fun MoveDetailsHeader(state: MoveDetailsState, onTypeClicked: (PokemonType) -> Unit) {
    Crossfade(
        label = "header-crossfade",
        targetState = state.isLoading
    ) { isLoading ->
        if (isLoading) {
            MoveDetailsSkeleton()
        } else {
            Content(state = state, onTypeClicked = onTypeClicked)
        }
    }
}

@Composable
private fun Content(
    state: MoveDetailsState,
    onTypeClicked: (PokemonType) -> Unit,
) {
    MoveDetailsHeaderScaffold(
        icon = {
            state.type?.let { type ->
                Image(
                    type.assets.icon.render(),
                    contentDescription = null,
                    modifier = Modifier
                        .size(MaterialTheme.grid.x3)
                        .clip(MaterialTheme.shapes.circular)
                        .clickable { onTypeClicked(type) }
                )
            }
        },
        title = {
            state.localeName?.let { name ->
                Text(
                    text = name.render(),
                    style = MaterialTheme.typography.titleLarge
                )
            }
        },
        ppLabel = {
            Text(
                text = state.ppLabel.render(),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
        }
    )
}
