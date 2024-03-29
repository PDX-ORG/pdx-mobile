@file:OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalAnimationApi::class
)

package io.github.lexadiky.pdx.library.uikit.widget

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.with
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedSuggestionChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import io.github.lexadiky.pdx.library.resources.color.ColorResource
import io.github.lexadiky.pdx.library.resources.string.StringResource
import io.github.lexadiky.pdx.library.uikit.resources.render
import io.github.lexadiky.pdx.library.uikit.theme.animation
import io.github.lexadiky.pdx.library.uikit.theme.grid

@Composable
fun LargeWikiPreview(
    preTitle: String?,
    title: String,
    image: Painter,
    tags: List<TagItem>,
    onClick: (() -> Unit)? = null,
    isFavorite: Boolean = false,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = { onClick?.invoke() },
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.grid.x2)
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(MaterialTheme.grid.x1),
                ) {
                    if (isFavorite) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    if (preTitle != null) {
                        Text(
                            text = preTitle,
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                    Text(
                        text = title,
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                }
                TagStrip(tags = tags)
            }

            AnimatedContent(
                targetState = image,
                transitionSpec = {
                    scaleIn(MaterialTheme.animation.linearSlow())
                        .with(scaleOut(MaterialTheme.animation.linearSlow()))
                },
                modifier = Modifier
                    .size(MaterialTheme.grid.x8)
                    .align(Alignment.CenterEnd)
            ) { self ->
                Image(
                    painter = self,
                    contentDescription = null,
                )
            }
        }
    }
}

data class TagItem(
    val title: StringResource,
    val color: ColorResource,
    val onClick: (() -> Unit)
)

@Composable
private fun TagStrip(tags: List<TagItem>, modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.grid.x05),
        modifier = modifier
    ) {
        for (tag in tags) {
            TagChip(
                title = tag.title.render(),
                color = tag.color.render(),
                onClick = { tag.onClick() }
            )
        }
    }
}

@Composable
fun TagChip(title: String, color: Color, onClick: () -> Unit) {
    ElevatedSuggestionChip(
        label = { Text(text = title) },
        colors = SuggestionChipDefaults.elevatedSuggestionChipColors(
            containerColor = color,
            labelColor = MaterialTheme.colorScheme.onError
        ),
        enabled = true,
        onClick = onClick
    )
}
