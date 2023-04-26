package io.github.lexadiky.pdx.feature.move.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.github.lexadiky.akore.alice.robo.DIFeature
import io.github.lexadiky.akore.alice.robo.di
import io.github.lexadiky.akore.alice.robo.viewModel
import io.github.lexadiky.pdx.domain.pokemon.asset.PokemonTypeAssets
import io.github.lexadiky.pdx.domain.pokemon.asset.assets
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonType
import io.github.lexadiky.pdx.lib.errorhandler.ErrorDialog
import io.github.lexadiky.pdx.ui.uikit.resources.render
import io.github.lexadiky.pdx.ui.uikit.theme.circular
import io.github.lexadiky.pdx.ui.uikit.theme.grid

@Composable
fun MoveDetailsPage(moveId: String) {
    DIFeature(MoveDetailsModule) {
        MoveDetailsPageImpl(di.viewModel(moveId))
    }
}

@Composable
private fun MoveDetailsPageImpl(vm: MoveDetailsViewModel) {
    ErrorDialog(error = vm.state.error) {
        vm.hideError()
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.grid.x2),
        modifier = Modifier
            .padding(MaterialTheme.grid.x2)
    ) {
        Header(
            state = vm.state,
            onTypeClicked = { vm.onTypeClicked(it) }
        )
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            vm.state.localeFlavourText?.let { flavourText ->
                Text(
                    text = flavourText.render(),
                    modifier = Modifier.padding(MaterialTheme.grid.x2)
                )
            }
        }
        Box(modifier = Modifier.height(500.dp))
    }
}

@Composable
private fun Header(state: MoveDetailsState, onTypeClicked: (PokemonType) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.grid.x1)
        ) {
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
            state.localeName?.let { name ->
                Text(
                    text = name.render(),
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }

        Text(
            text = state.ppLabel.render(),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )
    }
}