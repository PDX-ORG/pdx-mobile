@file:OptIn(ExperimentalComposeUiApi::class)

package io.github.lexadiky.pdx.feature.typechart.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import io.github.lexadiky.akore.alice.robo.di
import io.github.lexadiky.akore.alice.robo.viewModel
import io.github.lexadiky.pdx.domain.pokemon.asset.assets
import io.github.lexadiky.pdx.feature.type.chart.R
import io.github.lexadiky.pdx.feature.typechart.ui.EffectChart
import io.github.lexadiky.pdx.library.errorhandler.ErrorDialog
import io.github.lexadiky.pdx.library.nibbler.decoration.Decoration
import io.github.lexadiky.pdx.library.uikit.resources.ImageTransformation
import io.github.lexadiky.pdx.library.uikit.resources.render
import io.github.lexadiky.pdx.library.uikit.theme.grid
import io.github.lexadiky.pdx.library.uikit.widget.LargeWikiPreview
import io.github.lexadiky.pdx.library.uikit.widget.SearchField
import io.github.lexadiky.pdx.library.uikit.widget.SmallWikiPreview
import io.github.lexadiky.pdx.library.uikit.widget.TagItem

@Composable
internal fun TypeSearchPage(viewModel: TypeSearchViewModel = di.viewModel()) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }

    ErrorDialog(viewModel.state.error) {
        viewModel.hideError()
    }
    Decoration("pdx://toolbar/title") {
        SearchField(
            text = viewModel.state.searchQuery,
            onTextChanged = { viewModel.updateSearchQuery(it) },
            modifier = Modifier
                .fillMaxWidth()
                .height(MaterialTheme.grid.x5)
                .focusRequester(focusRequester)
        )
        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }
    }

    LazyColumn(
        contentPadding = PaddingValues(MaterialTheme.grid.x2),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.grid.x2)
    ) {
        if (viewModel.state.selectedPokemon == null) {
            items(viewModel.state.suggestedPokemon) { pokemon ->
                SmallWikiPreview(
                    title = pokemon.name.render(),
                    preTitle = pokemon.nationalId.render(),
                    icon = pokemon.image.render(
                        transformations = listOf(ImageTransformation.CropTransparent)
                    ),
                    onClick = {
                        keyboardController?.hide()
                        viewModel.selectHint(pokemon)
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        viewModel.state.selectedPokemon?.let { pokemon ->
            item {
                LargeWikiPreview(
                    title = pokemon.name.render(),
                    preTitle = pokemon.nationalId.render(),
                    image = pokemon.image.render(
                        transformations = listOf(ImageTransformation.CropTransparent)
                    ),
                    tags = pokemon.types.map { type ->
                        TagItem(
                            title = type.assets.title,
                            color = type.assets.color,
                            onClick = { viewModel.onTypeClicked(type) }
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        item {
            Spacer(modifier = Modifier.size(MaterialTheme.grid.x2))
            EffectChart(
                title = stringResource(id = R.string.type_chart_section_attack_title),
                table = viewModel.state.attackDamageRelationTable,
                onTypeClicked = { viewModel.onTypeClicked(it) }
            )
            Spacer(modifier = Modifier.size(MaterialTheme.grid.x4))
            EffectChart(
                title = stringResource(id = R.string.type_chart_section_defence_title),
                table = viewModel.state.defenceDamageRelationTable,
                onTypeClicked = { viewModel.onTypeClicked(it) }
            )
        }
    }
}
