package io.github.lexadiky.pdx.library.uikit.widget

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import io.github.lexadiky.pdx.library.uikit.theme.circular
import io.github.lexadiky.pdx.library.uikit.theme.grid
import io.github.lexadiky.pdx.library.uikit.util.delayState
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.launch

private const val ITEM_CHUNK = 100
private const val HEIGHT_RATIO = 0.5f
private const val SCROLL_WHEEL_ALPHA = 0.5f
private const val BACKGROUND_TINT = 0.75f

@Composable
fun <T> FastScrollWheel(
    items: List<T>,
    columnState: LazyListState,
    modifier: Modifier = Modifier,
) {
    if (items.size < ITEM_CHUNK) {
        return // show nothing
    }

    val localState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    val isScrollInProgressRecently by delayState(1.seconds) {
        columnState.isScrollInProgress || localState.isScrollInProgress
    }

    AnimatedVisibility(
        visible = isScrollInProgressRecently,
        enter = fadeIn(),
        exit = fadeOut(),
        modifier = modifier
    ) {
        LazyColumn(
            state = localState,
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.grid.x2),
            modifier = Modifier
                .shadow(8.dp, MaterialTheme.shapes.circular)
                .padding(MaterialTheme.grid.x1)
                .clip(MaterialTheme.shapes.circular)
                .background(MaterialTheme.colorScheme.surface.copy(alpha = SCROLL_WHEEL_ALPHA))
                .fillMaxHeight(HEIGHT_RATIO)
        ) {
            items(items.chunked(ITEM_CHUNK).indices.toList()) { idx ->
                Text(
                    text = idx.toString().padStart(2, '0'),
                    modifier = Modifier
                        .clip(MaterialTheme.shapes.circular)
                        .clickable {
                            scope.launch {
                                val offset = columnState.layoutInfo.totalItemsCount - items.size
                                columnState.scrollToItem(offset + idx * ITEM_CHUNK)
                            }
                        }
                        .background(MaterialTheme.colorScheme.surfaceTint.copy(alpha = BACKGROUND_TINT))
                        .padding(MaterialTheme.grid.x1)
                )
            }
        }
    }
}
