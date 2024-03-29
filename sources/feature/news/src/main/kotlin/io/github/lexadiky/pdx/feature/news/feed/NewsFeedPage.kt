@file:OptIn(ExperimentalMaterial3Api::class)

package io.github.lexadiky.pdx.feature.news.feed

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import io.github.lexadiky.akore.alice.robo.DIFeature
import io.github.lexadiky.akore.alice.robo.di
import io.github.lexadiky.akore.alice.robo.viewModel
import io.github.lexadiky.pdx.feature.news.NewsModule
import io.github.lexadiky.pdx.feature.news.entity.NewsFeedItem
import io.github.lexadiky.pdx.library.arc.Page
import io.github.lexadiky.pdx.library.errorhandler.ErrorDialog
import io.github.lexadiky.pdx.library.uikit.UikitDrawable
import io.github.lexadiky.pdx.library.uikit.resources.render
import io.github.lexadiky.pdx.library.uikit.theme.grid
import io.github.lexadiky.pdx.library.uikit.theme.pdx
import io.github.lexadiky.pdx.library.uikit.util.scroll.LocalPrimeScrollState
import io.github.lexadiky.pdx.feature.news.R
import io.github.lexadiky.pdx.library.nibbler.decoration.Decoration

@Composable
fun NewsFeedPage() {
    DIFeature(NewsModule) {
        NewsFeedPageImpl(di.viewModel())
    }
}

@Composable
internal fun NewsFeedPageImpl(viewModel: NewsFeedSocket) = Page(viewModel) { state, act ->
    Decoration("pdx://toolbar/title") {
        Text(text = stringResource(id = R.string.news_feed_title))
    }
    ErrorDialog(error = viewModel.state.error) {
        act(NewsFeedAction.DismissError)
    }
    LazyColumn(
        state = LocalPrimeScrollState.current.asLazyListState(),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.grid.x2),
        contentPadding = PaddingValues(MaterialTheme.grid.x2)
    ) {
        items(viewModel.state.items) { item ->
            NewsFeedItem(
                item = item,
                onItemClick = { act(NewsFeedAction.Navigate.Article(item)) },
                onAuthorClicked = { act(NewsFeedAction.Navigate.Author(item)) }
            )
        }
    }
}

@Composable
private fun NewsFeedItem(item: NewsFeedItem, onItemClick: () -> Unit, onAuthorClicked: () -> Unit) {
    Card(
        onClick = { onItemClick() },
        Modifier.fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.grid.x2),
            modifier = Modifier.padding(MaterialTheme.grid.x2)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.grid.x2)
            ) {
                item.preview?.let { preview ->
                    Image(
                        painter = preview.render(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(MaterialTheme.grid.x10)
                            .clip(MaterialTheme.shapes.extraLarge),
                    )
                }
                Text(
                    text = item.title.render(),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                SuggestionChip(
                    label = { Text(text = item.author) },
                    icon = {
                        Icon(
                            painterResource(id = UikitDrawable.uikit_ic_reddit),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.pdx.externalBrands.reddit
                        )
                    },
                    onClick = { onAuthorClicked() }
                )

                Text(text = item.time.render())
            }
        }
    }
}
