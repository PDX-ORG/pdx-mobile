package io.github.lexadiky.pdx.ui.uikit.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import io.github.lexadiky.pdx.ui.uikit.theme.grid

@Composable
fun BottomSheetHeaderScaffold(
    icon: @Composable () -> Unit = {},
    title: @Composable () -> Unit = {},
    subtitle: @Composable () -> Unit = {},
    endDecoration: @Composable () -> Unit = {}
) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.grid.x1)
            ) {
                icon()
                CompositionLocalProvider(LocalTextStyle provides MaterialTheme.typography.titleLarge) {
                    title()
                }
            }

            CompositionLocalProvider(
                LocalTextStyle provides MaterialTheme.typography.titleMedium
                    .copy(fontWeight = FontWeight.SemiBold)
            ) {
                endDecoration()
            }
        }
        CompositionLocalProvider(LocalTextStyle provides MaterialTheme.typography.bodyMedium) {
            subtitle()
        }
    }
}
