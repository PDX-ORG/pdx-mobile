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
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.AssistChip
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import io.github.lexadiky.akore.alice.robo.DIFeature
import io.github.lexadiky.akore.alice.robo.di
import io.github.lexadiky.akore.alice.robo.viewModel
import io.github.lexadiky.akore.lechuck.robo.decoration.Decoration
import io.github.lexadiky.pdx.domain.pokemon.asset.PokemonAssetsDrawable
import io.github.lexadiky.pdx.domain.pokemon.asset.PokemonTypeAssets
import io.github.lexadiky.pdx.feature.home.entitiy.SuggestedPokemonItem
import io.github.lexadiky.pdx.feature.home.entitiy.SuggestedPokemonType
import io.github.lexadiky.pdx.feature.home.widget.RecommendedPokemonCard
import io.github.lexadiky.pdx.library.arc.Page
import io.github.lexadiky.pdx.library.errorhandler.ErrorDialog
import io.github.lexadiky.pdx.library.resources.image.ImageResource
import io.github.lexadiky.pdx.library.resources.string.StringResource
import io.github.lexadiky.pdx.library.resources.string.from
import io.github.lexadiky.pdx.library.uikit.UikitString
import io.github.lexadiky.pdx.library.uikit.resources.ImageTransformation
import io.github.lexadiky.pdx.library.uikit.resources.render
import io.github.lexadiky.pdx.library.uikit.theme.grid
import io.github.lexadiky.pdx.library.uikit.util.scroll.LocalPrimeScrollState
import io.github.lexadiky.pdx.library.uikit.widget.ToolbarContent


@Composable
fun HomePage() {
    DIFeature(HomePageModule) {
        HomePageImpl()
    }
}

@Composable
internal fun HomePageImpl(viewModel: HomePageSocket = di.viewModel()) = Page(viewModel) { state, act ->
    HomeDecoration {
        act(HomePageAction.Navigate.ApplicationShare)
    }

    ErrorDialog(state.error) {
        act(HomePageAction.HideError)
    }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.grid.x1),
        state = LocalPrimeScrollState.current.asLazyListState()
    ) {
        item {
            FeaturedBlock(act)
        }
        pokemonSuggestionSection(
            title = R.string.home_section_featured_pokemon_title,
            icon = PokemonAssetsDrawable.domain_pokemon_ic_type_fairy,
            items = state.featuredPokemon,
            openPokemonDetails = { item ->
                act(
                    HomePageAction.Navigate.PokemonDetails(
                        item,
                        SuggestedPokemonType.Suggested
                    )
                )
            }
        )
        pokemonSuggestionSection(
            title = R.string.home_section_last_viewed_pokemon_title,
            icon = PokemonAssetsDrawable.domain_pokemon_ic_type_fire,
            items = state.latestViewedPokemon,
            openPokemonDetails = { item ->
                act(
                    HomePageAction.Navigate.PokemonDetails(
                        item,
                        SuggestedPokemonType.LAST_VIEWED
                    )
                )
            }
        )
        item {
            Spacer(modifier = Modifier.size(MaterialTheme.grid.x12))
        }
    }
}

@Composable
private fun HomeDecoration(onShareClicked: () -> Unit) {
    Decoration(decoration = "pdx://toolbar/title") {
        ToolbarContent(
            start = {
                Text(
                    text = stringResource(
                        id = UikitString.uikit_generic_app_title
                    )
                )
            }, end = {
                AssistChip(
                    onClick = { onShareClicked() },
                    label = { Text(stringResource(id = R.string.home_title_share_chip)) },
                    modifier = Modifier.padding(end = MaterialTheme.grid.x1)
                )
            }
        )
    }
}

private fun LazyListScope.pokemonSuggestionSection(
    title: Int,
    icon: Int,
    items: List<SuggestedPokemonItem>,
    openPokemonDetails: (SuggestedPokemonItem) -> Unit,
) {
    if (items.isEmpty()) {
        return
    }

    item(title) {
        RecommendedPokemonCard(
            title = stringResource(id = title),
            icon = painterResource(id = icon),
            items = items,
            openPokemonDetails = openPokemonDetails
        )
    }
}

@Composable
private fun FeaturedBlock(act: (HomePageAction) -> Unit) {
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
                onClick = { act(HomePageAction.Navigate.PokemonList) }
            )
            HomePageFeaturedCard(
                title = StringResource.from(R.string.home_featured_title_types),
                image = PokemonTypeAssets.FIGHTING.icon,
                shape = HomePageFeaturedCardShape.Card,
                weight = 1f,
                onClick = { act(HomePageAction.Navigate.Types) }
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
                onClick = { act(HomePageAction.Navigate.WhoIs) }
            )
            HomePageFeaturedCard(
                title = StringResource.from(R.string.home_featured_title_news),
                image = PokemonTypeAssets.FLYING.icon,
                shape = HomePageFeaturedCardShape.Box,
                weight = 1f,
                onClick = { act(HomePageAction.Navigate.News) }
            )
            HomePageFeaturedCard(
                title = StringResource.from(R.string.home_featured_title_achievements),
                image = PokemonTypeAssets.ELECTRIC.icon,
                shape = HomePageFeaturedCardShape.Box,
                weight = 1f,
                onClick = { act(HomePageAction.Navigate.Achievements) }
            )
        }
    }
}

enum class HomePageFeaturedCardShape {
    Card, Box
}

private const val FEATURE_IMAGE_WIDTH_RATIO = 0.7f

@Composable
private fun RowScope.HomePageFeaturedCard(
    title: StringResource,
    image: ImageResource,
    shape: HomePageFeaturedCardShape,
    weight: Float,
    onClick: () -> Unit,
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
                    .fillMaxWidth(FEATURE_IMAGE_WIDTH_RATIO)
                    .aspectRatio(1f)
                    .align(Alignment.BottomEnd)
                    .offset(MaterialTheme.grid.x2, MaterialTheme.grid.x2)
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
