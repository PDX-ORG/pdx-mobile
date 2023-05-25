package io.github.lexadiky.pdx.feature.move.details.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.lexadiky.pdx.library.uikit.theme.circular
import io.github.lexadiky.pdx.library.uikit.theme.grid
import io.github.lexadiky.pdx.library.uikit.widget.BottomSheetHeaderScaffold
import io.github.lexadiky.pdx.library.uikit.widget.PlaceholderDefaults
import io.github.lexadiky.pdx.library.uikit.widget.placeholder

@Composable
internal fun MoveDetailsSkeleton() {
    BottomSheetHeaderScaffold(
        icon = {
            Box(
                modifier = Modifier
                    .size(MaterialTheme.grid.x3)
                    .placeholder(shape = MaterialTheme.shapes.circular)
            )
        },
        title = {
            Text(
                text = "Placeholder",
                modifier = Modifier
                    .placeholder()
            )
        },
        subtitle = {
            Column {
                Text(
                    text = "Placeholder",
                    modifier = Modifier
                        .fillMaxWidth(PlaceholderDefaults.Width.Large)
                        .placeholder(scaleHeight = PlaceholderDefaults.ShrinkedTextHeight)
                )
                Text(
                    text = "Placeholder",
                    modifier = Modifier
                        .fillMaxWidth(PlaceholderDefaults.Width.Medium)
                        .placeholder(scaleHeight = PlaceholderDefaults.ShrinkedTextHeight)
                )
            }
        },
        endDecoration = {
            Text(
                text = "PP",
                modifier = Modifier
                    .placeholder(true)
            )
        }
    )
}
