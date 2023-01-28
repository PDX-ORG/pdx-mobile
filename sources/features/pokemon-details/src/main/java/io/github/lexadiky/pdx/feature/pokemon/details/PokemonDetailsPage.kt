@file:OptIn(ExperimentalPagerApi::class)

package io.github.lexadiky.pdx.feature.pokemon.details

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import io.github.lexadiky.akore.alice.robo.DIFeature
import io.github.lexadiky.akore.alice.robo.di
import io.github.lexadiky.akore.alice.robo.inject
import io.github.lexadiky.pdx.domain.pokemon.asset.assets
import io.github.lexadiky.pdx.lib.errorhandler.ErrorDialog
import io.github.lexadiky.pdx.lib.navigation.decoration.Decoration
import io.github.lexadiky.pdx.lib.navigation.page.PageContext
import io.github.lexadiky.pdx.lib.resources.image.ImageResource
import io.github.lexadiky.pdx.lib.resources.image.from
import io.github.lexadiky.pdx.ui.uikit.resources.ImageTransformation
import io.github.lexadiky.pdx.ui.uikit.resources.render
import io.github.lexadiky.pdx.ui.uikit.theme.grid
import io.github.lexadiky.pdx.ui.uikit.util.scroll.LocalPrimeScrollState
import io.github.lexadiky.pdx.ui.uikit.widget.TagChip

private const val HEADER_IMAGE_ID = "__header_image__"

@Composable
fun PageContext.PokemonDetailsPage(pokemonId: String) {
    DIFeature(PokemonDetailsModule) {
        PokemonDetailsPageImpl(di.inject(pokemonId))
    }
}

@Composable
private fun PokemonDetailsPageImpl(viewModel: PokemonDetailsViewModel) {
    Decoration("pdx://toolbar/title") {
        Text(viewModel.state.name?.render().orEmpty())
    }
    Decoration("pdx://toolbar/actions") {
        Row(
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.grid.x05),
            modifier = Modifier.padding(end = MaterialTheme.grid.x2)
        ) {
            viewModel.state.types.forEach { type ->
                TagChip(
                    title = type.assets.title.render(),
                    color = type.assets.color.render(),
                    onClick = { viewModel.openTypeDetails(type) }
                )
            }
        }
    }
    ErrorDialog(viewModel.state.error) {
        viewModel.hideError()
    }

    LazyColumn(
        state = LocalPrimeScrollState.current.asLazyListState(),
        modifier = Modifier
            .fillMaxSize()
            .animateContentSize()
    ) {
        item(HEADER_IMAGE_ID) {
            val state = rememberPagerState()
            LaunchedEffect(state.currentPage) {
                viewModel.selectVariety(state.currentPage)
            }
            HorizontalPager(
                viewModel.state.availableVarieties,
                state = state
            ) { page ->
                val image = viewModel.state.pokemonSpeciesDetails?.varieties?.get(page)?.sprites?.default
                    ?.let { ImageResource.from(it) }
                HeaderImage(image)
            }
        }
        item {
            Spacer(modifier = Modifier.size(MaterialTheme.grid.x2))
        }
        item {
            Card(
                Modifier
                    .padding(MaterialTheme.grid.x2)
                    .height(800.dp)
                    .fillMaxWidth()
            ) {
                var selectedTab by remember { mutableStateOf(0) }
                val tabs = listOf("Stats", "Lore", "Moves", "Catch", "Evolution")
                ScrollableTabRow(selectedTabIndex = selectedTab, containerColor = Color.Transparent) {
                    tabs.forEach { tab ->
                        Tab(selected = selectedTab == tabs.indexOf(tab), onClick = { selectedTab = tabs.indexOf(tab) }, text = {
                            Text(text = tab)
                        })
                    }
                }
            }
        }
    }
}

@Composable
private fun HeaderImage(
    image: ImageResource?,
) {
    Box {
        AnimatedVisibility(
            enter = expandIn(expandFrom = Alignment.Center) + fadeIn(),
            visible = image != null,
            modifier = Modifier.fillMaxWidth()
        ) {
            image?.let { image ->
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = image.render(listOf(ImageTransformation.CropTransparent)),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .aspectRatio(1f)
                    )
                }
            }
        }
        AnimatedVisibility(
            exit = shrinkOut(shrinkTowards = Alignment.Center) + fadeOut(),
            visible = image == null,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(MaterialTheme.grid.x8)
                        .padding(MaterialTheme.grid.x2)
                )
            }
        }
    }
}