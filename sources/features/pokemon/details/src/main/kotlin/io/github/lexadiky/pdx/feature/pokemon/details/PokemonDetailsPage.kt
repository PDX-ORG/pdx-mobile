@file:OptIn(ExperimentalFoundationApi::class)

package io.github.lexadiky.pdx.feature.pokemon.details

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import io.github.lexadiky.akore.alice.robo.DIFeature
import io.github.lexadiky.akore.alice.robo.di
import io.github.lexadiky.akore.alice.robo.viewModel
import io.github.lexadiky.akore.lechuck.robo.decoration.Decoration
import io.github.lexadiky.akore.lechuck.robo.fsdialog.FullScreenDialogAnchor
import io.github.lexadiky.pdx.domain.pokemon.asset.assets
import io.github.lexadiky.pdx.domain.pokemon.entity.PokemonType
import io.github.lexadiky.pdx.feature.pokemon.details.entitiy.PokemonDetailsSection
import io.github.lexadiky.pdx.feature.pokemon.details.subpage.evolution.EvolutionSubPage
import io.github.lexadiky.pdx.feature.pokemon.details.subpage.info.InfoSubPage
import io.github.lexadiky.pdx.feature.pokemon.details.subpage.moves.MovesSubPage
import io.github.lexadiky.pdx.feature.pokemon.details.subpage.stats.StatsSubPage
import io.github.lexadiky.pdx.lib.arc.Page
import io.github.lexadiky.pdx.lib.core.lce.Lce
import io.github.lexadiky.pdx.lib.errorhandler.ErrorDialog
import io.github.lexadiky.pdx.lib.navigation.FullScreenDialogStyles
import io.github.lexadiky.pdx.lib.resources.image.ImageResource
import io.github.lexadiky.pdx.lib.resources.string.StringResource
import io.github.lexadiky.pdx.lib.uikit.R
import io.github.lexadiky.pdx.ui.uikit.resources.ImageTransformation
import io.github.lexadiky.pdx.ui.uikit.resources.render
import io.github.lexadiky.pdx.ui.uikit.theme.animation
import io.github.lexadiky.pdx.ui.uikit.theme.circular
import io.github.lexadiky.pdx.ui.uikit.theme.grid
import io.github.lexadiky.pdx.ui.uikit.util.createColorSchemeFromColor
import io.github.lexadiky.pdx.ui.uikit.util.scroll.LocalPrimeScrollState
import io.github.lexadiky.pdx.ui.uikit.widget.PagerDotIndicator
import io.github.lexadiky.pdx.ui.uikit.widget.ToolbarContent

private const val HEADER_IMAGE_ID = "__header_image__"

@Composable
fun PokemonDetailsPage(pokemonId: String) {
    DIFeature(PokemonDetailsModule) {
        PokemonDetailsPageImpl(di.viewModel(pokemonId), di.viewModel(pokemonId))
    }
}

@SuppressLint("RestrictedApi")
@Composable
private fun PokemonDetailsPageImpl(
    viewModel: PokemonDetailsSocket,
    styleFastFetchViewModel: PokemonDetailsStyleFastFetchSocket,
) {
    val color = styleFastFetchViewModel.state.color?.render()

    AnimatedVisibility(visible = color != null, enter = fadeIn()) {
        MaterialTheme(colorScheme = createColorSchemeFromColor(color)) {
            PokemonDetailsPageThemedContent(viewModel)
        }
    }
}

@Composable
private fun PokemonDetailsPageThemedContent(vm: PokemonDetailsSocket) = Page(vm) { state, act ->
    TitleDecoration(state.name, state.types) {
        act(PokemonDetailsAction.OpenTypeDetails(it))
    }
    ErrorDialog(state.error) {
        act(PokemonDetailsAction.HideError)
    }

    val scrollState = LocalPrimeScrollState.current
    LazyColumn(
        state = scrollState.asLazyListState(),
        modifier = Modifier
            .fillMaxSize()
            .animateContentSize()
    ) {
        item(HEADER_IMAGE_ID) {
            HeaderImagePager(
                state,
                act
            )
        }
        item {
            DataCard(state)
        }
    }
}

@Composable
private fun DataCard(state: PokemonDetailsState) {
    AnimatedVisibility(
        enter = fadeIn(),
        visible = state.availableDetailsSections.isNotEmpty()
    ) {
        Card(
            Modifier
                .padding(MaterialTheme.grid.x2)
                .fillMaxWidth()
                .animateContentSize()
        ) {
            var selectedTab by remember { mutableStateOf(0) }
            val currentTab = state.availableDetailsSections.getOrNull(selectedTab)
            ScrollableTabRow(
                selectedTabIndex = selectedTab,
                containerColor = Color.Transparent
            ) {
                state.availableDetailsSections.forEach { tab ->
                    val indexOfCurrentTab = state.availableDetailsSections.indexOf(tab)
                    Tab(
                        selected = selectedTab == indexOfCurrentTab,
                        onClick = { selectedTab = indexOfCurrentTab },
                        text = {
                            Text(text = tab.title.render())
                        })
                }
            }
            Content(currentTab, state)
        }
    }
}

@Composable
private fun Content(
    currentTab: PokemonDetailsSection?,
    state: PokemonDetailsState,
) {
    val selectedVariety = state.selectedVariety
    if (selectedVariety is Lce.Content) {
        when (currentTab) {
            PokemonDetailsSection.Stats -> StatsSubPage(
                selectedVariety.value
            )

            PokemonDetailsSection.Info -> InfoSubPage(
                state.pokemonSpeciesDetails,
                selectedVariety.value
            )

            PokemonDetailsSection.Evolution -> EvolutionSubPage(
                state.pokemonSpeciesDetails,
                selectedVariety.value
            )

            PokemonDetailsSection.Battle -> MovesSubPage(
                state.pokemonSpeciesDetails,
            )

            null -> Unit
        }
    }
}

