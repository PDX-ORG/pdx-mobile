@file:OptIn(ExperimentalMaterial3Api::class)

package io.github.lexadiky.pdx.library.uikit.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import io.github.lexadiky.pdx.library.uikit.theme.grid

@Composable
fun SmallWikiPreview(
    title: String,
    icon: Painter,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    preTitle: String? = null,
    colors: CardColors = CardDefaults.cardColors()
) {
    Card(
        onClick = { onClick() },
        colors = colors,
        modifier = modifier
    ) {
        Content(title = title, preTitle = preTitle, icon = icon)
    }
}

@Composable
private fun Content(
    title: String,
    preTitle: String?,
    icon: Painter,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.grid.x2)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.grid.x1),
        ) {
            if (preTitle != null) {
                Text(
                    text = preTitle,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.alignByBaseline()
                )
            }
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.alignByBaseline()
            )
        }
        Image(
            painter = icon,
            contentDescription = null,
            modifier = Modifier.size(MaterialTheme.grid.x4)
        )
    }
}
