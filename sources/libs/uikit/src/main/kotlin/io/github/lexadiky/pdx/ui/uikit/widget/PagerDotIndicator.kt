package io.github.lexadiky.pdx.ui.uikit.widget

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import io.github.lexadiky.pdx.ui.uikit.theme.animation
import io.github.lexadiky.pdx.ui.uikit.theme.circular
import io.github.lexadiky.pdx.ui.uikit.theme.grid

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PagerDotIndicator(pagerState: PagerState) {
    if (pagerState.pageCount < 2) {
        return
    }

    Box {
        Row(
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.grid.x1),
        ) {
            repeat(pagerState.pageCount) { idx ->
                Box(
                    modifier = Modifier
                        .clip(MaterialTheme.shapes.circular)
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .size(MaterialTheme.grid.x1)
                )
            }
        }
        val coverDotPosition by animateDpAsState(
            targetValue = MaterialTheme.grid.x1 * pagerState.currentPage * 2,
            animationSpec = MaterialTheme.animation.overShootFast()
        )
        val coverDotScale by animateFloatAsState(targetValue = if (pagerState.isScrollInProgress) 1.5f else 1f)
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
