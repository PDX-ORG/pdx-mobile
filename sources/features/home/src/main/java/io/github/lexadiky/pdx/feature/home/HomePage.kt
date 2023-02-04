@file:OptIn(ExperimentalMaterial3Api::class)

package io.github.lexadiky.pdx.feature.home

import androidx.compose.foundation.Image
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
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.github.lexadiky.akore.alice.robo.DIFeature
import io.github.lexadiky.akore.alice.robo.di
import io.github.lexadiky.akore.alice.robo.inject
import io.github.lexadiky.pdx.domain.pokemon.asset.PokemonTypeAssets
import io.github.lexadiky.pdx.lib.errorhandler.ErrorDialog
import io.github.lexadiky.pdx.lib.resources.image.ImageResource
import io.github.lexadiky.pdx.lib.resources.string.StringResource
import io.github.lexadiky.pdx.lib.resources.string.from
import io.github.lexadiky.pdx.ui.uikit.resources.ImageTransformation
import io.github.lexadiky.pdx.ui.uikit.resources.render
import io.github.lexadiky.pdx.ui.uikit.theme.grid
import io.github.lexadiky.pdx.ui.uikit.widget.SmallWikiPreview

@Composable
fun HomePage() {
    DIFeature(HomePageModule) {
        HomePageImpl()
    }
}

@Composable
private fun HomePageImpl(viewModel: HomePageViewModel = di.inject()) {
    ErrorDialog(viewModel.state.error) {
        viewModel.hideError()
    }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.grid.x2)
    ) {
        item {
            FeaturedBlock(viewModel)
        }
        item {
            Column(modifier = Modifier.padding(horizontal = MaterialTheme.grid.x2)) {
                Text(
                    text = "Featured pokemon",
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.size(MaterialTheme.grid.x2))
                Column(
                    verticalArrangement = Arrangement.spacedBy(MaterialTheme.grid.x2)
                ) {
                    for (item in viewModel.state.featuredPokemon) {
                        SmallWikiPreview(
                            title = item.name.render(),
                            preTitle = item.nationalDexId.render(),
                            icon = item.image.render(
                                listOf(ImageTransformation.CropTransparent)
                            ),
                            isOutlined = true,
                            onClick = { viewModel.openPokemonDetails(item) }
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
                    HomePageFeaturedCardShape.Card -> mod.height(MaterialTheme.grid.x(20f))
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