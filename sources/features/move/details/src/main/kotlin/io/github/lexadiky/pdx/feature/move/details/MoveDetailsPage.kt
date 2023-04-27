package io.github.lexadiky.pdx.feature.move.details

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.lexadiky.akore.alice.robo.DIFeature
import io.github.lexadiky.akore.alice.robo.di
import io.github.lexadiky.akore.alice.robo.viewModel
import io.github.lexadiky.pdx.feature.move.details.widget.MoveDetailsHeader
import io.github.lexadiky.pdx.lib.errorhandler.ErrorDialog
import io.github.lexadiky.pdx.ui.uikit.resources.render
import io.github.lexadiky.pdx.ui.uikit.theme.grid
import io.github.lexadiky.pdx.ui.uikit.widget.placeholder

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
        MoveDetailsHeader(
            state = vm.state,
            onTypeClicked = { vm.onTypeClicked(it) }
        )
        DescriptionCard(vm.state)
        Box(modifier = Modifier.height(500.dp))
    }
}

@Composable
private fun DescriptionCard(state: MoveDetailsState) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .placeholder(state.isLoading)
            .animateContentSize()
    ) {
        if (!state.isLoading) {
            state.localeShortEffect?.let { text ->
                Text(
                    text = text.render(),
                    modifier = Modifier.padding(MaterialTheme.grid.x2)
                )
            }
        } else {
            Text(
                text = "\n\n",
                modifier = Modifier.padding(MaterialTheme.grid.x2)
                    .fillMaxWidth()
            )
        }
    }
}
