package io.github.lexadiky.pdx.library.uikit.widget

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Shape
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material3.fade
import com.google.accompanist.placeholder.material3.placeholder
import io.github.lexadiky.pdx.library.uikit.util.alpha

fun Modifier.placeholder(
    visible: Boolean = true,
    scaleHeight: Float = 1f,
    shape: Shape? = null
) = composed {
    val realShape = shape ?: MaterialTheme.shapes.small

    scale(scaleX = 1f, scaleY = scaleHeight)
        .placeholder(
            visible = visible,
            color = MaterialTheme.colorScheme.primary
                .alpha(PlaceholderDefaults.ColorAlpha),
            shape = realShape,
            highlight = PlaceholderHighlight.fade()
        )
}

object PlaceholderDefaults {
    const val ShrinkedTextHeight = 0.8f

    internal const val ColorAlpha = 0.2f

    object Width {

        const val Large = 0.8f
        const val Big = 0.7f
        const val Medium = 0.6f
    }
}
