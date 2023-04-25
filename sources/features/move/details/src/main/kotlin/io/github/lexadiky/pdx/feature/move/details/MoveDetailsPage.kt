package io.github.lexadiky.pdx.feature.move.details

import androidx.compose.foundation.Image
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.github.lexadiky.akore.alice.robo.DIFeature
import io.github.lexadiky.akore.alice.robo.di
import io.github.lexadiky.akore.alice.robo.viewModel
import io.github.lexadiky.pdx.domain.pokemon.asset.PokemonTypeAssets
import io.github.lexadiky.pdx.ui.uikit.resources.render
import io.github.lexadiky.pdx.ui.uikit.theme.grid

@Composable
fun MoveDetailsPage(moveId: String) {
    DIFeature(MoveDetailsModule) {
        MoveDetailsPageImpl(di.viewModel(moveId))
    }
}

@Composable
private fun MoveDetailsPageImpl(vm: MoveDetailsViewModel) {
    Column(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.grid.x2),
        modifier = Modifier
            .padding(MaterialTheme.grid.x2)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.grid.x1)
            ) {
                Image(
                    PokemonTypeAssets.DRAGON.icon.render(),
                    contentDescription = null,
                    modifier = Modifier.size(MaterialTheme.grid.x3)
                )
                Text(
                    text = "Hello World",
                    style = MaterialTheme.typography.titleLarge
                )
            }

            Text(
                text = "25",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
        }
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "ksdjfaks msdkafm sdsf ".repeat(4),
                modifier = Modifier.padding(MaterialTheme.grid.x2)
            )
        }
        Box(modifier = Modifier.height(500.dp))
    }
}