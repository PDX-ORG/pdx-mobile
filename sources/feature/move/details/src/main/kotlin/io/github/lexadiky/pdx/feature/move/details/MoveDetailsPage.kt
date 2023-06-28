package io.github.lexadiky.pdx.feature.move.details

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
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
import io.github.lexadiky.pdx.feature.move.details.entity.attribute.MoveAttribute
import io.github.lexadiky.pdx.feature.move.details.widget.MoveDetailsHeader
import io.github.lexadiky.pdx.library.errorhandler.ErrorDialog
import io.github.lexadiky.pdx.library.uikit.resources.render
import io.github.lexadiky.pdx.library.uikit.theme.grid
import io.github.lexadiky.pdx.library.uikit.widget.BottomSheetBasement
import io.github.lexadiky.pdx.library.uikit.widget.placeholder

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
        Attributes(state = vm.state)
        BottomSheetBasement()
    }
}

@Composable
private fun Attributes(state: MoveDetailsState) {
    Column(
        verticalArrangement = Arrangement
            .spacedBy(MaterialTheme.grid.x2)
    ) {
        if (!state.isLoading) {
            state.attributes.forEach { attribute ->
                when (attribute) {
                    is MoveAttribute.Text -> TextAttribute(attr = attribute)
                }
            }
        }
    }
}

@Composable
private fun TextAttribute(attr: MoveAttribute.Text) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.grid.x2),
            verticalAlignment = Alignment.CenterVertically
            ) {
            Icon(
                painter = attr.icon.render(),
                contentDescription = null,
                modifier = Modifier.size(MaterialTheme.grid.x2)
            )

            Text(text = attr.title.render())
        }

        Text(
            text = attr.value.render(),
            fontWeight = FontWeight.SemiBold
        )
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
                modifier = Modifier
                    .padding(MaterialTheme.grid.x2)
                    .fillMaxWidth()
            )
        }
    }
}
