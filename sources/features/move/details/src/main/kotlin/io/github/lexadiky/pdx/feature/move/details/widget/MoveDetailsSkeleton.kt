package io.github.lexadiky.pdx.feature.move.details.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.lexadiky.pdx.ui.uikit.theme.circular
import io.github.lexadiky.pdx.ui.uikit.theme.grid
import io.github.lexadiky.pdx.ui.uikit.widget.placeholder

@Composable
internal fun MoveDetailsSkeleton() {
    MoveDetailsHeaderScaffold(
        icon = {
            Box(
                modifier = Modifier
                    .size(MaterialTheme.grid.x3)
                    .placeholder(true, shape = MaterialTheme.shapes.circular)
            )
        },
        title = {
            Text(
                text = "Placeholder",
                modifier = Modifier
                    .placeholder(true)
            )
        },
        ppLabel = {
            Text(
                text = "PP",
                modifier = Modifier
                    .placeholder(true)
            )
        }
    )
}