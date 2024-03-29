@file:OptIn(ExperimentalMaterial3Api::class)

package io.github.lexadiky.pdx.library.uikit.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import io.github.lexadiky.pdx.library.uikit.theme.grid
import io.github.lexadiky.pdx.library.uikit.util.saturation

@Composable
fun PillChip(
    label: @Composable () -> Unit,
    labelColor: Color,
    trail: @Composable () -> Unit,
    trailColor: Color,
    textColor: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    val textStyle = MaterialTheme.typography.titleSmall.copy(
        color = textColor
    )
    CompositionLocalProvider(LocalTextStyle provides textStyle) {
        Surface(
            shape = RoundedCornerShape(MaterialTheme.grid.x1),
            color = trailColor,
            modifier = modifier
                .clip(RoundedCornerShape(MaterialTheme.grid.x1))
                .clickable { onClick() }
        ) {
            Row {
                Box(
                    modifier = Modifier
                        .background(labelColor)
                        .padding(horizontal = MaterialTheme.grid.x1, vertical = MaterialTheme.grid.x05)
                ) {
                    label()
                }
                Box(
                    modifier = Modifier
                        .padding(horizontal = MaterialTheme.grid.x1, vertical = MaterialTheme.grid.x05)
                ) {
                    trail()
                }
            }
        }
    }
}

object PillChipDefaults {

    fun trailColor(color: Color): Color =
        color.saturation()
}

