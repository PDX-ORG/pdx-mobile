package io.github.lexadiky.pdx.feature.ability.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.lexadiky.akore.alice.robo.DIFeature
import io.github.lexadiky.akore.alice.robo.di
import io.github.lexadiky.akore.alice.robo.viewModel
import io.github.lexadiky.pdx.lib.errorhandler.ErrorDialog
import io.github.lexadiky.pdx.ui.uikit.resources.render
import io.github.lexadiky.pdx.ui.uikit.theme.grid
import io.github.lexadiky.pdx.ui.uikit.widget.BottomSheetHeaderScaffold

@Composable
fun AbilityDetailsPage(id: String) {
    DIFeature(AbilityDetailsModule) {
        AbilityDetailsPageImpl(di.viewModel(id))
    }
}

@Composable
private fun AbilityDetailsPageImpl(viewModel: AbilityDetailsViewModel) {
    ErrorDialog(viewModel.state.error) {
        viewModel.hideError()
    }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.grid.x2),
        modifier = Modifier.padding(MaterialTheme.grid.x2)
    ) {
        item {
            BottomSheetHeaderScaffold(
                title = { Text(text = viewModel.state.title.render()) },
                subtitle = { Text(text = viewModel.state.subtitle.render()) }) {
                
            }
        }
        item {
            Card {
                Text(
                    text = viewModel.state.effect.render(),
                    modifier = Modifier.padding(MaterialTheme.grid.x2)
                )
            }
        }
        item {
            Spacer(modifier = Modifier.size(MaterialTheme.grid.x10))
        }
    }
}
