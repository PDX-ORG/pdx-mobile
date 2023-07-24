package io.github.lexadiky.pdx.feature.account.details.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import io.github.lexadiky.pdx.feature.account.details.entity.CandyBoxState
import io.github.lexadiky.pdx.library.core.lce.Lce
import io.github.lexadiky.pdx.library.uikit.resources.render
import io.github.lexadiky.pdx.library.uikit.theme.grid
import io.github.lexadiky.pdx.library.uikit.util.saturation
import io.github.lexadiky.pdx.library.uikit.widget.placeholder

private const val CANDY_BOX_SATURATION_TOP = 1.2f
private const val CANDY_BOX_SATURATION_BOTTOM = 0.5f
private const val CANDY_BOX_PLACEHOLDER_SIZE = 5

@Composable
fun CandyBoxStrip(candyBoxesLce: Lce<*, List<CandyBoxState>>) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = MaterialTheme.grid.x2),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.grid.x2)
    ) {
        if (candyBoxesLce is Lce.Content) {
            items(candyBoxesLce.value) { item ->
                CandyBox(color = item.color.render()) {
                    Column(
                        modifier = Modifier.padding(MaterialTheme.grid.x2)
                    ) {
                        Text(
                            text = "Team 1",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            }
        } else {
            items(CANDY_BOX_PLACEHOLDER_SIZE) {
                CandyBoxPlaceholder()
            }
        }
    }
}

@Composable
fun CandyBox(
    color: Color,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        content = content,
        modifier = Modifier
            .size(MaterialTheme.grid.x20)
            .shadow(
                elevation = MaterialTheme.grid.x1,
                shape = MaterialTheme.shapes.extraLarge,
            )
            .clickable {

            }
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        color.saturation(CANDY_BOX_SATURATION_TOP),
                        color.saturation(CANDY_BOX_SATURATION_BOTTOM)
                    )
                )
            )
            .clip(shape = MaterialTheme.shapes.extraLarge)
    )
}

@Composable
fun CandyBoxPlaceholder() {
    Box(
        modifier = Modifier
            .size(MaterialTheme.grid.x20)
            .placeholder(true)
            .clip(shape = MaterialTheme.shapes.extraLarge)
    )
}
