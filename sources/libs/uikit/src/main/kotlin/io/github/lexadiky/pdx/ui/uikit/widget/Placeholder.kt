package io.github.lexadiky.pdx.ui.uikit.widget

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Shape
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material3.fade
import com.google.accompanist.placeholder.material3.placeholder
import io.github.lexadiky.pdx.ui.uikit.util.alpha

object PlaceholderDefaults {
    const val SHRIEKED_TEXT_HEIGHT = 0.8f
}

@Composable
fun Modifier.placeholder(
    visible: Boolean,
    scaleHeight: Float = 1f,
    shape: Shape = MaterialTheme.shapes.small
) = scale(scaleX = 1f, scaleY = scaleHeight)
    .placeholder(
        visible = visible,
        color = MaterialTheme.colorScheme.primary
            .alpha(0.2f),
        shape = shape,
        highlight = PlaceholderHighlight.fade()
    )
