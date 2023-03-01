@file:OptIn(ExperimentalMaterial3Api::class)

package io.github.lexadiky.pdx.feature.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.github.lexadiky.akore.alice.robo.DIFeature
import io.github.lexadiky.akore.alice.robo.di
import io.github.lexadiky.akore.alice.robo.inject
import io.github.lexadiky.akore.alice.robo.viewModel
import io.github.lexadiky.pdx.domain.pokemon.asset.PokemonTypeAssets
import io.github.lexadiky.pdx.feature.home.entitiy.FeaturedPokemonItem
import io.github.lexadiky.pdx.lib.dynbanner.DynamicBanner
import io.github.lexadiky.pdx.lib.errorhandler.ErrorDialog
import io.github.lexadiky.pdx.lib.resources.image.ImageResource
import io.github.lexadiky.pdx.lib.resources.string.StringResource
import io.github.lexadiky.pdx.lib.resources.string.from
import io.github.lexadiky.pdx.ui.uikit.resources.ImageTransformation
import io.github.lexadiky.pdx.ui.uikit.resources.render
import io.github.lexadiky.pdx.ui.uikit.theme.grid
import io.github.lexadiky.pdx.ui.uikit.util.scroll.LocalPrimeScrollState
import io.github.lexadiky.pdx.ui.uikit.widget.SmallWikiPreview

@Composable
fun HomePage() {
    DIFeature(HomePageModule) {
        HomePageImpl()
    }
}

@Composable
private fun HomePageImpl(viewModel: HomePageViewModel = di.viewModel()) {
    ErrorDialog(viewModel.state.error) {
        viewModel.hideError()
    }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.grid.x2),
        state = LocalPrimeScrollState.current.asLazyListState()
    ) {
        item {
            FeaturedBlock(viewModel)
        }
        pokemonSuggestionSection(
            title = R.string.home_section_featured_pokemon_title,
            items = viewModel.state.featuredPokemon,
            openPokemonDetails = { viewModel.openPokemonDetails(it) }
        )
        pokemonSuggestionSection(
            title = R.string.home_section_last_viewed_pokemon_title,
            items = viewModel.state.latestViewedPokemon,
            openPokemonDetails = { viewModel.openPokemonDetails(it) }
        )
        item {
            Spacer(modifier = Modifier.size(MaterialTheme.grid.x12))
        }
        item {
            DynamicBanner(
                id = "donate_author",
                modifier = Modifier.fillMaxWidth()
                    .padding(MaterialTheme.grid.x2)
            )
        }
    }
}

private const val HEADER_ARROW_ROTATION_DOWN = 0f
private const val HEADER_ARROW_ROTATION_UP = 180f

private fun LazyListScope.pokemonSuggestionSection(
    title: Int,
    items: List<FeaturedPokemonItem>,
    openPokemonDetails: (FeaturedPokemonItem) -> Unit
) {
    if (items.isEmpty()) {
        return
    }

    item(title) {
        var isFolded by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .padding(horizontal = MaterialTheme.grid.x1)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.extraLarge)
                    .clickable { isFolded = !isFolded }
                    .padding(horizontal = MaterialTheme.grid.x1)
            ) {
                Text(
                    text = stringResource(id = title),
                    style = MaterialTheme.typography.titleLarge
                )
                IconButton(onClick = { isFolded = !isFolded }) {
                    val iconRotation by animateFloatAsState(
                        if (isFolded) HEADER_ARROW_ROTATION_DOWN else HEADER_ARROW_ROTATION_UP
                    )
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = null,
                        modifier = Modifier.rotate(iconRotation)
                    )
                }
            }
            AnimatedVisibility(visible = !isFolded) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(MaterialTheme.grid.x2),
                    modifier = Modifier.padding(horizontal = MaterialTheme.grid.x1)
                ) {
                    Spacer(modifier = Modifier.size(MaterialTheme.grid.x05))
                    for (item in items) {
                        SmallWikiPreview(
                            title = item.name.render(),
                            preTitle = item.nationalDexId.render(),
                            icon = item.image.render(
                                listOf(ImageTransformation.CropTransparent)
                            ),
                            isOutlined = true,
                            onClick = { openPokemonDetails(item) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun FeaturedBlock(viewModel: HomePageViewModel) {
    Column(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.grid.x2),
        modifier = Modifier.padding(horizontal = MaterialTheme.grid.x2)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.grid.x2),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            HomePageFeaturedCard(
                title = StringResource.from(R.string.home_featured_title_pokemon),
                image = PokemonTypeAssets.GRASS.icon,
                shape = HomePageFeaturedCardShape.Card,
                weight = 1.5f,
                onClick = { viewModel.openPokemonList() }
            )
            HomePageFeaturedCard(
                title = StringResource.from(R.string.home_featured_title_team),
                image = PokemonTypeAssets.FIGHTING.icon,
                shape = HomePageFeaturedCardShape.Card,
                weight = 1f,
                onClick = {}
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.grid.x2),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            HomePageFeaturedCard(
                title = StringResource.from(R.string.home_featured_title_whois),
                image = PokemonTypeAssets.PSYCHIC.icon,
                shape = HomePageFeaturedCardShape.Box,
                weight = 1f,
                onClick = { viewModel.openWhoIs() }
            )
            HomePageFeaturedCard(
                title = StringResource.from(R.string.home_featured_title_news),
                image = PokemonTypeAssets.FLYING.icon,
                shape = HomePageFeaturedCardShape.Box,
                weight = 1f,
                onClick = { viewModel.openNews() }
            )
            HomePageFeaturedCard(
                title = StringResource.from(R.string.home_featured_title_achievements),
                image = PokemonTypeAssets.ELECTRIC.icon,
                shape = HomePageFeaturedCardShape.Box,
                weight = 1f,
                onClick = { viewModel.openAchievements() }
            )
        }
    }
}

enum class HomePageFeaturedCardShape {
    Card, Box
}

@Composable
private fun RowScope.HomePageFeaturedCard(
    title: StringResource,
    image: ImageResource,
    shape: HomePageFeaturedCardShape,
    weight: Float,
    onClick: () -> Unit
) {
    ElevatedCard(
        onClick = { onClick() },
        modifier = Modifier
            .fillMaxWidth()
            .let { mod ->
                when (shape) {
                    HomePageFeaturedCardShape.Card -> mod.height(MaterialTheme.grid.x20)
                    HomePageFeaturedCardShape.Box -> mod.aspectRatio(1f)
                }
            }
            .weight(weight)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = image.render(listOf(ImageTransformation.CropTransparent)),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .aspectRatio(1f)
                    .align(Alignment.BottomEnd)
                    .offset(16.dp, 16.dp)
            )
            Text(
                text = title.render(),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(MaterialTheme.grid.x2)
            )
        }
    }
}