private const val PROGRESS_BOX_SIZE_RATIO = 0.5f

@Composable
private fun HeaderImagePager(
    state: PokemonDetailsState,
    act: (PokemonDetailsAction) -> Unit,
) {
    Crossfade(targetState = state.isLoaded, label = "header-image-pager-cf") { isLoaded ->
        if (isLoaded) {
            val pagerState = rememberPagerState()
            LaunchedEffect(pagerState.currentPage) {
                act(PokemonDetailsAction.SelectVariety(pagerState.currentPage))
            }

            Box {
                Column {
                    HorizontalPager(
                        state.availableVarieties,
                        state = pagerState
                    ) { page ->
                        HeaderImage(state.image(page))
                    }
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = MaterialTheme.grid.x1)
                    ) {
                        PagerDotIndicator(pagerState, state.availableVarieties)
                    }
                }

                val alpha = rememberPagerHeaderLabelsAlpha(pagerState, state)

                if (state.isSpritesViewerEnabled) {
                    SpriteButtonIcon(alpha) {
                        act(PokemonDetailsAction.Navigate.Sprites)
                    }
                }
                PhysicalDimensions(alpha, state)
                FavoriteButtonIcon(alpha, state) {
                    act(PokemonDetailsAction.ToggleFavorite)
                }
            }
        } else {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(PROGRESS_BOX_SIZE_RATIO)
                        .aspectRatio(1f)
                        .align(Alignment.Center)
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(MaterialTheme.grid.x8)
                            .padding(MaterialTheme.grid.x2)
                            .align(Alignment.Center)
                    )
                }
            }
        }
    }
}

@Composable
private fun BoxScope.SpriteButtonIcon(
    alpha: Float,
    openSprites: () -> Unit,
) {
    IconButton(
        onClick = { openSprites() },
        modifier = Modifier
            .size(MaterialTheme.grid.x8)
            .alpha(alpha)
            .align(Alignment.BottomEnd)
            .padding(end = MaterialTheme.grid.x2)
    ) {
        Box(contentAlignment = Alignment.Center) {
            FullScreenDialogAnchor(
                style = FullScreenDialogStyles.circularExpansion()
            )
            Icon(
                painter = painterResource(id = R.drawable.uikit_ic_camera),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
private fun BoxScope.FavoriteButtonIcon(
    alpha: Float,
    state: PokemonDetailsState,
    toggleFavorite: () -> Unit,
) {
    IconButton(
        onClick = { toggleFavorite() },
        modifier = Modifier
            .size(MaterialTheme.grid.x8)
            .alpha(alpha)
            .align(Alignment.BottomStart)
            .padding(start = MaterialTheme.grid.x2)
    ) {
        Box(contentAlignment = Alignment.Center) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = if (state.isFavorite) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.surfaceTint
                }
            )
        }
    }
}

@Composable
private fun BoxScope.PhysicalDimensions(
    alpha: Float,
    state: PokemonDetailsState,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.grid.x1),
        modifier = Modifier
            .alpha(alpha)
            .padding(start = MaterialTheme.grid.x2)
            .align(Alignment.TopStart)
    ) {
        state.dimensions.forEach { dimension ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.grid.x1),
                modifier = Modifier
                    .background(
                        MaterialTheme.colorScheme.surfaceTint,
                        MaterialTheme.shapes.circular
                    )
                    .padding(
                        horizontal = MaterialTheme.grid.x1,
                        vertical = MaterialTheme.grid.x05
                    )
            ) {
                with(LocalDensity.current) {
                    Image(
                        painter = dimension.icon.render(),
                        contentDescription = null,
                        modifier = Modifier
                            .size(MaterialTheme.typography.labelLarge.fontSize.toDp())
                    )
                    Text(
                        text = dimension.label.render(),
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }
        }
    }
}

@Composable
private fun rememberPagerHeaderLabelsAlpha(
    pagerState: PagerState,
    state: PokemonDetailsState,
): Float {
    val isSpritesIconVisible = !pagerState.isScrollInProgress || state.availableVarieties == 1
    val spriteIconAlpha by animateFloatAsState(
        if (isSpritesIconVisible) 1f else 0f,
        MaterialTheme.animation.linearSlow()
    )
    return spriteIconAlpha
}

@Composable
private fun TitleDecoration(
    name: StringResource?,
    types: List<PokemonType>,
    onClick: (PokemonType) -> Unit,
) {
    if (name != null) {
        Decoration("pdx://toolbar/title") {
            ToolbarContent(
                start = {
                    Text(
                        text = name.render(),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                end = {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.grid.x05),
                        modifier = Modifier.padding(end = MaterialTheme.grid.x2)
                    ) {
                        types.forEach { type ->
                            Image(
                                painter = type.assets.icon.render(),
                                contentDescription = type.assets.title.render(),
                                modifier = Modifier
                                    .size(MaterialTheme.grid.x4)
                                    .shadow(MaterialTheme.grid.x1, MaterialTheme.shapes.circular)
                                    .clickable { onClick(type) }
                            )
                        }
                    }
                })
        }
    }
}

private const val HEADER_IMAGE_WIDTH_RATIO = 0.5f

@Composable
private fun HeaderImage(
    image: ImageResource,
) {
    image.let { img ->
        Image(
            painter = img.render(listOf(ImageTransformation.CropTransparent)),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(HEADER_IMAGE_WIDTH_RATIO)
                .aspectRatio(1f)
        )
    }
}
