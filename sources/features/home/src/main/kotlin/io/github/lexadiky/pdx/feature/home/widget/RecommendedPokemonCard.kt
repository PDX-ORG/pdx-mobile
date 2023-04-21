package io.github.lexadiky.pdx.feature.home.widget

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSizeIn
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.ElevatedCard
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.github.lexadiky.pdx.feature.home.entitiy.SuggestedPokemonItem
import io.github.lexadiky.pdx.ui.uikit.resources.ImageTransformation
import io.github.lexadiky.pdx.ui.uikit.resources.render
import io.github.lexadiky.pdx.ui.uikit.theme.circular
import io.github.lexadiky.pdx.ui.uikit.theme.grid

private const val HEADER_ARROW_ROTATION_DOWN = 0f
private const val HEADER_ARROW_ROTATION_UP = 180f

@Composable
internal fun RecommendedPokemonCard(
    title: String,
    icon: Painter,
    items: List<SuggestedPokemonItem>,
    openPokemonDetails: (SuggestedPokemonItem) -> Unit
) {
    var isExpanded: Boolean by remember { mutableStateOf(true) }

    ElevatedCard(
        modifier = Modifier
            .animateContentSize()
            .padding(
                horizontal = MaterialTheme.grid.x2,
                vertical = MaterialTheme.grid.x1
            )
            .fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { isExpanded = !isExpanded }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = MaterialTheme.grid.x2)
            ) {
                Image(
                    painter = icon,
                    contentDescription = null,
                    modifier = Modifier
                        .size(MaterialTheme.grid.x3)
                        .aspectRatio(1f)
                )
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(start = MaterialTheme.grid.x2)
                )
            }
            IconButton(onClick = { isExpanded = !isExpanded }) {
                val rotation by animateFloatAsState(
                    if (isExpanded) HEADER_ARROW_ROTATION_UP else HEADER_ARROW_ROTATION_DOWN,
                    label = "expand-rotation"
                )
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    modifier = Modifier.rotate(rotation)
                )
            }
        }
        if (isExpanded) {
            PokemonList(items, openPokemonDetails)
        }
    }
}

private const val MAX_ITEM_SIZE_SCREEN_WIDTH_MULTIPLIER = 0.25f

@Composable
private fun PokemonList(
    items: List<SuggestedPokemonItem>,
    openPokemonDetails: (SuggestedPokemonItem) -> Unit,
) {
    val configuration = LocalConfiguration.current
    val maxItemSize = configuration.screenWidthDp * MAX_ITEM_SIZE_SCREEN_WIDTH_MULTIPLIER
    Row(
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.grid.x2),
        modifier = Modifier
            .padding(MaterialTheme.grid.x2)
            .fillMaxWidth()
    ) {
        items.forEach { item ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .requiredSizeIn(maxWidth = maxItemSize.dp, maxHeight = maxItemSize.dp)
                ) {
                    Image(
                        painter = item.image.render(
                            transformations = listOf(ImageTransformation.CropTransparent)
                        ),
                        contentDescription = null,
                        modifier = Modifier
                            .aspectRatio(1f)
                    )
                    Box(
                        modifier = Modifier
                            .scale(1.2f)
                            .matchParentSize()
                            .clip(MaterialTheme.shapes.circular)
                            .clickable { openPokemonDetails(item) }
                    )
                }
                Text(
                    text = item.name.render(),
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}
