@file:OptIn(ExperimentalFoundationApi::class)

package io.github.lexadiky.pdx.library.uikit.widget

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import io.github.lexadiky.pdx.library.uikit.theme.animation
import io.github.lexadiky.pdx.library.uikit.theme.circular
import io.github.lexadiky.pdx.library.uikit.theme.grid

private const val EXPANDED_SCALE = 1.5f
private const val DEFAULT_SCALE = 1f

@Composable
fun PagerDotIndicator(pagerState: PagerState, pageCount: Int) {
    if (pageCount < 2) {
        return
    }

    Box {
        Row(
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.grid.x1),
        ) {
            repeat(pageCount) { idx ->
                Box(
                    modifier = Modifier
                        .clip(MaterialTheme.shapes.circular)
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .size(MaterialTheme.grid.x1)
                )
            }
        }
        val coverDotPosition by animateDpAsState(
            label = "cover-dot-position",
            targetValue = MaterialTheme.grid.x1 * pagerState.currentPage * 2,
            animationSpec = MaterialTheme.animation.overShootFast()
        )
        val coverDotScale by animateFloatAsState(
            label = "cover-dot-scale",
            targetValue = if (pagerState.isScrollInProgress) EXPANDED_SCALE else DEFAULT_SCALE
        )
        Box(
            modifier = Modifier
                .offset(x = coverDotPosition)
                .scale(coverDotScale)
                .clip(MaterialTheme.shapes.circular)
                .background(MaterialTheme.colorScheme.primary)
                .size(MaterialTheme.grid.x1)
        )
    }
}
