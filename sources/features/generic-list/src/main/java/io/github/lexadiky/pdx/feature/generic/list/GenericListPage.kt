@file:OptIn(ExperimentalFoundationApi::class)

package io.github.lexadiky.pdx.feature.generic.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import io.github.lexadiky.pdx.feature.generic.list.entity.GenericListItem
import io.github.lexadiky.pdx.feature.generic.list.entity.SearchQuery
import io.github.lexadiky.pdx.lib.dynbanner.DynamicBanner
import io.github.lexadiky.pdx.lib.errorhandler.ErrorDialog
import io.github.lexadiky.pdx.ui.uikit.resources.ImageTransformation
import io.github.lexadiky.pdx.ui.uikit.resources.render
import io.github.lexadiky.pdx.ui.uikit.theme.grid
import io.github.lexadiky.pdx.ui.uikit.util.scroll.LocalPrimeScrollState
import io.github.lexadiky.pdx.ui.uikit.widget.FastScrollWheel
import io.github.lexadiky.pdx.ui.uikit.widget.LargeWikiPreview
import io.github.lexadiky.pdx.ui.uikit.widget.TagItem
import io.github.lexadiky.pdx.ui.uikit.widget.ToggleableFab

typealias FilterBlock<T> = @Composable (isVisible: Boolean, queryCallback: (SearchQuery<T>) -> Unit) -> Unit

@Composable
fun <T : GenericListItem> GenericListPage(
    filterBlock: FilterBlock<T>?,
    viewModel: GenericListViewModel<T>,
) {
    GenericListPageImpl(
        viewModel = viewModel,
        filterBlock = filterBlock
    )
}

private const val SEARCH_QUERY_ITEM_ID = "search_query_item"

@Composable
private fun <T : GenericListItem> GenericListPageImpl(
    filterBlock: FilterBlock<T>?,
    viewModel: GenericListViewModel<T>,
) {
    LaunchedEffect(filterBlock == null) {
        viewModel.setSearchAvailable(filterBlock != null)
    }

    ErrorDialog(error = viewModel.state.uiError) {
        viewModel.hideError()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val columnState = LocalPrimeScrollState.current
            .asLazyListState()

        LaunchedEffect(viewModel.state.searchActivated) {
            if (viewModel.state.searchActivated) {
                columnState.animateScrollToItem(0)
            }
        }
        LazyColumn(
            state = columnState,
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.grid.x2),
            contentPadding = PaddingValues(MaterialTheme.grid.x2)
        ) {
            item(SEARCH_QUERY_ITEM_ID) {
                val shouldShowFilter =
                    viewModel.state.searchActivated && viewModel.state.searchAvailable
                filterBlock?.invoke(shouldShowFilter) { query ->
                    viewModel.updateQuery(query)
                }
            }
            itemsIndexed(
                items = viewModel.state.visibleItems,
                key = { idx, entry -> entry.id }
            ) { idx, entry ->
                val applicableBanners = remember(idx, entry) {
                    viewModel.state.banners.filter { it.shouldPlace(idx, entry) }
                }

                if (applicableBanners.isNotEmpty()) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(MaterialTheme.grid.x2),
                        modifier = Modifier.padding(bottom = MaterialTheme.grid.x2)
                    ) {
                        applicableBanners.forEach { banner ->
                            DynamicBanner(id = banner.bannerId)
                        }
                    }
                }

                LargeWikiPreview(
                    preTitle = entry.note.render(),
                    title = entry.title.render(),
                    image = genericListItemPreviewPainter(
                        item = entry,
                        useAlternativeImages = viewModel.state.useAlternativeImages
                    ),
                    tags = entry.tags.map { tag ->
                        TagItem(tag.text, tag.color) {
                            viewModel.onTagClicked(entry, tag)
                        }
                    },
                    onClick = { viewModel.openDetails(entry) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateItemPlacement()
                )
            }
        }
        FastScrollWheel(
            items = viewModel.state.visibleItems,
            columnState = columnState,
            modifier = Modifier.align(Alignment.CenterEnd)
        )

        if (viewModel.state.searchAvailable) {
            SearchModeToggleFab(
                state = viewModel.state,
                onToggle = { viewModel.toggleSearchMode() },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(MaterialTheme.grid.x2)
            )
        }
    }
}

@Composable
private fun SearchModeToggleFab(
    state: GenericListState<*>,
    onToggle: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ToggleableFab(
        state.searchActivated,
        toggled = { Icon(Icons.Default.Close, null) },
        untoggled = { Icon(Icons.Default.Search, null) },
        onToggle = onToggle,
        modifier = modifier
    )
}

@Composable
private fun genericListItemPreviewPainter(
    item: GenericListItem,
    useAlternativeImages: Boolean,
): Painter {
    return if (!useAlternativeImages && item.secondaryImage != null) {
        item.secondaryImage!!.render(listOf(ImageTransformation.CropTransparent))
    } else {
        item.primaryImage.render(listOf(ImageTransformation.CropTransparent))
    }
}
